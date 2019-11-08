package dao;

import java.util.List;

import model.Departamento;

public interface DepartamentoDAO extends GenericDAO<Departamento> {

	List<Departamento> findByNome(String nome);

	List<Departamento> findByNumero(int numero);

}
