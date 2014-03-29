package org.dt340a.group6.sprint2.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dt340a.group6.sprint2.dao.FailureclassDAO;
import org.dt340a.group6.sprint2.entities.Failureclass;

@JPA
public class JPAFailureclassDAO implements FailureclassDAO {

    @PersistenceContext(unitName="sprint2")
    private EntityManager em;
    
    @Override
    public void addFailureclass(Failureclass failureclass) {
        Query query = em.createQuery("SELECT f FROM Failureclass f");
        List<Failureclass> failureclasses = query.getResultList(); 
        if (!failureclasses.contains(failureclass))
            em.persist(failureclass);          
    }

    @Override
    public Failureclass getFailureclassById(String id) {
        // TODO Auto-generated method stub      
        Query query  = em.createQuery("FROM Failureclass f WHERE f.id = :id");
        query.setParameter("id", id);
        // remember we are assuming only one album has any particular title
        // could have used getSingleResult() but this an exception if there is more than one result
        List<Failureclass> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public List<Failureclass> getFailureclassesByDescription(String description) {
        // TODO Auto-generated method stu
        Query query  = em.createQuery("FROM Failureclass f WHERE f.description = :description");
        query.setParameter("description", description);
        List<Failureclass> result = query.getResultList();
        return result;
    }

    @Override
    public List<Failureclass> getAllFailureclasses() {
        // TODO Auto-generated method stub
        // not the best place to start at TX but nevermind for now!
        System.out.println("JPAFailureclassDAO "+4);
//        Query query = em.createQuery("SELECT DISTINCT f FROM Failureclass f LEFT JOIN FETCH f.callfailures"); //JOIN FETCH f.callfailures");
        Query query = em.createNamedQuery("Failureclass.findAll");
        
        
        
        List<Failureclass> failureclasses = (List<Failureclass>) query.getResultList(); 
        System.out.println(failureclasses.size());
        return failureclasses;
    }

}
