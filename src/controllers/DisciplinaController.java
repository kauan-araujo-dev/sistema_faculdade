package controllers;

import javax.swing.JFrame;

import models.Disciplina;
import services.DisciplinaService;
import views.ViewCadastrarDisciplina;
import views.ViewConsultarDisciplina;
import views.ViewConsultarDisciplinaAbertas;
import views.ViewEditarDisciplina;
import views.ViewExcluirDisciplina;

public class DisciplinaController extends Controller {

	private DisciplinaService disciplinaService;

	public DisciplinaController() {
		disciplinaService = new DisciplinaService();
	}

	public void cadastrarView(JFrame frameAnterior) {
		new ViewCadastrarDisciplina(this, frameAnterior, new CursoController().consultarCursos());

	}

	public void editarView(JFrame frameAnterior) {
		new ViewEditarDisciplina(this, frameAnterior, consultarDisciplinas(), new CursoController().consultarCursos());

	}

	public void excluirView(JFrame frameAnterior) {
		new ViewExcluirDisciplina(this, frameAnterior, consultarDisciplinas());

	}

	public void consultarView(JFrame frameAnterior) {
		new ViewConsultarDisciplina(this, frameAnterior, consultarDisciplinas());
	}

	public void consultarDisciplinasAbertasView(JFrame frameAnterior) {
		new ViewConsultarDisciplinaAbertas(this, frameAnterior, consultarDisciplinasCursosInscritos());
	}

	public void cadastrarDisciplina(String nome, String diaSemana, String horario, int quantidadeHorasDiarias,
			int codigoCurso) {
		disciplinaService.cadastrarDisciplina(nome, diaSemana, horario, quantidadeHorasDiarias, codigoCurso);

	}

	public void editarDisciplina(int id, String nome, String diaSemana, String horario, int quantidadeHorasDiarias,
			int codigoCurso) {
		disciplinaService.editarDisciplina(id, nome, diaSemana, horario, quantidadeHorasDiarias, codigoCurso);
	}

	public void excluirDisciplina(int codigo) {
		disciplinaService.excluirDisciplina(codigo);
	}

	public Disciplina[] consultarDisciplinas() {
		return disciplinaService.consultarDisciplinas();
	}

	public Disciplina consultarDisciplina(int codigo) {
		return disciplinaService.consultarDisciplina(codigo);
	}

	public Object[][] consultarDisciplinasCursosInscritos() {
		return disciplinaService.consultarDisciplinasCursosInscritos();
	}
}
