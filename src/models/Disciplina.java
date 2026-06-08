package models;

import java.util.Objects;

public class Disciplina {
	private int codigo;
	private String nome;
	private String diaSemana;
	private String horario;
	private int quantidadeHorasDiarias;
	private int codigoCurso;

	public Disciplina(int codigo, String nome, String diaSemana, String horario, int quantidadeHorasDiarias,
			int codigoCurso) {
		this.codigo = codigo;
		this.nome = nome;
		this.diaSemana = diaSemana;
		this.horario = horario;
		this.quantidadeHorasDiarias = quantidadeHorasDiarias;
		this.codigoCurso = codigoCurso;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getQuantidadeHorasDiarias() {
		return quantidadeHorasDiarias;
	}

	public void setQuantidadeHorasDiarias(int quantidadeHorasDiarias) {
		this.quantidadeHorasDiarias = quantidadeHorasDiarias;
	}

	public int getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String toString() {
		return codigo + " - " + nome;
	}

}
