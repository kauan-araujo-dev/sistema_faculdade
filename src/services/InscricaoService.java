package services;

import lista.Lista;
import models.Disciplina;
import models.Inscricao;
import repository.DisciplinaRepository;
import repository.InscricaoRepository;

public class InscricaoService {

	private InscricaoRepository inscricaoRepository;
	Lista<Disciplina>[] disciplinasAbertas;

	public InscricaoService() {
		inscricaoRepository = new InscricaoRepository();
		tabelaEspalhamento();
	}

	public void cadastrarInscricao(String cpf, int codigoDisciplina) {
		int codigoProcesso = inscricaoRepository.getUltimoId();
		Inscricao inscricao = new Inscricao(cpf, codigoDisciplina, codigoDisciplina + 1);
		inscricaoRepository.cadastrar(inscricao);

	}

	public void editarInscricao(String cpf, int codigoDisciplina, int codigoProcesso) {
		Inscricao inscricao = new Inscricao(cpf, codigoDisciplina, codigoProcesso);

		inscricaoRepository.editar(inscricao);
	}

	public void excluirInscricao(int codigoProcesso) {
		inscricaoRepository.excluir(codigoProcesso);
	}

	public void excluirInscricaoPorCpf(String cpf) {
		inscricaoRepository.excluirPorCpf(cpf);
	}

	public void excluirInscricaoPorCodigoDisciplina(int codigoDisciplina) {
		inscricaoRepository.excluirPorDisciplina(codigoDisciplina);
	}

	public Inscricao[] consultarInscricoes() {
		return inscricaoRepository.consultar();
	}

	public Inscricao consultarInscricaoesPorCodigoProcesso(int codigoProcesso) {
		return inscricaoRepository.consultarPorCodigoProcesso(codigoProcesso);
	}

	public void tabelaEspalhamento() {

		Inscricao[] inscricoes = inscricaoRepository.consultar();
		int tamanho = 101;
		this.disciplinasAbertas = new Lista[tamanho];

		DisciplinaService disciplinaService = new DisciplinaService();

		for (int i = 0; i < tamanho; i++) {
			disciplinasAbertas[i] = new Lista<Disciplina>();
		}
		for (int i = 0; i < inscricoes.length; i++) {
			Inscricao inscricao = inscricoes[i];
			Disciplina disciplina = disciplinaService.consultarDisciplina(inscricao.getCodigoDisciplina());
			int posicao = hash(disciplina.getCodigo(), tamanho);

			disciplinasAbertas[posicao].addLast(disciplina);
		}
	}

	public boolean verificarDisciplinaAberta(Disciplina disciplina) {
		int tamanho = disciplinasAbertas.length;
		int posicao = hash(disciplina.getCodigo(), tamanho);
		int tamanhoLista = disciplinasAbertas[posicao].size();

		for (int i = 0; i < tamanhoLista; i++) {
			Disciplina d = disciplinasAbertas[posicao].get(i);
			if (d.getCodigo() == disciplina.getCodigo()) {
				return true;
			}
		}
		return false;
	}

	public int hash(int codigo, int tamanho) {
		return codigo % tamanho;
	}

}
