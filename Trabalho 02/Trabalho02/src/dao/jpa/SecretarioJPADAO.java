package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.SecretarioDAO;
import jpa.JPAUtil;
import model.Secretario;

public class SecretarioJPADAO extends GenericJPADAO<Secretario> implements SecretarioDAO {
	public SecretarioJPADAO() {
		super(Secretario.class);
	}

	@Override
	public List<Secretario> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT s FROM Secretario s WHERE s.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Secretario> findByArea(String grau) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT s FROM Secretario s WHERE s.grauEscolaridade LIKE :grau");
		query.setParameter("grau", "%"+grau+"%");
		return query.getResultList();
	}

	@Override
	public List<Secretario> findBySalario(double salario) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT s FROM Secretario s WHERE s.salario=:salario");
		query.setParameter("salario", salario);
		return query.getResultList();
	}

}
