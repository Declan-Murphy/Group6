package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.entity.Equipment;
import org.dt340a.group6.sprint1.entity.FailureClass;
import org.dt340a.group6.sprint1.validation.PrimitiveCheck;

public class ModelEventCauseQueryServlet extends HttpServlet{
	
	String model;
	int tac;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();		
		model = req.getParameter("username");
		
		try{
			tac = PersistenceUtil.findEquipmentByModel(model).get(0).gettAC();
		}catch(Exception e){
			
			tac = 0;
		}
		
		List<CallFailure> callFailures = PersistenceUtil.groupCallFailureByTAC(tac);
	
		topText(out);
		
			if (callFailures == null) {
				noResultsFound(out);
			}
			else {
				mid(out, callFailures);			
			}
		
		bottomText(out);		
	}
	
	
	private void noResultsFound(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>This search has returned 0 results for Model: " + model + " </td>");
		out.println("                    </tr>");
    }
	
	private void mid(PrintWriter out, List<CallFailure> callFailures) {
		int count=0;
		out.println("                    <tr class='alt'>");
		out.println("                      <td>Model:</td>");
        out.println("                      <td>"+model+"</td>");

		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <th>Number of Times Occured</th>");
		out.println("                      <th>Event ID</th>");
        out.println("                      <th>Cause Code</th>");
        out.println("                      <th>Description</th>");
		out.println("                    </tr>");
		for(CallFailure fail : callFailures){
			
			List<CallFailure> numberEventIDCause = PersistenceUtil.countCauseCode(tac, fail.getCause().getCauseCode(), fail.getCause().getEventId());		
			
			if (count%2==0) {
				out.println("                    <tr>");
				
				out.println("                      <td>" + numberEventIDCause.get(0) + "</td>");
				out.println("                      <td>" + (int)fail.getCause().getEventId() + "</td>");
                out.println("                      <td>" + (int)fail.getCause().getCauseCode() + "</td>");
                out.println("                      <td>" + fail.getCause().getDescription() + "</td>");
				out.println("                    </tr>");
			}
			else {
				out.println("                    <tr class='alt'>");
				
				out.println("                      <td>" + numberEventIDCause.get(0) + "</td>");
				out.println("                      <td>" + (int)fail.getCause().getEventId() + "</td>");
				out.println("                      <td>" + (int)fail.getCause().getCauseCode() + "</td>");
                out.println("                      <td>" + fail.getCause().getDescription() + "</td>");
				out.println("                    </tr>");
			}
			count++;
		}
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
		out.println("                     <img alt='DIT' src='http://www.dit.ie/media/styleimages/dit_crest.gif' width='90px' height='90px'>  ");
		out.println("                </div> ");
		out.println("                <h1>Call Investigation Assistant</h1>");
		out.println("                <h2>Group 6</h2>");
		out.println("                <h3>Network Management Engineer</h3>");
		out.println("            </div>");
		out.println("            <div id='inner-container' >  ");
		out.println("            <h3> Find the number of occurrence of event ID's and cause codes by a given Model </h3>");		
		out.println("            <form method=GET action='modelQuery'>");
		out.println("               <input class='submissionfield' type='text' name='username' placeholder='Please Enter a Model Here' required >");
		out.println("               <input type='submit'>");
		out.println("               <input Type='button' VALUE='Back' onClick='history.go(-2);return true;'>");
		out.println("            </form>");
		out.println("            </div>");
		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
	}
	
	private void bottomText(PrintWriter out) {
		out.println("");
		out.println("                </table>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("         <div id='eric-multi'>");
		out.println("                       <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
	}


}