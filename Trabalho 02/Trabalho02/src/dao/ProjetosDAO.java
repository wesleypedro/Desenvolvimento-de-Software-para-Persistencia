package dao;

import java.util.List;

import model.Projetos;

public interface ProjetosDAO extends GenericDAO<Projetos> {

	List<Projetos> findByNome(String nome);

	List<Projetos> findByNumero(Long numero);

}
