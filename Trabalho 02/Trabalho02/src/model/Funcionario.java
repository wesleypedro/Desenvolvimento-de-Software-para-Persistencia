package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario {
	
	/* 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) Indica que a estratégia de 
	 * herança será "JOINED", ou seja, será feita uma junção através de chaves estrangeiras
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoIdentificacao;
	private String nome;
	private String sexo;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private double salario;
	
//	@OneToOne(mappedBy="funcionario")
//	private Endereco endereco;
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	@OneToMany(mappedBy="funcionario")//, fetch=FetchType.LAZY)
	private List<Dependentes> dependentes;
	
	
	public Funcionario() {}
	
	public Funcionario(Long codigoIdentificacao, String nome, String sexo, 
						Date dataNascimento, double salario, Endereco endereco) {
		this(codigoIdentificacao, nome, sexo, dataNascimento, salario, endereco, null);
	}
	
	public Funcionario(Long codigoIdentificacao, String nome, String sexo, 
						Date dataNascimento, double salario, Endereco endereco, List<Dependentes> dependentes) {
		this.codigoIdentificacao = codigoIdentificacao;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.endereco = endereco;
		this.dependentes = dependentes;
	}

	public Long getCodigoIdentificacao() {
		return codigoIdentificacao;
	}
	
	public void setCodigoIdentificacao(Long codigoIdentificacao) {
		this.codigoIdentificacao = codigoIdentificacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public List<Dependentes> getDependentes() {
		return dependentes;
	}
	
	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}
	
	public void setAddDependentes(Dependentes dependente) {
		this.dependentes.add(dependente);
	}

	@Override
	public String toString() {
		return "Funcionario [codigoIdentificacao=" + codigoIdentificacao + ", nome=" + nome + ", sexo=" + sexo
				+ ", dataNascimento=" + dataNascimento + ", salario=" + salario/* + ", endereco=" + endereco
				+ ", departamento=" + departamento + ", dependentes=" + dependentes*/ + "]";
	}
}
