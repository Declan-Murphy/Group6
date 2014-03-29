package org.dt340a.group6.sprint2.services;

import java.util.List;
import javax.ejb.Local;
import org.dt340a.group6.sprint2.entities.Failureclass;

//@WebService
@Local
public interface FailureclassService {

    public List<Failureclass> getFailureclasses() ;
    public void addToFailureclasses(Failureclass failureclass);
    
}
