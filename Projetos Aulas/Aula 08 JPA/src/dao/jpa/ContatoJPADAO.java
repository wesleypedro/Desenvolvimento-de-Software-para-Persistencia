package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.ContatoDAO;
import jpa.JPAUtil;
import model.Contato;

public class ContatoJPADAO extends GenericJPADAO<Contato> implements ContatoDAO {
	public ContatoJPADAO() {
		super(Contato.class);
	}

	@Override
	public List<Contato> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query  query = em.createNamedQuery("Contato.findByName");
		query.setParameter("nome", nome);
		
		return query.getResultList();
	}

	@Override
	public List<Contato> findByPartedoNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query  query = em.createNamedQuery("SELECT c FROM Contato c WHERE p.nome LIKE :nome");
		query.setParameter("nome", nome+"%");
		
		return query.getResultList();
	}

}
