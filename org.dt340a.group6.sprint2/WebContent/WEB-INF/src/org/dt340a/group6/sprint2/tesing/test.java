package org.dt340a.group6.sprint2.tesing;

import org.dt340a.group6.sprint2.dao.jpa.JPAFailureclassDAO;
import org.dt340a.group6.sprint2.rest.FailureclassRestService;

public class test {

    public static void main(String args[]) {
        System.out.println("test "+1);
        JPAFailureclassDAO d = new JPAFailureclassDAO();
        System.out.println(d.getAllFailureclasses());
//        List<Failureclass> c = ;
//        for(Failureclass f : c) {
//            System.out.println(f.getDescription());
//        }
    }
    
}
