package dao;

import java.util.List;

import model.Telefone;

public interface TelefoneDAO extends GenericDAO<Telefone> {
	
	public List<Telefone> findByClientId(int id);
}
