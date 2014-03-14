package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dt340a.group6.sprint1.entity.User;

import entity.ListItem;



public class PersistenceUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sprint1"); 	
	
	
	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		if(entity != null){
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Object mergedEntity = em.merge(entity);
			em.remove(mergedEntity);
			em.getTransaction().commit();
			em.close();
		}
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
		
	public static List<User> findAllUsers(){
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findAll").getResultList();
		em.close();		
		return users;		
	}
	
	public static User findUserByUsername(String username){
		
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
		em.close();
		
		if (users.size() == 0)
			return null;
		else 
			return users.get(0);
	}
	
	public static List<ListItem> findAllItems(){
		EntityManager em = emf.createEntityManager();
		List<ListItem> items = (List<ListItem>) em.createNamedQuery("ListItem.findAll").getResultList();
		em.close();		
		return items;		
	}
	public static ListItem findListItem(User user, String content){
		EntityManager em = emf.createEntityManager();
		List<ListItem> items = (List<ListItem>) em.createNamedQuery("ListItem.findAll").getResultList();
		for(ListItem item : items){
			if(item.getUser().getId() == user.getId() && item.getItemContent().equals(content)){
				return item;
			}
		}
		em.close();	
		return null;		
	}
	public static ListItem findListItemByUserId(int userId){
		
		EntityManager em = emf.createEntityManager();
		List<ListItem> items = (List<ListItem>) em.createNamedQuery("ListItem.findByUserId").setParameter("userId", userId).getResultList();
		em.close();
		
		if (items.size() == 0)
			return null;
		else 
			return items.get(0);
	}
}

