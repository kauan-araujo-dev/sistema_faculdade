package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import config.Config;
import lista.Lista;
import models.Inscricao;
import models.Professor;

public class ProfessorRepository implements RepositoryInterface {
	Lista<Professor> professores = new Lista<Professor>();

	public ProfessorRepository() {
		try {
			this.lerCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler");
		}
	}

	public void cadastrar(Professor professor) {
		professores.addLast(professor);
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void editar(Professor professor) {
		int size = professores.size();

		for (int i = 0; i < size; i++) {
			Professor professorAux = professores.get(i);

			if (professorAux.getCpf().equals(professor.getCpf())) {
				professores.remove(i);
				professores.add(professor, i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluir(String cpf) {
		for (int i = 0; i < professores.size(); i++) {
			Professor professor = professores.get(i);

			if (professor.getCpf().equals(cpf)) {
				professores.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public Professor[] consultar() {
		int size = professores.size();
		Professor[] arrayprofessores = new Professor[size];

		for (int i = 0; i < size; i++) {
			Professor professor = professores.get(i);
			arrayprofessores[i] = professor;
		}
		return arrayprofessores;
	}

	public Professor consultarPorCpf(String cpf) {
		int size = professores.size();
		Professor professor = null;

		for (int i = 0; i < size; i++) {
			professor = professores.get(i);
			if (!professor.getCpf().equals(cpf)) {
				professor = null;
			}
		}
		return professor;
	}

	public Professor[] consultarPorDisciplina(int codigoDisciplina) {

		Inscricao[] inscricoes = new InscricaoRepository().consultarPorDisciplina(codigoDisciplina);
		Professor[] professoresAux = new Professor[inscricoes.length];

		for (int i = 0; i < professores.size(); i++) {

			for (int j = 0; j < professoresAux.length; j++) {
				if (professores.get(i).getCpf().equals(inscricoes[j].getCpf())) {
					professoresAux[j] = professores.get(i);
				}
			}
		}

		return professoresAux;
	}

	@Override
	public void registrarCSV() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_PROFESSORES));

		writer.close();

		writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_PROFESSORES));
		int size = professores.size();

		for (int i = 0; i < size; i++) {

			Professor professor = professores.get(i);
			writer.write(professor.getCpf() + ";" + professor.getNome() + ";" + professor.getArea() + ";"
					+ professor.getQuantidadePontos());
			writer.newLine();

		}
		consultar();
		writer.close();
	}

	@Override
	public void lerCSV() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_PROFESSORES));

		String linha = reader.readLine();

		while (linha != null) {

			String[] professorTexto = linha.split(";");

			Professor professor = new Professor(professorTexto[0], professorTexto[1], professorTexto[2],
					Double.parseDouble(professorTexto[3]));

			professores.addLast(professor);

			linha = reader.readLine();

		}
		reader.close();
	}

}
