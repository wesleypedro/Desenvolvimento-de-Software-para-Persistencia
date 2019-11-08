package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.ProjetosDAO;
import jpa.JPAUtil;
import model.Projetos;

public class ProjetosJPADAO extends GenericJPADAO<Projetos> implements ProjetosDAO {
	public ProjetosJPADAO() {
		super(Projetos.class);
	}

	public List<Projetos> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Projetos p WHERE p.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Projetos> findByNumero(Long numero) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Projetos p WHERE p.numeroIdentificacao LIKE :numero");
		query.setParameter("numero", numero);
		return query.getResultList();
	}
}
