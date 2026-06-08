package services;

import models.Disciplina;
import repository.DisciplinaRepository;

public class DisciplinaService {

	private DisciplinaRepository disciplinaRepository;

	public DisciplinaService() {
		disciplinaRepository = new DisciplinaRepository();
	}

	public void cadastrarDisciplina(String nome, String diaSemana, String horario, int quantidadeHorasDiarias,
			int codigoCurso) {
		int id = disciplinaRepository.getUltimoId();
		Disciplina disciplina = new Disciplina(id + 1, nome, diaSemana, horario, quantidadeHorasDiarias, codigoCurso);

		disciplinaRepository.cadastrar(disciplina);

	}

	public void editarDisciplina(int id, String nome, String diaSemana, String horario, int quantidadeHorasDiarias,
			int codigoCurso) {
		Disciplina disciplina = new Disciplina(id, nome, diaSemana, horario, quantidadeHorasDiarias, codigoCurso);

		disciplinaRepository.editar(disciplina);
	}

	public void excluirDisciplina(int codigo) {
		disciplinaRepository.excluir(codigo);
		new InscricaoService().excluirInscricaoPorCodigoDisciplina(codigo);

	}

	public void excluirDisciplinaPorCodigoCurso(int codigo) {
		Disciplina[] disciplina = consultarDisciplinas();
		for (int i = disciplina.length - 1; i >= 0; i--) {
			if (disciplina[i].getCodigoCurso() == codigo) {
				excluirDisciplina(disciplina[i].getCodigo());
			}
		}

	}

	public Disciplina[] consultarDisciplinas() {
		return disciplinaRepository.consultar();
	}

	public Disciplina consultarDisciplina(int codigo) {
		return disciplinaRepository.consultarPorCodigo(codigo);
	}

	public Disciplina[] consultarDisciplinasInscritas() {
		Disciplina[] disciplinas = disciplinaRepository.consultar();
		InscricaoService inscricaoService = new InscricaoService();

		Disciplina[] temporario = new Disciplina[inscricaoService.disciplinasAbertas.length];
		int contador = 0;
		for (int i = 0; i < disciplinas.length; i++) {

			if (inscricaoService.verificarDisciplinaAberta(disciplinas[i])) {
				temporario[contador] = disciplinas[i];
				contador++;
			}
		}
		Disciplina[] disciplinasInscritas = new Disciplina[contador];
		for (int i = 0; i < contador; i++) {
			disciplinasInscritas[i] = temporario[i];
		}
		return disciplinasInscritas;
	}

	public Object[][] consultarDisciplinasCursosInscritos() {

		Disciplina[] disciplinasInscritas = consultarDisciplinasInscritas();
		Object[][] disciplinasCursosInscritos = new Object[disciplinasInscritas.length][2];

		CursoService cursoService = new CursoService();

		for (int i = 0; i < disciplinasInscritas.length; i++) {
			disciplinasCursosInscritos[i][0] = disciplinasInscritas[i];
			disciplinasCursosInscritos[i][1] = cursoService.consultarCurso(disciplinasInscritas[i].getCodigoCurso());
		}
		return disciplinasCursosInscritos;
	}

}
