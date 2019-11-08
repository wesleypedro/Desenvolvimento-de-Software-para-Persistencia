package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import dao.ContatoDAO;
import dao.jpa.ContatoJPADAO;
import jpa.JPAUtil;
import model.Contato;
import model.Endereco;
import model.Foto;
import model.Telefone;

public class Principal {
	public static void main(String[] args) {
//		InserirDados();
//		BuscarComJPQL();
//		BuscarComCriteriaQuery();
//		BuscarComNamedQuery();
//		BuscarComNativeQuery();
//		BuscarComNativeQuery2();
//		InsereComJPAUtil();
//		InserirDadosComDAO();
//		DeleteComDAO();
//		BuscarComDAO();
//		BuscarPorNome();
//		BuscarPorPartedoNome();
		Paginacao();
		
		/*
		 * Exercício: faça um método findByNome para buscar um contato 
		 * (ou mais) por nome. Pense onde colocar esse método (onde faz
		 * sentido ele estar).
		 */
		
	}
	
	public static void InserirDados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		try {
			tx.begin();
//			em.persist(new Contato("Wesley", "1234567890"));
//			em.persist(new Contato("Pedro", "0987654321"));
//			em.persist(new Contato("Francisco", "1230984567"));
//			em.persist(new Contato("Lima", "9000000001"));
			tx.commit();
		} catch(IllegalStateException | PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComJPQL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		
		List<Contato> contatos = em.createQuery("FROM Contato", Contato.class).getResultList();
		em.close();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for (Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComCriteriaQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contato> cq = cb.createQuery(Contato.class);
		cq.from(Contato.class);
		List<Contato> contatos = em.createQuery(cq).getResultList();
		em.close();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for (Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComNamedQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		
		List<Contato> contatos = em.createNamedQuery("Contato.findAll", Contato.class).getResultList();
		em.close();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for (Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComNativeQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createNativeQuery("SELECT * FROM Contato");
		
		List<Object[]> result = query.getResultList();
		List<Contato> contatos = new ArrayList<>();

		em.close();
		
		for(Object[] linha :  result) {
			int id = (int) linha[0];
			String nome = (String) linha[1];
			String telefone = (String) linha[2];
			
			contatos.add(new Contato(id, nome, telefone));
		}
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for (Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComNativeQuery2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createNativeQuery("SELECT * FROM Contato", Contato.class);
		List<Contato> contatos = query.getResultList();
		em.close();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for (Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void InsereComJPAUtil() {
		EntityManager em = JPAUtil.getEntityManager();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		try {
			JPAUtil.beginTransaction();
			
			em.persist(new Contato("Carlos", "123123123"));
			em.persist(new Contato("Marcelo", "987987987"));
			em.persist(new Contato("Amanada", "456456456"));
			
			JPAUtil.commit();
		} catch(IllegalStateException | PersistenceException e) {
			JPAUtil.rollback();
			e.printStackTrace();
		} finally {
			JPAUtil.closeEntityManager();
		}
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void InserirDadosComDAO() {
		ContatoDAO cDao = new ContatoJPADAO();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		try {
			cDao.beginTransaction();
//			cDao.save(new Contato("Ronaldo", "999"));
//			cDao.save(new Contato("Mary", "100"));
//			cDao.save(new Contato("Junior", "300"));
//			cDao.save(new Contato(7, "Amanda", "321"));
			cDao.commit();
			
		} catch(IllegalStateException | PersistenceException e) {
			cDao.rollback();
			e.printStackTrace();
		} finally {
			cDao.close();
		}
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void DeleteComDAO() {		
		ContatoDAO cDao = new ContatoJPADAO();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		try {
			
			cDao.beginTransaction();
//			Contato c = cDao.find(13);
//			cDao.delete(c);
//			cDao.close();
//			cDao.commit();
			
			cDao.deleteById(12);
			cDao.close();
			cDao.commit();
			
		} catch(IllegalStateException | PersistenceException e) {
			cDao.rollback();
			e.printStackTrace();
		} finally {
			cDao.close();
		}
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarComDAO() {
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		ContatoDAO cDao = new ContatoJPADAO();
		List<Contato> contatos = cDao.findAll();
		cDao.close();
		
		for(Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarPorNome() {
		System.out.println("==================================================");
		System.out.println("Início JPA");

		ContatoDAO cDao = new ContatoJPADAO();
		List<Contato> contatos = cDao.findByNome("Junio");
		cDao.close();
		
		for(Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void BuscarPorPartedoNome() {
		System.out.println("==================================================");
		System.out.println("Início JPA");

		ContatoDAO cDao = new ContatoJPADAO();
		List<Contato> contatos = cDao.findByNome("Junio");
		cDao.close();
		
		for(Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void Paginacao() {
		EntityManager em = JPAUtil.getEntityManager();
		
		List<Contato> contatos = em.createQuery("FROM Contato")
									.setFirstResult(3)
									.setMaxResults(4)
									.getResultList(); 
		JPAUtil.closeEntityManager();
		
		System.out.println("==================================================");
		System.out.println("Início JPA");
		
		for(Contato contato : contatos) System.out.println(contato);
		
		System.out.println("FIM JPA");
		System.out.println("==================================================");
	}
	
	public static void InserirContatoAssociacoes() {
		Contato c = new Contato("Wesley");
		Foto foto = new Foto(10, 10, c);
		c.setFoto(foto);
		
		List<Telefone> fones = new ArrayList<Telefone>();
		fones.add(new Telefone(88, 111, c));
		fones.add(new Telefone(85, 111, c));
		fones.add(new Telefone(88, 222, c));
		c.setTelefones(fones);
		
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(new Endereco("A", 1));
		enderecos.add(new Endereco("B", 2));
		enderecos.add(new Endereco("C", 3));
		
		for(Endereco e : enderecos) {
			
		}
	}
}
