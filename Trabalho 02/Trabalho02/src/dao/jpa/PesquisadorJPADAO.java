package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.PesquisadorDAO;
import jpa.JPAUtil;
import model.Pesquisador;

public class PesquisadorJPADAO extends GenericJPADAO<Pesquisador> implements PesquisadorDAO {

//	public PesquisadorJPADAO(Class<Pesquisador> persistenceClass) {
//		super(persistenceClass);
//	}
	
	public PesquisadorJPADAO() {
		super(Pesquisador.class);
	}

	@Override
	public List<Pesquisador> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Pesquisador p WHERE p.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Pesquisador> findByArea(String area) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Pesquisador p WHERE p.areaAtuacao LIKE :area");
		query.setParameter("area", "%"+area+"%");
		return query.getResultList();
	}

	@Override
	public List<Pesquisador> findBySalario(double salario) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Pesquisador p WHERE p.salario=:salario");
		query.setParameter("salario", salario);
		return query.getResultList();
	}

}
