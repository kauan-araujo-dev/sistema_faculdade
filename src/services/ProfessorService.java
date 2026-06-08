package services;

import models.Professor;
import repository.ProfessorRepository;

public class ProfessorService {
	private ProfessorRepository professorRepository;

	public ProfessorService() {
		professorRepository = new ProfessorRepository();
	}

	public Professor[] professoresOrdenadosPontuacao(Professor[] professores) {

		return quickSort(professores, 0, professores.length - 1);
	}

	public void cadastrarProfessor(String cpf, String nome, String area, double quantidadePontos) {
		Professor professor = new Professor(cpf, nome, area, quantidadePontos);
		professorRepository.cadastrar(professor);

	}

	public void editarProfessor(String cpf, String nome, String area, double quantidadePontos) {
		Professor professor = new Professor(cpf, nome, area, quantidadePontos);

		professorRepository.editar(professor);
	}

	public void excluirProfessor(String cpf) {
		professorRepository.excluir(cpf);
		new InscricaoService().excluirInscricaoPorCpf(cpf);
	}

	public Professor[] consultarProfessores() {
		return professorRepository.consultar();
	}

	public Professor[] consultarProfessoresDisciplina(int codigoDisciplina) {
		return professoresOrdenadosPontuacao(professorRepository.consultarPorDisciplina(codigoDisciplina));
	}

	public Professor consultarProfessoresPorCpf(String cpf) {
		return professorRepository.consultarPorCpf(cpf);
	}

	private Professor[] quickSort(Professor[] vetor, int inicio, int fim) {

		if (inicio < fim) {
			int pivoFixo = dividir(vetor, inicio, fim);
			quickSort(vetor, inicio, pivoFixo - 1);
			quickSort(vetor, pivoFixo + 1, fim);
		}
		for (Professor professor : vetor) {
			System.out.println(professor);
		}
		return vetor;

	}

	private int dividir(Professor[] vetor, int inicio, int fim) {
		double pivo = vetor[inicio].getQuantidadePontos();
		int ponteiroEsquerda = inicio + 1;
		int ponteiroDireita = fim;

		while (ponteiroEsquerda <= ponteiroDireita) {
			while (ponteiroEsquerda <= ponteiroDireita && vetor[ponteiroEsquerda].getQuantidadePontos() > pivo) {
				ponteiroEsquerda++;
			}
			while (ponteiroEsquerda <= ponteiroDireita && vetor[ponteiroDireita].getQuantidadePontos() <= pivo) {
				ponteiroDireita--;
			}
			if (ponteiroEsquerda < ponteiroDireita) {
				Professor aux = vetor[ponteiroEsquerda];
				vetor[ponteiroEsquerda] = vetor[ponteiroDireita];
				vetor[ponteiroDireita] = aux;

				ponteiroEsquerda++;
				ponteiroDireita--;
			}
		}
		Professor aux = vetor[ponteiroDireita];
		vetor[ponteiroDireita] = vetor[inicio];
		vetor[inicio] = aux;

		return ponteiroDireita;

	}
}
