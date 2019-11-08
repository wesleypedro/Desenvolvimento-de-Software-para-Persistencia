package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/*
 * @PrimaryKeyJoinColumn(name="codigoIdentificacao"): identifica qual
 * campo fará essa "junção" entre as tabelas Funcionario e Limpeza
 */

@Entity
@PrimaryKeyJoinColumn(name="codigoIdentificacao")
public class Pesquisador extends Funcionario {
	private String areaAtuacao;
	
	@OneToMany(mappedBy="pesquisador")
	private List<PesquisadorProjetos> pesquisadorProjetos;
	
	public Pesquisador() {}
	
	public Pesquisador(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, 
			String areaAtuacao) {
		this(nome, sexo, dataAniversario, salario, endereco, areaAtuacao, null);
	}
	
	public Pesquisador(String nome, String sexo, Date dataAniversario, double salario, Endereco endereco, 
					String areaAtuacao, List<PesquisadorProjetos> pesquisadorProjetos) {
		super((long)0, nome, sexo, dataAniversario, salario, endereco);
		this.areaAtuacao = areaAtuacao;
		this.pesquisadorProjetos = pesquisadorProjetos;
	}
	
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
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
		return "Pesquisador [areaAtuacao=" + areaAtuacao /*+ ", pesquisadorProjetos=" + pesquisadorProjetos*/
				+ ", toString()=" + super.toString() + "]";
	}
}
