package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/*
 * @PrimaryKeyJoinColumn(name="codigoIdentificacao"): identifica qual
 * campo fará essa "junção" entre as tabelas Funcionario e Limpeza
 */

@Entity
@PrimaryKeyJoinColumn(name="codigoIdentificacao")
public class Limpeza extends Funcionario {
	private String cargo;
	private int jornadaTrabalho;
	private Long codigoIdentificacaoGerente;
	
	
	public Limpeza() {}
	
	public Limpeza(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, 
			String cargo, int jornadaTrabalho) {
		this(nome, sexo, dataAniversario, salario, endereco, cargo, jornadaTrabalho, (long)0, null);
	}
	
	public Limpeza(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, 
			String cargo, int jornadaTrabalho, List<Dependentes> dependentes) {
		this(nome, sexo, dataAniversario, salario, endereco, cargo, jornadaTrabalho, (long)0, dependentes);
	}
	
	public Limpeza(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, 
					String cargo, int jornadaTrabalho, Long codigoIdentificacaoGerente, List<Dependentes> dependentes) {
		super((long)0, nome, sexo, dataAniversario, salario, endereco, dependentes);
		this.cargo = cargo;
		this.jornadaTrabalho = jornadaTrabalho;
		this.codigoIdentificacaoGerente = codigoIdentificacaoGerente;
	}

	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public int getJornadaTrabalho() {
		return jornadaTrabalho;
	}
	
	public void setJornadaTrabalho(int jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}
	
	public Long getCodigoIdentificacaoGerente() {
		return codigoIdentificacaoGerente;
	}
	
	public void setCodigoIdentificacaoGerente(Long codigoIdentificacaoGerente) {
		this.codigoIdentificacaoGerente = codigoIdentificacaoGerente;
	}
	

	@Override
	public String toString() {
		return "Limpeza [cargo=" + cargo + ", jornadaTrabalho=" + jornadaTrabalho + ", codigoIdentificacaoGerente="
				+ codigoIdentificacaoGerente + ", toString()=" + super.toString() + "]";
	}
}
