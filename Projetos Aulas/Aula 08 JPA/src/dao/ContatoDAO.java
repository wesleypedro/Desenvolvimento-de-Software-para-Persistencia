package dao;

import java.util.List;

import model.Contato;

public interface ContatoDAO extends GenericDAO<Contato>{
	public List<Contato> findByNome(String nome);
	public List<Contato> findByPartedoNome(String nome);
}
