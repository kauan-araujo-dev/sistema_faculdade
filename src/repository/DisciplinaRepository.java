package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import config.Config;
import lista.Lista;
import models.Disciplina;

public class DisciplinaRepository implements RepositoryInterface, IdIncrementoInterface {
	Lista<Disciplina> disciplinas = new Lista<Disciplina>();

	public DisciplinaRepository() {
		try {
			this.lerCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler" + e.getMessage());
		}
	}

	public void cadastrar(Disciplina disciplina) {
		disciplinas.addLast(disciplina);
		try {
			registrarCSV();
			registrarId(disciplina.getCodigo());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar disciplina");
		}
	}

	public void editar(Disciplina disciplina) {
		int size = disciplinas.size();

		for (int i = 0; i < size; i++) {
			Disciplina disciplinaAux = disciplinas.get(i);

			if (disciplina.getCodigo() == disciplinaAux.getCodigo()) {
				disciplinas.remove(i);
				disciplinas.add(disciplina, i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluir(int id) {
		for (int i = 0; i < disciplinas.size(); i++) {
			Disciplina disciplina = disciplinas.get(i);

			if (disciplina.getCodigo() == id) {
				disciplinas.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluirPorCodigoCurso(int id) {
		for (int i = disciplinas.size() - 1; i >= 0; i--) {
			Disciplina disciplina = disciplinas.get(i);
			if (disciplina.getCodigoCurso() == id) {
				excluir(disciplina.getCodigo());
			}
		}
	}

	public Disciplina[] consultar() {
		int size = disciplinas.size();
		Disciplina[] arrayDisciplinas = new Disciplina[size];

		for (int i = 0; i < size; i++) {
			Disciplina disciplina = disciplinas.get(i);
			arrayDisciplinas[i] = disciplina;
		}
		return arrayDisciplinas;
	}

	public Disciplina consultarPorCodigo(int codigo) {
		int size = disciplinas.size();
		Disciplina disciplina = null;

		for (int i = 0; i < size; i++) {
			disciplina = disciplinas.get(i);
			if (disciplina.getCodigo() == codigo) {
				return disciplina;
			}
		}
		return disciplina;
	}

	@Override
	public int getUltimoId() {
		int id = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_ID_DISCIPLINAS));
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
			BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_DISCIPLINAS));

			writer.close();

			writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_DISCIPLINAS));

			writer.write(Integer.toString(id));

			writer.close();

		} catch (IOException e) {
			throw new RuntimeException("Erro ao registrar id");
		}
	}

	@Override
	public void registrarCSV() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_DISCIPLINAS));

		writer.close();

		writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_DISCIPLINAS));
		int size = disciplinas.size();

		for (int i = 0; i < size; i++) {

			Disciplina disciplina = disciplinas.get(i);
			writer.write(disciplina.getCodigo() + ";" + disciplina.getNome() + ";" + disciplina.getDiaSemana() + ";"
					+ disciplina.getHorario() + ";" + disciplina.getQuantidadeHorasDiarias() + ";"
					+ disciplina.getCodigoCurso());
			writer.newLine();

		}
		writer.close();
	}

	@Override
	public void lerCSV() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_DISCIPLINAS));

		String linha = reader.readLine();

		while (linha != null && !linha.equals("")) {

			String[] disciplinaTexto = linha.split(";");

			Disciplina disciplina = new Disciplina(Integer.parseInt(disciplinaTexto[0]), disciplinaTexto[1],
					disciplinaTexto[2], disciplinaTexto[3], Integer.parseInt(disciplinaTexto[4]),
					Integer.parseInt(disciplinaTexto[5]));

			disciplinas.addLast(disciplina);

			linha = reader.readLine();

		}

		reader.close();
	}

}
