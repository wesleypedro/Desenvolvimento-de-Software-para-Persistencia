package dao;

import java.util.List;

import model.Limpeza;

public interface LimpezaDAO extends GenericDAO<Limpeza> {

	List<Limpeza> findByNome(String nome);
	List<Limpeza> findByCargo(String cargo);
	List<Limpeza> findBySalario(double salario);
	List<Limpeza> findByJornada(int joranada);
}
