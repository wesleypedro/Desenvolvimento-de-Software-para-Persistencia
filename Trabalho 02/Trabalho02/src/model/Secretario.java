package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/*
 * @PrimaryKeyJoinColumn(name="codigoIdentificacao"): identifica qual
 * campo fará essa "junção" entre as tabelas Funcionario e Limpeza
 */

@Entity
@PrimaryKeyJoinColumn(name="codigoIdentificacao")
public class Secretario extends Funcionario {
	private String grauEscolaridade;
	
	public Secretario() {}
	
//	public Secretario(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, String gauEscolaridade) {
//		this(nome, sexo, dataAniversario, salario, endereco, grauEscolaridade);
//	}
	
	public Secretario(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, String grauEscolaridade) {
		super((long)0, nome, sexo, dataAniversario, salario, endereco);
		this.grauEscolaridade = grauEscolaridade;
	}
	
	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	@Override
	public String toString() {
		return "Secretario [grauEscolaridade=" + grauEscolaridade + ", toString()=" + super.toString() + "]";
	}
}
