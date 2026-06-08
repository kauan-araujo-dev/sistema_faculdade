package controllers;

import javax.swing.JFrame;

import models.Professor;
import services.ProfessorService;
import views.ViewCadastrarProfessor;
import views.ViewConsultarProfessor;
import views.ViewEditarProfessor;
import views.ViewExcluirProfessor;

public class ProfessorController extends Controller {

	private ProfessorService professorService;

	public ProfessorController() {
		professorService = new ProfessorService();
	}

	public void cadastrarView(JFrame frameAnterior) {
		new ViewCadastrarProfessor(this, frameAnterior);

	}

	public void editarView(JFrame frameAnterior) {
		new ViewEditarProfessor(this, frameAnterior, consultarProfessores());

	}

	public void excluirView(JFrame frameAnterior) {
		new ViewExcluirProfessor(this, frameAnterior, consultarProfessores());

	}

	public void consultarView(JFrame frameAnterior) {
		new ViewConsultarProfessor(this, frameAnterior, consultarProfessores());

	}

	public void cadastrarProfessor(String cpf, String nome, String area, double quantidadePontos) {
		professorService.cadastrarProfessor(cpf, nome, area, quantidadePontos);

	}

	public void editarProfessor(String cpf, String nome, String area, double quantidadePontos) {

		professorService.editarProfessor(cpf, nome, area, quantidadePontos);
	}

	public void excluirProfessor(String cpf) {

		professorService.excluirProfessor(cpf);
	}

	public Professor[] consultarProfessores() {
		return professorService.consultarProfessores();
	}

	public Professor[] consultarProfessoresDisciplina(int codigoDisciplina) {
		return professorService.consultarProfessoresDisciplina(codigoDisciplina);
	}

	public Professor consultarProfessoresPorCpf(String cpf) {
		return professorService.consultarProfessoresPorCpf(cpf);
	}

}
