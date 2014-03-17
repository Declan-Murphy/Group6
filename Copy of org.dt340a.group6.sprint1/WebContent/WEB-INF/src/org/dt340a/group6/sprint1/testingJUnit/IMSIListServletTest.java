package org.dt340a.group6.sprint1.testingJUnit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.servlet.IMSIListServlet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import static org.powermock.api.easymock.PowerMock.*;
//import static org.easymock.EasyMock.expect;

public class IMSIListServletTest {

	private String fromText = "2013-01-01T00:00";
	private String middleText = "2013-07-01T00:00";
	// private String toText = "2014-01-01T00:00";
	private String toText = "2013-02-19T19:36";
	private Date from;
	private Date middle;
	private Date to;
	private List<CallFailure> callFailures;
	private HashSet<String> IMSIs;
	private String firstIMSI;

	// private IMSIListServlet servlet;
	// private HttpServletRequest request;
	// private HttpServletResponse response;
	//
	// @Before
	// public void setUp() throws Exception {
	// servlet = new IMSIListServlet();
	// request.setAttribute("from", "2013-01-01T00:00");
	// request.setAttribute("to", "2014-01-01T00:00");
	// response.getWriter()
	//
	// }

	@Before
	public void setUp() throws Exception {
		IMSIs = new HashSet<>();
		setDates();
		setFirstIMSI();
		// setIMSIs();
		// createList();
	}

	@Test
	public void testIMSIListServlet() {
		// fail("Not yet implemented");
	}

	@Test
	public void testDoGet() {
		IMSIListServlet servlet = new IMSIListServlet();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getParameter("from")).thenReturn(fromText);
		when(request.getParameter("to")).thenReturn(toText);
		// StringWriter stringWriter = new StringWriter();
		PrintWriter writer;
		try {
			writer = new PrintWriter("testfile.txt");
			writer.println("in file");
			when(response.getWriter()).thenReturn(writer);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// PrintWriter writer = new PrintWriter(System.out);
		// System.out.println("here");
		// System.out.println(stringWriter.toString());
		// System.out.println("after");
		try {
			// assertTrue(FileUtils
			// .readFileToString(new File("testfile.txt"), "UTF-8").contains(
			// firstIMSI));
			System.out.println("here");
			System.out.println(FileUtils.readFileToString(new File("testfile.txt"), "UTF-8"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			servlet.doGet(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	public void setDates() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			from = sdf.parse(fromText);
			middle = sdf.parse(middleText);
			to = sdf.parse(toText);
		} catch (java.text.ParseException exception) {
			exception.printStackTrace();
		}
	}

	public void setIMSIs() {
		getFailures();
		for (CallFailure failure : callFailures)
			IMSIs.add(failure.getiMSI());
	}

	public void setFirstIMSI() {
		getFailures();
		firstIMSI = callFailures.get(0).getiMSI();
	}

	public void getFailures() {
		callFailures = PersistenceUtil.findAllCallFailuresBetween(from, to);
	}

	public void createList() {
		Cause cause = new Cause.Builder().description("failure reason").build();
		CallFailure failure = new CallFailure.Builder().dateTime(middle).cause(cause).iMSI("1234").build();
		callFailures.add(failure);
	}

}
