package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Projetos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="projetos_id")
	private Long id;
	private String nome;
	private Long numeroIdentificacao;
	private Date inicioDesenvolvimento;
	private Date fimDesenvolvimento;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="departamento_id")
	private Departamento departamento;
	
//	@OneToMany(mappedBy="numeroIdentificacaoProjeto", fetch=FetchType.EAGER)
	@OneToMany(mappedBy="projetos")
	private List<PesquisadorProjetos> pesquisadorProjetos;
	

	
	public Projetos() {}
	
	public Projetos(Long id, String nome, Long numeroIdentificacao, Date inicioDesenvolvimento, Date fimDesenvolvimento) {
		this(id, nome, numeroIdentificacao, inicioDesenvolvimento, fimDesenvolvimento, null, null);
	}

	public Projetos(String nome, Long numeroIdentificacao, Date inicioDesenvolvimento, Date fimDesenvolvimento) {
		this((long)0, nome, numeroIdentificacao, inicioDesenvolvimento, fimDesenvolvimento, null, null);
	}

	public Projetos(int id, String nome, Long numeroIdentificacao, Date inicioDesenvolvimento, Date fimDesenvolvimento,
			Departamento departamento, List<PesquisadorProjetos> pesquisadorProjetos) {
		this.id = (long)id;
		this.nome = nome;
		this.numeroIdentificacao = numeroIdentificacao;
		this.inicioDesenvolvimento = inicioDesenvolvimento;
		this.fimDesenvolvimento = fimDesenvolvimento;
		this.departamento = departamento;
		this.pesquisadorProjetos = pesquisadorProjetos;
	}
	
	public Projetos(Long id, String nome, Long numeroIdentificacao, Date inicioDesenvolvimento, Date fimDesenvolvimento,
			Departamento departamento, List<PesquisadorProjetos> pesquisadorProjetos) {
		this.id = id;
		this.nome = nome;
		this.numeroIdentificacao = numeroIdentificacao;
		this.inicioDesenvolvimento = inicioDesenvolvimento;
		this.fimDesenvolvimento = fimDesenvolvimento;
		this.departamento = departamento;
		this.pesquisadorProjetos = pesquisadorProjetos;
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
	
	public Long getNumeroIdentificacao() {
		return numeroIdentificacao;
	}
	
	public void setNumeroIdentificacao(Long numeroIdentificacao) {
		this.numeroIdentificacao = numeroIdentificacao;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Date getInicioDesenvolvimento() {
		return inicioDesenvolvimento;
	}
	
	public void setInicioDesenvolvimento(Date inicioDesenvolvimento) {
		this.inicioDesenvolvimento = inicioDesenvolvimento;
	}
	
	public Date getFimDesenvolvimento() {
		return fimDesenvolvimento;
	}
	
	public void setFimDesenvolvimento(Date fimDesenvolvimento) {
		this.fimDesenvolvimento = fimDesenvolvimento;
	}
	
	public List<PesquisadorProjetos> getPesquisadorProjetos() {
		return pesquisadorProjetos;
	}
	
	public void setPesquisadorProjetos(List<PesquisadorProjetos> pesquisadorProjetos) {
		this.pesquisadorProjetos = pesquisadorProjetos;
	}

	public void setAddPesquisadorProjetos(PesquisadorProjetos pesquisadorProjetos) {
		this.pesquisadorProjetos.add(pesquisadorProjetos);
	}

	@Override
	public String toString() {
		return "Projeto:\n\tId = " + id + "\n\tNome = " + nome + "\n\tNúmero de Identificação = " + numeroIdentificacao
				+ "\n\tData de Início de Desenvolvimento = " + inicioDesenvolvimento 
				+ "\n\tData de Fim de Desenvolvimento = " + fimDesenvolvimento
				/*+ ", departamento=" + departamento + ", pesquisadorProjetos=" + pesquisadorProjetos*/;	
	}
}
