package models;

public class Inscricao {
	private String cpf;
	private int codigoDisciplina;
	private int codigoProcesso;

	public Inscricao() {
	}

	public Inscricao(String cpf, int codigoDisciplina, int codigoProcesso) {
		super();
		this.cpf = cpf;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public int getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(int codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	public String toString() {
		return codigoProcesso + " - " + cpf;
	}

}
