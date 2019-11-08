package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * 
 */

@Entity
public class PesquisadorProjetos {
	@Id
//	@Column(name="pesquisador_projetos_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
//    @MapsId("codigoIdentificacao")
    @JoinColumn(name = "codigoIdentificacao")
    private Pesquisador pesquisador;
	
	@ManyToOne
//    @MapsId("projetos_id")
    @JoinColumn(name = "projetos_id")
    private Projetos projetos;
	
	private int numeroHoras;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public Projetos getProjetos() {
		return projetos;
	}

	public void setProjetos(Projetos projetos) {
		this.projetos = projetos;
	}

	public int getNumeroHoras() {
		return numeroHoras;
	}
	
	public void setNumeroHoras(int numeroHoras) {
		this.numeroHoras = numeroHoras;
	}
}
