package dao;

import model.Pesquisador;
import model.PesquisadorProjetos;
import model.Projetos;

public interface PesquisadorProjetosDAO extends GenericDAO<PesquisadorProjetos> {

	PesquisadorProjetos findByRelation(Pesquisador codigo, Projetos id);

}
