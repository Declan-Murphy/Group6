package org.dt340a.group6.sprint2.dao;

import java.util.List;
import org.dt340a.group6.sprint2.entities.Failureclass;

public interface FailureclassDAO {
    
    void addFailureclass(Failureclass failureclass);
    Failureclass getFailureclassById(String id);
    List<Failureclass> getFailureclassesByDescription(String description);
    List<Failureclass> getAllFailureclasses();

}
