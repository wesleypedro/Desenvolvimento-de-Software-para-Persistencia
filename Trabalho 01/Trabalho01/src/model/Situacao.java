package model;

public class Situacao {
	private String idSituacao;
	private String situacao;
	
	public Situacao(String idSituacao, String situacao) {
		this.idSituacao = idSituacao;
		this.situacao = situacao;
	}

	public Situacao() {}

	public String getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(String idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
