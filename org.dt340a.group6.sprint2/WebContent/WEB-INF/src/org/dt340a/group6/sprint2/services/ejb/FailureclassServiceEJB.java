package org.dt340a.group6.sprint2.services.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dt340a.group6.sprint2.dao.FailureclassDAO;
import org.dt340a.group6.sprint2.dao.jpa.JPA;
import org.dt340a.group6.sprint2.entities.Failureclass;
import org.dt340a.group6.sprint2.services.FailureclassService;



@Stateless
//@WebService(endpointInterface="com.conygre.training.services.CompactDiscService")
@Local(FailureclassService.class)
public class FailureclassServiceEJB implements FailureclassService {

    private FailureclassDAO dao;

    @Inject
    public void setDao(@JPA FailureclassDAO dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Failureclass> getFailureclasses() {
        System.out.println("FailureclassServiceEJB"+ 3);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Failureclass> failureclasses = dao.getAllFailureclasses();
        for (Failureclass f : failureclasses) {
            System.out.println(f.getDescription());
        }
        return failureclasses;
    }

    @Override
    public void addToFailureclasses(Failureclass failureclass) {
        if (!dao.getAllFailureclasses().contains(failureclass))
            dao.addFailureclass(failureclass);
    }

}