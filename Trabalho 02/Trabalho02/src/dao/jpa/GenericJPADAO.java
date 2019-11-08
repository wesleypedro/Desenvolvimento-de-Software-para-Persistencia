package dao.jpa;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import dao.GenericDAO;
import jpa.JPAUtil;

public class GenericJPADAO<T> implements GenericDAO<T> {
	
	private Class<T> persistenceClass;
	
	public GenericJPADAO(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	@Override
	public void save(T entity) {
		JPAUtil.getEntityManager().merge(entity);
	}
	
	public T saveId(T entity) {
		JPAUtil.getEntityManager().unwrap(persistenceClass);
		Long id = (Long) JPAUtil.getEntityManager().merge(entity);
		System.out.println(id);
		return entity;
	}

	@Override
	public void delete(T entity) {
		JPAUtil.getEntityManager().remove(JPAUtil.getEntityManager().merge(entity));
	}
	
	@Override
	public void deleteById(Object id) {
		delete(find(id));
	}

	@Override
	public T find(Object id) {
		return JPAUtil.getEntityManager().find(persistenceClass, id);
	}

	@Override
	public List<T> findAll() {
		CriteriaQuery<T> cq = JPAUtil.getEntityManager().getCriteriaBuilder().createQuery(persistenceClass);
		cq.from(persistenceClass);
		return JPAUtil.getEntityManager().createQuery(cq).getResultList();
	}

	@Override
	public void beginTransaction() {
		JPAUtil.beginTransaction();
	}

	@Override
	public void commit() {
		JPAUtil.commit();
	}

	@Override
	public void rollback() {
		JPAUtil.rollback();
	}

	@Override
	public void close() {
		JPAUtil.closeEntityManager();
	}
	
}
