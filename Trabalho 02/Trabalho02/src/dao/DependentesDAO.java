package dao;

import java.util.List;

import model.Dependentes;

public interface DependentesDAO extends GenericDAO<Dependentes> {

	List<Dependentes> findByNome(String nome);

}
