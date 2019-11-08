package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="departamento_id")
	private Long id;
	private String nome;
	private int numero;
	
	@OneToMany(mappedBy="departamento", cascade=CascadeType.ALL)//, fetch=FetchType.LAZY)
	private List<Funcionario> funcionarios;
	
	@OneToMany(mappedBy="departamento", cascade=CascadeType.ALL)//, fetch=FetchType.LAZY)
	private List<Projetos> projetos;
	
	
	public Departamento() {}
	
	public Departamento(Long id, String nome, int numero) {
		this(id, nome, numero, null, null);
	}
	
	public Departamento(String nome, int numero) {
		this(0, nome, numero, null, null);
	}
	
	public Departamento(int id, String nome, int numero, List<Funcionario> funcionarios, List<Projetos> projetos) {
		this.id = (long) id;
		this.nome = nome;
		this.numero = numero;
		this.funcionarios = funcionarios;
		this.projetos = projetos;
	}
	
	public Departamento(Long id, String nome, int numero, List<Funcionario> funcionarios, List<Projetos> projetos) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.funcionarios = funcionarios;
		this.projetos = projetos;
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
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public void setAddFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}
	
	public List<Projetos> getProjetos() {
		return projetos;
	}
	
	public void setProjetos(List<Projetos> projetos) {
		this.projetos = projetos;
	}
	
	public void setAddProjetos(Projetos projeto) {
		this.projetos.add(projeto);
	}

	@Override
	public String toString() {
		return "Departamento\nId = " + id + "\nNome = " + nome + "\nNúmero = " + numero + "\nFuncionários = " + funcionarios
				+ "\nProjetos:\n\t" + projetos + "\n";
	}
	
}
