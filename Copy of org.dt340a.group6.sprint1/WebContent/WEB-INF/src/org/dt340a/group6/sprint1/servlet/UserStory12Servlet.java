package org.dt340a.group6.sprint1.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.query.UserStory12Object;
import org.dt340a.group6.sprint1.query.UserStory12Query;

/**
 * Servlet implementation class User12Servlet
 */
public class UserStory12Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Date startDate, endDate;
    int count=0;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		
		try {
			startDate = sdf.parse(request.getParameter("startDateTime"));
			endDate = sdf.parse(request.getParameter("endDateTime"));
		} catch (ParseException e) { e.printStackTrace();}
		
		
		String currentStory = request.getParameter("userStoryNumber");
		
		UserStory12Query query = new UserStory12Query();
		List<CallFailure> resultsList = null;
		resultsList = query.findAllBetween(startDate, endDate);
		
		beginHTMLPrintout(out, currentStory);
		
		if(startDate.after(endDate)){
			notValidDateRange(out);
		}
		else{
			if (resultsList == null) {
				noResultsFound(out);
			}
			else {
				ArrayList<UserStory12Object> resultsWanted = new ArrayList<>();
				String currentImsi = resultsList.get(0).getiMSI();
				int count = 0;
				for(CallFailure cf : resultsList){
					if(!cf.getiMSI().equals(currentImsi)){
						resultsWanted.add(new UserStory12Object(cf.getiMSI(), count));
						count = 0;
					}
					else{
						count++;
					}
					currentImsi = cf.getiMSI();
				}
				
				sortTheArrayListOfUserStory12Objects(resultsWanted);
				
				queryTableUS12(out, resultsWanted);
			}
		}
		
		endHTMLPrintout(out);
	}

	private void sortTheArrayListOfUserStory12Objects(ArrayList<UserStory12Object> resultsWanted) {
		Collections.sort(resultsWanted, new Comparator<UserStory12Object>() {
		    @Override
		    public int compare(UserStory12Object uso1, UserStory12Object uso2) {
		        if (uso1.getCount() < uso2.getCount())
		            return 1;
		        if (uso1.getCount() > uso2.getCount())
		            return -1;
		        return 0;
		    }
		});
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	/**
	 * Print out the results for user story 6 in a table
	 * @param out
	 * @param results
	 */
	private void queryTableUS12(PrintWriter out, ArrayList<UserStory12Object> results) {
		queryTableHeadersUS12(out);
		
		for(UserStory12Object uso : results){
			if (count%2-1==0) {
				out.println("                    <tr>");
                fillRowUS12(out, uso);
			}
			else {
				out.println("                    <tr class='alt'>");
				fillRowUS12(out, uso);
			}
			count++;
			if(count >= 10){
				break;
			}
		}
	}
	
	/**
	 * Fill in the two columns for the row <br> (for user story 6)
	 * @param out PrintWriter
	 * @param fail CallFailure
	 */
	private void fillRowUS12(PrintWriter out, UserStory12Object uso) {
        out.println("                      <td>" + (1+count) + "</td>");
        out.println("                      <td>" + uso.getImsi() + "</td>");
		out.println("                      <td>" + uso.getCount() + "</td>");
		out.println("                    </tr>");
	}
	
	/**
	 * HTML printout for the headings of the results table for user story 6
	 * @param out
	 */
	private void queryTableHeadersUS12(PrintWriter out) {
//		out.println("                    <tr class='alt'>");
//		out.println("                      <td> </td>");
//        out.println("                      <td> </td>");
//		out.println("                    </tr>");
		out.println("                    <tr>");
        out.println("                      <th>#</th>");
        out.println("                      <th>IMSI</th>");
        out.println("                      <th>Number of Failures</th>");
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
	 * @param out PrintWriter
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
		out.println("                <h3>Network Management Engineer</h3>");             
        out.println("                <div class='wrapper'>");
        out.println("                   <form class='alignleft' method='GET' action='logout'><input class='button' type='submit' value='Logout'/></form>");                
        out.println("                   <a href='netMgmtEngMenu.html'><button class='button alignright'>Home Page</button></a>");
        out.println("                </div>");
	}
	
	/**
	 * Create the back button & container and start the results table
	 * @param out PrintWriter
	 */
	private void startTable(PrintWriter out) {
        out.println("            <div class='wrapper' id='inner-container' >");
        out.println("            <div><h3>Please select the date and time range to query for Top 10 IMSIs that had call failures:</h3></div>");           
        out.println("            <form method=GET action='UserStory12Servlet'>");
        out.println("            <input type='hidden' name='userStoryNumber' value='us12'>");
        out.println("                    <strong>From (date and time):</strong> <input class='submissionfield' type='datetime-local' value='2013-01-01T00:00' name='startDateTime'>");
        out.println("                   <strong>To (date and time):</strong> <input class='submissionfield' type='datetime-local' value='2014-01-01T00:00' name='endDateTime'>");
        out.println("            <input class='button' type='submit'>");
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
	
	/**
	 * If no results are found call this method to print the informational HTML
	 * @param out PrintWriter
	 */
	private void noResultsFound(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>This search has returned 0 results for Time Range selected</td>");
		out.println("                    </tr>");
    }	
	
	/**
	 * If no results are found call this method to print the informational HTML
	 * @param out PrintWriter
	 */
	private void notValidDateRange(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>Date Range is invalid</td>");
		out.println("                    </tr>");
    }	
}
