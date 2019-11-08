package dao;

import java.util.List;

import model.Pesquisador;

public interface PesquisadorDAO extends GenericDAO<Pesquisador> {

	List<Pesquisador> findByNome(String nome);

	List<Pesquisador> findByArea(String area);

	List<Pesquisador> findBySalario(double salario);

}
