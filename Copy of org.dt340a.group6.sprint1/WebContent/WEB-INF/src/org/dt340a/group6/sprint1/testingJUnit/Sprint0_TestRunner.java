package org.dt340a.group6.sprint1.testingJUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Sprint0_TestRunner {
	
   public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(Sprint0_TestSuite.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println("\n\n\nThe tests run successfully: "+result.wasSuccessful());
	}
}
