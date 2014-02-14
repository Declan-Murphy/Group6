package main;

import java.util.List;

import persistence.PersistenceUtil;
import entity.FailureClass;

public class FailureClassConfig {
	
	public static void main(String[] args){
		FailureClassConfig config = new FailureClassConfig();
	}

	public FailureClassConfig(){
		createFailureClass(123, "1234565");
	}


	public void viewFailureClass(){
		List<FailureClass> failureClasses = PersistenceUtil.findAllFailureClasses();
		for(FailureClass f : failureClasses){
			System.out.println("FailureClass " + f.getDescription()+ " exists.");
		}
	}
	
	public void createFailureClass(int failureClassID, String description){
		FailureClass failureClass = new FailureClass(failureClassID, description);
		PersistenceUtil.persist(failureClass);
		System.out.println("FailureClass Created");
	}
	
}
