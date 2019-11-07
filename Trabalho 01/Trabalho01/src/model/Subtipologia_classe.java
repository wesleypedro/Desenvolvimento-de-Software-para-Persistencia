package model;

public class Subtipologia_classe {
	private String idSituacao;
	private String codigo_subclasse;
	private String desc_classe;
	
	public Subtipologia_classe(String idSituacao, String codigo_subclasse, String desc_classe) {
		this.idSituacao = idSituacao;
		this.codigo_subclasse = codigo_subclasse;
		this.desc_classe = desc_classe;
	}

	public Subtipologia_classe() {}

	public String getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(String idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getCodigo_subclasse() {
		return codigo_subclasse;
	}

	public void setCodigo_subclasse(String codigo_subclasse) {
		this.codigo_subclasse = codigo_subclasse;
	}

	public String getDesc_classe() {
		return desc_classe;
	}

	public void setDesc_classe(String desc_classe) {
		this.desc_classe = desc_classe;
	}
}
