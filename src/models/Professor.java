package models;

public class Professor {
	private String cpf;
	private String nome;
	private String area;
	private double quantidadePontos;

	public Professor() {
	}

	public Professor(String cpf, String nome, String area, double quantidadePontos) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.quantidadePontos = quantidadePontos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getQuantidadePontos() {
		return quantidadePontos;
	}

	public void setQuantidadePontos(double quantidadePontos) {
		this.quantidadePontos = quantidadePontos;
	}

	public String toString() {
		return cpf + " - " + nome;
	}
}
