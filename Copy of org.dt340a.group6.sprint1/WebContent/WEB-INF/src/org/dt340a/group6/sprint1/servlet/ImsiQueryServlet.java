package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.query.IMSIQuery;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.validation.PrimitiveCheck;

public class ImsiQueryServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	String imsi;
	
	/**
	 * GET method for ImsiQueryServlet
	 * <br>Takes the request from the form in HTML and sends a response through a print writer
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		imsi = request.getParameter("imsi");
		String currentStory = request.getParameter("userStoryNumber");
		
		IMSIQuery query = new IMSIQuery();
		List<CallFailure> callFailures = query.viewInfoForIMSI(imsi);
		
		beginHTMLPrintout(out, currentStory);
		if (PrimitiveCheck.isLong(imsi)) {
			if (callFailures == null) {
				noResultsFound(out);
			}
			else {
				queryReplyPrintout(out, currentStory, callFailures);
			}
		}
		else {
			notAValidImsi(out);
		}
		endHTMLPrintout(out);		
	}
	
	/**
	 * Generate the HTML for the query results
	 * @param out PrintWriter
	 * @param currentStory String
	 * @param callFailures List CallFailure
	 */
	private void queryReplyPrintout(PrintWriter out, String currentStory, List<CallFailure> callFailures) {
		if(currentStory.equals("us4")){
			userStory4QueryResult(out, callFailures);
		}
		else if(currentStory.equals("us6")){
			userStory6QueryResult(out, callFailures);
		}
		else{
			out.println("Something went horribly wrong");
		}
	}
	
	/**
	 * Generate HTML for user story 6 results
	 * @param out PrintWriter
	 * @param callFailures List CallFailure
	 */
	private void userStory6QueryResult(PrintWriter out, List<CallFailure> callFailures) {
		ArrayList<CallFailure> alreadySeenCallFailureCodes = findSingleInstanceOfEachCauseCode(callFailures);
		queryTableUS6(out, alreadySeenCallFailureCodes);
	}
	
	/**
	 * Create an ArrayList of CallFailures with no duplicates of Cause Codes
	 * @param callFailures List of CallFailures
	 * @return ArrayList of CallFailures
	 */
	public static ArrayList<CallFailure> findSingleInstanceOfEachCauseCode(List<CallFailure> callFailures) {
		Boolean found = false;
		ArrayList<CallFailure> alreadySeenCallFailureCodes = new ArrayList<CallFailure>();
		alreadySeenCallFailureCodes.add(callFailures.get(0));
		for(CallFailure currentCallFailure : callFailures){
			for(CallFailure currentCF : alreadySeenCallFailureCodes){
				if(currentCallFailure.getCause().getCauseCode() == currentCF.getCause().getCauseCode()){
					found = true;
					break;
				}
			}
			if(!found){
				alreadySeenCallFailureCodes.add(currentCallFailure);
			}
			found = false;
		}
		return alreadySeenCallFailureCodes;
	}
	
	/**
	 * If not a valid IMSI call this method to print the informational HTML
	 * @param out PrintWriter
	 */
	private void notAValidImsi(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Warning Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>The value '" + imsi + "' is not a valid IMSI. Please enter a valid IMSI.</td>");
		out.println("                    </tr>");
	}
	
	/**
	 * If no results are found call this method to print the informational HTML
	 * @param out PrintWriter
	 */
	private void noResultsFound(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>This search has returned 0 results for IMSI: " + imsi + " </td>");
		out.println("                    </tr>");
    }
	
	/**
	 * Print out the results for user story 4 in a table
	 * @param out
	 * @param callFailures
	 */
	private void userStory4QueryResult(PrintWriter out, List<CallFailure> callFailures) {
		int count=0;
		queryTableHeadersUS4(out);
		for(CallFailure fail : callFailures){
			if (count%2==0) {
				out.println("                    <tr>");
				fillRowUS4(out, fail);
			}
			else {
				out.println("                    <tr class='alt'>");
				fillRowUS4(out, fail);
			}
			count++;
		}
	}
	
	/**
	 * Fill in the two columns for the row <br> (for user story 4)
	 * @param out PrintWriter
	 * @param fail CallFailure
	 */
	private void fillRowUS4(PrintWriter out, CallFailure fail) {
		out.println("                      <td>" + (int)fail.getCause().getEventId() + "</td>");
		fillRowUS6(out, fail);
	}
	
	/**
	 * HTML printout for the headings of the results table for user story 4
	 * @param out PrintWriter
	 */
	private void queryTableHeadersUS4(PrintWriter out) {
		out.println("                    <tr class='alt'>");
		out.println("                      <td>IMSI Number:</td>");
        out.println("                      <td>"+imsi+"</td>");
        out.println("                      <td></td>");
		out.println("                    </tr>");
		out.println("                    <tr>");		
		out.println("                      <th>Event ID</th>");
        out.println("                      <th>Cause Code</th>");
        out.println("                      <th>Description</th>");
		out.println("                    </tr>");
	}
	
	/**
	 * Print out the results for user story 6 in a table
	 * @param out
	 * @param callFailures
	 */
	private void queryTableUS6(PrintWriter out, List<CallFailure> callFailures) {
		int count=0;
		queryTableHeadersUS6(out);
		for(CallFailure fail : callFailures){
			if (count%2-1==0) {
				out.println("                    <tr>");
                fillRowUS6(out, fail);
			}
			else {
				out.println("                    <tr class='alt'>");
				fillRowUS6(out, fail);
			}
			count++;
		}
	}
	
	/**
	 * Fill in the two columns for the row <br> (for user story 6)
	 * @param out PrintWriter
	 * @param fail CallFailure
	 */
	private void fillRowUS6(PrintWriter out, CallFailure fail) {
		out.println("                      <td>" + (int)fail.getCause().getCauseCode() + "</td>");
		out.println("                      <td>" + fail.getCause().getDescription() + "</td>");
		out.println("                    </tr>");
	}
	
	/**
	 * HTML printout for the headings of the results table for user story 6
	 * @param out
	 */
	private void queryTableHeadersUS6(PrintWriter out) {
		out.println("                    <tr class='alt'>");
		out.println("                      <td>IMSI Number:</td>");
        out.println("                      <td>"+imsi+"</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
        out.println("                      <th>Cause Code</th>");
        out.println("                      <th>Description</th>");
		out.println("                    </tr>");
	}
	
	/**
	 * Begin the HTML printout for the page <br> (up until the result display section)
	 * @param out PrintWriter
	 * @param currentStory String
	 */
	private void beginHTMLPrintout(PrintWriter out, String currentStory) {
		genericHeader(out);
		startBody(out);
		if(currentStory.equals("us4")){
			out.println("                <h4>User Story 4</h4>");
			out.println("                <h4> As Customer Service Rep. I want to display, for a given affected IMSI, <br>the Event ID and Cause Code for any / all failures affecting that IMSI</h4>");
		}
		else if(currentStory.equals("us6")){
			out.println("                <h4>User Story 6</h4>");
			out.println("                <h4>As a customer service rep, I want to see, for a given IMSI, <br>all the unique cause codes associated with its call failures.</h4>");
		}
		out.println("            </div>");
		startTable(out);
	}
	
	/**
	 * Start the html tag <br> Fill out the generic head tag for html
	 * @param out
	 */
	private void genericHeader(PrintWriter out) {
		out.println("<html>");
		out.println("    <head>");
		out.println("       <title>Dt340a - Group 6</title>");
		out.println("      <link rel='icon' type='image/ico' href='http://www.ericsson.com/favicon.ico'/>");
		out.println("        <link rel='stylesheet' type='text/css' href='css/mystyle.css'>");
		out.println("        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("    </head>");
	}
	
	/**
	 * Starts the generic body of the html
	 * @param out
	 */
	private void startBody(PrintWriter out) {
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
		out.println("                <h3>Customer Service Representative View</h3>");
	}
	
	/**
	 * Create the back button & container and start the results table
	 * @param out PrintWriter
	 */
	private void startTable(PrintWriter out) {
		out.println("            <div id='inner-container' >  ");
		out.println("            <form method=GET action='imsiQuery'>");
		out.println("               <input Type='button' VALUE='Back' onClick='history.go(-2);return true;'>");
		out.println("            </form>");
		out.println("            </div>");
		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
	}
	
	/**
	 * Finishes off the table for the results, prints the bottom grad, closes the html tags
	 * @param out PrintWriter
	 */
	private void endHTMLPrintout(PrintWriter out) {
		out.println("");
		out.println("                </table>");
		out.println("            </div>");
		out.println("         </div>");
		out.println("         <div id='eric-multi'>");
		out.println("              <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
	}

}