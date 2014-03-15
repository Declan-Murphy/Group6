package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.client.am.DateTime;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.validation.PrimitiveCheck;
import org.junit.internal.runners.statements.Fail;

/**
 * Servlet implementation class Us5servlet
 */
//@WebServlet("/Us5servlet")
public class Us5servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private java.util.Date startDate = null;
	private java.util.Date endDate = null;
	private String imsi;
	private String startDateString;
	private String endDateString;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Us5servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		imsi = request.getParameter("username");

		startDateString = request.getParameter("startDate");
		endDateString = request.getParameter("endDate");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	
			try {
				startDate = sdf.parse(startDateString);
				endDate = sdf.parse(endDateString);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


		List<CallFailure> callFailures = PersistenceUtil
				.findCallByIMSIBetweenDate(imsi,startDate,endDate);

		topText(out);

		test(out);
		if (PrimitiveCheck.isLong(imsi)) {
			if (callFailures == null) {
				noResultsFound(out);
			} else {
				mid(out, callFailures);
			}
		} else {
			notAValidImsi(out);
		}

		bottomText(out);
	}

	private void notAValidImsi(PrintWriter out) {
		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
		out.println("                    <tr>");
		out.println("                      <td>Warning Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>The value '" + imsi
				+ "' is not a valid IMSI. Please enter a valid IMSI.</td>");
		out.println("                    </tr>");

		out.println("                </table>");
		out.println("            </div>");
	}

	private void noResultsFound(PrintWriter out) {
		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>This search has returned 0 results for IMSI: "
				+ imsi + " </td>");
		out.println("                    </tr>");

		out.println("                </table>");
		out.println("            </div>");
	}

	private void topText(PrintWriter out) {
		out.println("<html>");
		out.println("    <head>");
		out.println("       <title>Dt340a - Group 6</title>");
		out.println("      <link rel='icon' type='image/ico' href='http://www.ericsson.com/favicon.ico'/>");
		out.println("        <link rel='stylesheet' type='text/css' href='css/mystyle.css'>");
		out.println("        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("    </head>");
		out.println("    <body>");
		out.println("        <div id='container'>");
		out.println("            <div id='heading-container'>");
		out.println("                <div id='eir-image'> ");
		out.println("                    <img alt='Ericsson' src='http://www.ericsson.com/shared/eipa/images/elogo.png'>   ");
		out.println("                </div> ");
		out.println("                <div id='dit-image'> ");
		out.println("                 <img alt='DIT' src='http://www.dit.ie/media/styleimages/dit_crest.gif' width='90px' height='90px'>  ");
		out.println("                </div> ");
		out.println("                <h1>Call Investigation Assistant</h1>");
		out.println("                <h2>Group 6</h2> ");
		out.println("                <h3>Customer Service Representative View</h3>");
		out.println("                <h4>User Story 5</h4>");
		out.println("                <h4> 5)	As a Customer Service Rep, I want to count, for a given IMSI, ");
		out.println("                <br>the number of failures they have had during a given time period</h4>");
		out.println("            </div>");

	}

	private void mid(PrintWriter out, List<CallFailure> callFailures) {
		int count = 0;

		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
		out.println("                    <tr class ='alt'>");
		out.println("	                 	<th>IMSI</th> ");
		out.println("                        <th>Start Date</th>");
		out.println("                        <th>End Date</th>");
		out.println("                        <th>Total Call Failures</th>");
		out.println("                     </tr>");

		out.println("                     <tr >");
		out.println("                     <td>" + imsi + "</td>");
		out.println("                      <td>" + startDate.toString()
				+ "</td>");
		out.println("                      <td>" + endDate.toString() + "</td>");
		out.println("                      <td> <Font color='red'>" + callFailures.size()
				+ "</font></td>");

		out.println("                    </tr>");

		out.println("                        </table>");
		out.println("                        </div>");
	}

	private void test(PrintWriter out) {

		out.println("<div id='inner-container'>");
		out.println("<form method=GET action='Us5servlet'>");
		out.println("<h4>Total Call Failures Over Time </h4>");

		out.println("<input class='submissionfield' type='text' name='username' value="
				+ imsi
				+ " placeholder='Please Enter an IMSI number Here' required='required'>");
		out.println("<br/>");
		out.println("<br/>");
		out.println("<label for='startDate'>Start Date</label>");
		out.println("<input type='datetime-local' name='startDate' value="
				+ startDateString
				+ " placeholder='Please Enter a start date Here' id='date' />");

		out.println("<label for='endDate'>End Date</label>");
		out.println("<input type='datetime-local' name='endDate' value="
				+ endDateString
				+ " placeholder='Please Enter end date Here' id='date' /> ");
		out.println("<input type='submit'>");
		out.println("<input Type='button' VALUE='Back' onClick='history.go(-1);return true;'>");
		out.println("</form>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js'></script>");
		out.println("<script src='js/jquery-ui.js'></script>");
		out.println("<script> (function() {	var elem = document.createElement('input');");
		out.println("elem.setAttribute('type', 'date');");
		out.println("if (elem.type === 'text') {");
		out.println("$('#date').datepicker();");
		out.println("}");
		out.println("})();");
		out.println("</script>");
		out.println("</div>");
	}

	private void bottomText(PrintWriter out) {
		out.println("");

		out.println("         <div id='eric-multi'>");
		out.println("                       <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
