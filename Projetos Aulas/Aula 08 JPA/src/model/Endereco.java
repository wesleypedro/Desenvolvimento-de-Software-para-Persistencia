package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="endereco_id")
	private int id;
	private String nome;
	private int numero;
	
	@ManyToMany
	@JoinTable(
			name="contato_endereco",
			joinColumns=@JoinColumn(name="endereco_id", referencedColumnName="endereco_id"),
						inverseJoinColumns = @JoinColumn(name="contato_id", referencedColumnName="contato_id")
			)
	private List<Contato> contato;
	
	public Endereco(String nome, int numero) {
		this.id = 0;
		this.nome = nome;
		this.numero = numero;
		this.contato = null;
	}

	public Endereco(int id, String nome, int numero, List<Contato> contato) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.contato = contato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", nome=" + nome + ", numero=" + numero + ", contato=" + contato + "]";
	}
	
	
}
