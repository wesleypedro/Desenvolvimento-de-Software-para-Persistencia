package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.PesquisadorProjetosDAO;
import jpa.JPAUtil;
import model.Pesquisador;
import model.PesquisadorProjetos;
import model.Projetos;

public class PesquisadorProjetosJPADAO extends GenericJPADAO<PesquisadorProjetos> implements PesquisadorProjetosDAO {
	public PesquisadorProjetosJPADAO() {
		super(PesquisadorProjetos.class);
	}

	@Override
	public PesquisadorProjetos findByRelation(Pesquisador pesquisador, Projetos projeto) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM PesquisadorProjetos p"
				+ " WHERE p.pesquisador=:pesquisador AND p.projetos=:projeto");
		query.setParameter("pesquisador", pesquisador);
		query.setParameter("projeto", projeto);
		return (PesquisadorProjetos) query.getSingleResult();
	}

}
