package services;

import models.Curso;
import repository.CursoRepository;
import repository.DisciplinaRepository;

public class CursoService {

	private CursoRepository cursoRepository;

	public CursoService() {
		cursoRepository = new CursoRepository();
	}

	public void cadastrarCurso(String nome, String area) {
		int id = cursoRepository.getUltimoId();
		Curso curso = new Curso(id + 1, nome, area);

		cursoRepository.cadastrar(curso);

	}

	public void editarCurso(int codigo, String nome, String area) {
		Curso curso = new Curso(codigo, nome, area);

		cursoRepository.editar(curso);
	}

	public Curso[] consultarCursos() {
		return cursoRepository.consultar();
	}

	public Curso consultarCurso(int codigo) {
		return cursoRepository.consultarPorCodigo(codigo);
	}

	public void excluirCurso(int codigo) {
		cursoRepository.excluir(codigo);
		new DisciplinaService().excluirDisciplinaPorCodigoCurso(codigo);
	}
}
