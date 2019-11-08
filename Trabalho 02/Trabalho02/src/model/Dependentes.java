package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependentes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dependentes_id")
	private Long id;
	private String nome;
	private String sexo;
	private Date dataAniversario;
	private String grauParentesco;
	
	@ManyToOne
	@JoinColumn(name="codigoIdentificacao")
	private Funcionario funcionario;
	
	public Dependentes() {}
	
	public Dependentes(String nome, String sexo, Date dataAniversario, String grauParentesco) {
		this((long)0, nome, sexo, dataAniversario, grauParentesco, null);
	}

	public Dependentes(Long id, String nome, String sexo, Date dataAniversario, String grauParentesco,
			Funcionario funcionario) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataAniversario = dataAniversario;
		this.grauParentesco = grauParentesco;
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getDataAniversario() {
		return dataAniversario;
	}
	
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	public String getGrauParentesco() {
		return grauParentesco;
	}
	
	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Dependentes [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", dataAniversario=" + dataAniversario
				+ ", grauParentesco=" + grauParentesco /*+ ", funcionario=" + funcionario*/ + "]";
	}
}
