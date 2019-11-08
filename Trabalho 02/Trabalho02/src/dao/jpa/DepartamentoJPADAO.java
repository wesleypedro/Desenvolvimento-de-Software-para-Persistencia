package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.DepartamentoDAO;
import jpa.JPAUtil;
import model.Departamento;

public class DepartamentoJPADAO extends GenericJPADAO<Departamento> implements DepartamentoDAO {
	public DepartamentoJPADAO() {
		super(Departamento.class);
	}

	@Override
	public List<Departamento> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Departamento> findByNumero(int numero) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT d FROM Departamento d WHERE d.numero LIKE :numero");
		query.setParameter("numero", numero);
		return query.getResultList();
	}
}
