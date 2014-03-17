package org.dt340a.group6.sprint1.servlet.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.dt340a.group6.sprint1.query.UserStory12Object;
import org.junit.Test;
import org.mockito.Mockito;

import antlr.collections.List;

public class UserStory12ServletTest {

//	@Test
//	public final void testDoGetHttpServletRequestHttpServletResponse() throws Exception {
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);       
//		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//		
//		Mockito.when(request.getParameter("startDateTime")).thenReturn("2013-01-01T00:00");
////		Mockito.when(request.getParameter("startDateTime")).thenReturn("2013-01-01T00:00");
//		PrintWriter writer = new PrintWriter("somefile.txt");
//		
//		new UserStory12Servlet().doPost(request, response);
//		
////		verify(request, atLeast(1)).getParameter("startDateTime"); 
//		writer.flush();
//		assertTrue(FileUtils.readFileToString(new File("somefile.txt"), "UTF-8").contains("My Expected String"));
//    }
	
//	@Test // TODO
//	public final void testSortTheArrayListOfUserStory12Objects(){
//		ArrayList<UserStory12Object> listOfUSOs = null;
//		UserStory12Object uso1 = null;
//		uso1.setImsi("191911000423586");
//		uso1.setCount(1000);
//		listOfUSOs.add(uso1);
//		UserStory12Object uso2 = null;
//		uso2.setImsi("191911000154506");
//		uso2.setCount(10500);
//		listOfUSOs.add(uso2);
//		UserStory12Object uso3 = null;
//		uso3.setImsi("191911000386769");
//		uso3.setCount(50);
//		listOfUSOs.add(uso3);
//		UserStory12Object uso4 = null;
//		uso4.setImsi("191911000437458");
//		uso4.setCount(50000);
//	}
}
