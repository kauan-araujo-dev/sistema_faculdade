package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import config.Config;
import lista.Lista;
import models.Curso;

public class CursoRepository implements RepositoryInterface, IdIncrementoInterface {
	Lista<Curso> cursos = new Lista<Curso>();

	public CursoRepository() {
		try {
			this.lerCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler");
		}
	}

	public void cadastrar(Curso curso) {
		cursos.addLast(curso);
		try {
			registrarCSV();
			registrarId(curso.getCodigo());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void editar(Curso curso) {
		int size = cursos.size();

		for (int i = 0; i < size; i++) {
			Curso cursoAux = cursos.get(i);

			if (curso.getCodigo() == cursoAux.getCodigo()) {
				cursos.remove(i);
				cursos.add(curso, i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public void excluir(int id) {
		for (int i = 0; i < cursos.size(); i++) {
			Curso curso = cursos.get(i);

			if (curso.getCodigo() == id) {
				cursos.remove(i);
			}
		}
		try {
			registrarCSV();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuário");
		}
	}

	public Curso[] consultar() {
		int size = cursos.size();
		Curso[] arrayCursos = new Curso[size];

		for (int i = 0; i < size; i++) {
			Curso curso = cursos.get(i);
			arrayCursos[i] = curso;
		}
		return arrayCursos;
	}

	public Curso consultarPorCodigo(int codigo) {
		int size = cursos.size();
		Curso curso = null;

		for (int i = 0; i < size; i++) {
			curso = cursos.get(i);
			if (curso.getCodigo() == codigo) {
				return curso;
			}
		}

		return curso;
	}

	@Override
	public int getUltimoId() {
		int id = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_ID_CURSOS));
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
			BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_CURSOS));

			writer.close();

			writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_ID_CURSOS));

			writer.write(Integer.toString(id));

			writer.close();

		} catch (IOException e) {
			throw new RuntimeException("Erro ao registrar id");
		}
	}

	@Override
	public void registrarCSV() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_CURSOS));

		writer.close();

		writer = new BufferedWriter(new FileWriter(Config.ARQUIVO_CURSOS));
		int size = cursos.size();

		for (int i = 0; i < size; i++) {

			Curso curso = cursos.get(i);
			writer.write(curso.getCodigo() + ";" + curso.getNome() + ";" + curso.getArea());
			writer.newLine();

		}
		consultar();
		writer.close();
	}

	@Override
	public void lerCSV() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Config.ARQUIVO_CURSOS));

		String linha = reader.readLine();

		while (linha != null) {

			String[] cursoTexto = linha.split(";");

			Curso curso = new Curso(Integer.parseInt(cursoTexto[0]), cursoTexto[1], cursoTexto[2]);

			cursos.addLast(curso);

			linha = reader.readLine();

		}
		reader.close();
	}

}
