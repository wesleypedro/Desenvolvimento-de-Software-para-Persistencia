package model;

public class Assunto {
	private String CodigoAssunto;
	private String DescricaoAssunto;
	private String SiglaAssunto;
	
	public Assunto(String codigoAssunto, String descricaoAssunto, String siglaAssunto) {
		this.CodigoAssunto = codigoAssunto;
		this.DescricaoAssunto = descricaoAssunto;
		this.SiglaAssunto = siglaAssunto;
	}

	public Assunto() {}

	public String getCodigoAssunto() {
		return CodigoAssunto;
	}

	public void setCodigoAssunto(String codigoAssunto) {
		CodigoAssunto = codigoAssunto;
	}

	public String getDescricaoAssunto() {
		return DescricaoAssunto;
	}

	public void setDescricaoAssunto(String descricaoAssunto) {
		DescricaoAssunto = descricaoAssunto;
	}

	public String getSiglaAssunto() {
		return SiglaAssunto;
	}

	public void setSiglaAssunto(String siglaAssunto) {
		SiglaAssunto = siglaAssunto;
	}
}