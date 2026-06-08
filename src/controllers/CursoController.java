package controllers;

import javax.swing.JFrame;

import models.Curso;
import services.CursoService;
import views.ViewCadastrarCurso;
import views.ViewConsultarCurso;
import views.ViewEditarCurso;
import views.ViewExcluirCurso;

public class CursoController extends Controller {

	private services.CursoService cursoService;

	public CursoController() {
		cursoService = new CursoService();
	}

	public void cadastrarView(JFrame frameAnterior) {
		new ViewCadastrarCurso(this, frameAnterior);

	}

	public void editarView(JFrame frameAnterior) {
		new ViewEditarCurso(this, frameAnterior, consultarCursos());

	}

	public void excluirView(JFrame frameAnterior) {
		new ViewExcluirCurso(this, frameAnterior, consultarCursos());

	}

	public void consultarView(JFrame frameAnterior) {
		new ViewConsultarCurso(this, frameAnterior, consultarCursos());
	}

	public void cadastrarCurso(String nome, String area) {
		cursoService.cadastrarCurso(nome, area);

	}

	public void editarCurso(int id, String nome, String area) {
		cursoService.editarCurso(id, nome, area);
	}

	public Curso[] consultarCursos() {
		return cursoService.consultarCursos();
	}

	public Curso consultarCurso(int id) {
		return cursoService.consultarCurso(id);
	}

	public void excluirCurso(int id) {
		cursoService.excluirCurso(id);
	}

}
