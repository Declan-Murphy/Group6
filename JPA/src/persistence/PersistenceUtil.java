package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Subscriber;



public class PersistenceUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a"); 	
	
	
	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}
	
	
	public static List<Subscriber> findAllSubscribers(){
		EntityManager em = emf.createEntityManager();
		List<Subscriber> subscribers = (List<Subscriber>) em.createNamedQuery("Subscriber.findAll").getResultList();
		em.close();
		
		return subscribers;
		
	}
	

	public static Subscriber findSubscriberByUsername(String username){
		
		EntityManager em = emf.createEntityManager();
		List<Subscriber> subscribers = (List<Subscriber>) em.createNamedQuery("Subscriber.findByUsername").setParameter("username", username).getResultList();
		em.close();
		
		if (subscribers.size() == 0)
			return null;
		else 
			return subscribers.get(0);
	}
	

	
}

