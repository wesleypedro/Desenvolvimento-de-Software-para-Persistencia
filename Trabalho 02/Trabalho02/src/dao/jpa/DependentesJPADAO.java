package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.DependentesDAO;
import jpa.JPAUtil;
import model.Dependentes;

public class DependentesJPADAO extends GenericJPADAO<Dependentes> implements DependentesDAO {
	public DependentesJPADAO() {
		super(Dependentes.class);
	}

	@Override
	public List<Dependentes> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT d FROM Dependentes d WHERE d.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}
}
