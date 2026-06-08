package controllers;

import javax.swing.JFrame;

import models.Inscricao;
import services.InscricaoService;
import views.ViewCadastrarInscricao;
import views.ViewConsultarInscricao;
import views.ViewEditarInscricao;
import views.ViewExcluirInscricao;
import views.ViewInscritosDisciplina;

public class InscricaoController extends Controller {

	private InscricaoService inscricaoService;

	public InscricaoController() {
		inscricaoService = new InscricaoService();
	}

	public void cadastrarView(JFrame frameAnterior) {
		new ViewCadastrarInscricao(this, frameAnterior, new ProfessorController().consultarProfessores(),
				new DisciplinaController().consultarDisciplinas());

	}

	public void editarView(JFrame frameAnterior) {
		new ViewEditarInscricao(this, frameAnterior, consultarInscricoes(),
				new ProfessorController().consultarProfessores(), new DisciplinaController().consultarDisciplinas());
	}

	public void excluirView(JFrame frameAnterior) {
		new ViewExcluirInscricao(this, frameAnterior, consultarInscricoes());
	}

	public void consultarView(JFrame frameAnterior) {
		new ViewConsultarInscricao(this, frameAnterior, consultarInscricoes());
	}

	public void consultarPorDisciplinasView(JFrame frameAnterior) {
		new ViewInscritosDisciplina(new ProfessorController(), frameAnterior,
				new DisciplinaController().consultarDisciplinas());
	}

	public void cadastrarInscricao(String cpf, int codigoDisciplina) {
		inscricaoService.cadastrarInscricao(cpf, codigoDisciplina);

	}

	public void editarInscricao(String cpf, int codigoDisciplina, int codigoProcesso) {
		inscricaoService.editarInscricao(cpf, codigoDisciplina, codigoProcesso);
	}

	public void excluirInscricao(int codigoProcesso) {
		inscricaoService.excluirInscricao(codigoProcesso);
	}

	public Inscricao[] consultarInscricoes() {
		return inscricaoService.consultarInscricoes();
	}

	public Inscricao consultarInscricaoesPorCodigoProcesso(int codigoProcesso) {
		return inscricaoService.consultarInscricaoesPorCodigoProcesso(codigoProcesso);
	}

}
