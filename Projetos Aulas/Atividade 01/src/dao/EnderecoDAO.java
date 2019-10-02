package dao;

import java.util.List;

import model.Endereco;

public interface EnderecoDAO extends GenericDAO<Endereco> {
	
	public List<Endereco> findByClientId(int id);
}
