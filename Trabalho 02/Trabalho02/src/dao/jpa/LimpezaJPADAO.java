package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.LimpezaDAO;
import jpa.JPAUtil;
import model.Limpeza;

public class LimpezaJPADAO extends GenericJPADAO<Limpeza> implements LimpezaDAO {

	public LimpezaJPADAO() {
		super(Limpeza.class);
	}

	@Override
	public List<Limpeza> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT l FROM Limpeza l WHERE l.nome LIKE :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Limpeza> findByCargo(String cargo) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT l FROM Limpeza l WHERE l.cargo LIKE :cargo");
		query.setParameter("cargo", "%"+cargo+"%");
		return query.getResultList();
	}

	@Override
	public List<Limpeza> findBySalario(double salario) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT l FROM Limpeza l WHERE l.salario=:salario");
		query.setParameter("salario", salario);
		return query.getResultList();
	}

	@Override
	public List<Limpeza> findByJornada(int jornada) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT l FROM Limpeza l WHERE l.jornadaTrabalho LIKE :jornada");
		query.setParameter("jornada", jornada);
		return query.getResultList();
	}

}
