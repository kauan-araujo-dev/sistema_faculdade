package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import config.Config;
import lista.Lista;
import models.Disciplina;
import models.Inscricao;

public class InscricaoRepository implements RepositoryInterface, IdIncrementoInterface {
	Lista<Inscricao> inscricoes = new Lista<Inscricao>();

	public InscricaoRepository() {
		try {
			this.lerCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler");
		}
	}

	public void cadastrar(Inscricao inscricao) {
		inscricoes.addLast(inscricao);
		try {

			registrarCSV();
			int size = inscricoes.size();
			System.out.println("Size: " + size);

			for (int i = 0; i < size; i++) {
				Inscricao inscricaoAux = inscricoes.get(i);

				System.out.println("CPF: " + inscricaoAux.getCpf());
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void editar(Inscricao inscricao) {
		int size = inscricoes.size();

		for (int i = 0; i < size; i++) {
			Inscricao inscricaoAux = inscricoes.get(i);

			if (inscricaoAux.getCodigoProcesso() == inscricao.getCodigoProcesso()) {
				inscricoes.remove(i);
				inscricoes.add(inscricao, i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluir(int codigoProcesso) {
		for (int i = 0; i < inscricoes.size(); i++) {
			Inscricao inscricao = inscricoes.get(i);

			if (inscricao.getCodigoProcesso() == codigoProcesso) {
				inscricoes.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluirPorDisciplina(int codigoDisciplina) {
		for (int i = inscricoes.size() - 1; i >= 0; i--) {
			Inscricao inscricao = inscricoes.get(i);
			if (inscricao.getCodigoDisciplina() == codigoDisciplina) {
				inscricoes.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluirPorCpf(String cpf) {
		for (int i = inscricoes.size() - 1; i >= 0; i--) {
			Inscricao inscricao = inscricoes.get(i);

			if (inscricao.getCpf().equals(cpf)) {
				inscricoes.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public Inscricao[] consultar() {
		int size = inscricoes.size();
		Inscricao[] arrayInscricoes = new Inscricao[size];

		for (int i = 0; i < size; i++) {
			Inscricao inscricao = inscricoes.get(i);
			arrayInscricoes[i] = inscricao;
		}
		return arrayInscricoes;
	}

	public Inscricao consultarPorCodigoProcesso(int codigoProcesso) {
		int size = inscricoes.size();
		Inscricao inscricao = null;

		for (int i = 0; i < size; i++) {
			inscricao = inscricoes.get(i);
			if (inscricao.getCodigoProcesso() == codigoProcesso) {
				inscricao = null;
			}
		}
		return inscricao;
	}

	public Inscricao[] consultarPorDisciplina(int codigoDisciplina) {
		int size = inscricoes.size();
		Inscricao inscricao = null;
		int contador = 0;
		for (int i = 0; i < size; i++) {
			inscricao = inscricoes.get(i);
			if (inscricao.getCodigoDisciplina() == codigoDisciplina) {
				contador++;
			}
		}

		Inscricao[] inscricoesArray = new Inscricao[contador];
		System.out.println(contador);
		for (int i = 0; i < size; i++) {
			inscricao = inscricoes.get(i);
			if (inscricao.getCodigoDisciplina() == codigoDisciplina) {
				inscricoesArray[--contador] = inscricao;
			}

		}
		return inscricoesArray;
	}

	@Override
	public void registrarCSV() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_INSCRICOES));

		writer.close();

		writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_INSCRICOES));
		int size = inscricoes.size();

		for (int i = 0; i < size; i++) {

			Inscricao inscricao = inscricoes.get(i);
			writer.write(
					inscricao.getCpf() + ";" + inscricao.getCodigoDisciplina() + ";" + inscricao.getCodigoProcesso());
			writer.newLine();

		}
		consultar();
		writer.close();
	}

	@Override
	public void lerCSV() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_INSCRICOES));

		String linha = reader.readLine();

		while (linha != null) {

			String[] inscricaoTexto = linha.split(";");

			Inscricao inscricao = new Inscricao(inscricaoTexto[0], Integer.parseInt(inscricaoTexto[1]),
					Integer.parseInt(inscricaoTexto[2]));

			inscricoes.addLast(inscricao);

			linha = reader.readLine();

		}
		reader.close();
	}

	@Override
	public int getUltimoId() {
		int id = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_ID_INSCRICOES));
			String idTexto = reader.readLine();
			reader.close();

			if (idTexto != null) {
				id = Integer.parseInt(idTexto);
			}

		} catch (Exception e) {
			System.out.println("Erro ao consultar id");
		}
		return id;
	}

	@Override
	public void registrarId(int id) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_INSCRICOES));

			writer.close();

			writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_INSCRICOES));

			writer.write(Integer.toString(id));

			writer.close();

		} catch (IOException e) {
			throw new RuntimeException("Erro ao registrar id");
		}
	}

}
