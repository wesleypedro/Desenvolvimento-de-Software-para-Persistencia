package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="endereco_id")
	private Long id;
	private String logradouro;
	private String bairro;
	private int numero;
	private String cidade;
	
//	@OneToOne
//	@JoinColumn(name="codigoIdentificacao")
//	private Funcionario funcionario;
	
	public Endereco() {}
	
	public Endereco(String logradouro, String bairro, int numero, String cidade) {
		this(0, logradouro, bairro, numero, cidade);
	}

	public Endereco(int id, String logradouro, String bairro, int numero, String cidade/*, Funcionario funcionario*/) {
		this.id = (long)id;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
//		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
//	public Funcionario getFuncionario() {
//		return funcionario;
//	}
//	
//	public void setFuncionario(Funcionario funcionario) {
//		this.funcionario = funcionario;
//	}
}
