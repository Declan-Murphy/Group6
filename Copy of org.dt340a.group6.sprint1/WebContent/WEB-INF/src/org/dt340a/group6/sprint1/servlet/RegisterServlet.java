package org.dt340a.group6.sprint1.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dt340a.group6.sprint1.entity.User;
import org.dt340a.group6.sprint1.persistence.*;


public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPassWord");
		String userRePass = request.getParameter("userRePassWord");
		String userType = request.getParameter("userType");
		
		HttpSession session = request.getSession(false);
		
		User user = null;
		user = PersistenceUtil.findUserByUsername(userName);
		
		genericHeader(out);
		startBody(out);
//		startContainer(out);
		
		
		if(user != null){
			response.sendRedirect("RegistrationFailed.html");
		}
		else if(!userPass.equals(userRePass)){
			passwordMisMatch(out);
			response.sendRedirect("PasswordMisMatchRegistrationFailed.html");
		}
		else{
			
			UserConfig Persist = new UserConfig();
			Persist.createUser(userName, userPass, userType);
	        out.println("<div class='wrapper' id='inner-container'>");			
			out.println("<p>User " + userName + " has been created</p>");
	        out.println("</div>");
	        formStuff(out);
			endHTMLPrintout(out);
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
        out.println("                <h3>System Administrator</h3>");
        out.println("                <div class='wrapper'>");
        out.println("                   <form class='alignleft' method='GET' action='logout'><input class='button' type='submit' value='Logout'/></form>");                
        out.println("                   <a href='adminMenu.html'><button class='button alignright'>Home Page</button></a>");
        out.println("                </div>");
        out.println("            </div>");
	}
	
	/**
	 * Create the back button & container and start the results table
	 * @param out PrintWriter
	 */
	private void startContainer(PrintWriter out) {
		out.println("            <div id='inner-container' >  ");
		out.println("            <form method=GET action='imsiQuery'>");
		out.println("               <input Type='button' VALUE='Back' onClick='history.go(-2);return true;'>");
		out.println("            </form>");
		out.println("            </div>");
		out.println("            <div id='inner-container'>");
	}
	
	private void userNotExists(PrintWriter out){
		out.println("            <form name=login method='get' action='register'><center>");
		out.println("            <p style='font: x-large;'>Unfortunately, a user with that name is taken.</p>");
	}
	
	private void passwordMisMatch(PrintWriter out){
		out.println("            <form name=login method='get' action='register'><center>");
		out.println("            <p style='font: x-large;'>Unfortunately, your passwords did not match.</p>");
	}
	
	private void formStuff(PrintWriter out){
	    out.println("            <div class='wrapper' id='inner-container'>");
	    
		out.println("                <form name=login method='get' action='register' onSubmit='return checkform(this)'>");
		out.println("                <center>");		
		out.println("                <table border=1>");
		out.println("                <tr>");
		out.println("                	<td>Enter Your Name :</td>");
		out.println("                	<td><input type='text' name='userName' value='' required='required'></td>");
		out.println("                </tr>");
		out.println("                <tr>");
		out.println("                	<td>Enter Your Password :</td>");
		out.println("                	<td><input type='password' name='userPassWord' value=''	required='required'></td>");
		out.println("                </tr>");
		out.println("                <tr>");
		out.println("                	<td>Confirm Your Password :</td>");
		out.println("                	<td><input type='password' name='userRePassWord' value='' required='required'></td>");
		out.println("                </tr>");
		out.println("                <tr>");
		out.println("                <td>Enter Your User type :</td>");
		out.println("                <td>");
		out.println("                	<select name='userType'>");
		out.println("                		<option value='Support Engineer'>Support Engineer</option>");
		out.println("                		<option value='Customer Service'>Customer Service</option>");
		out.println("                		<option value='Network Engineer'>Network Engineer</option>");
		out.println("                	</select>");
		out.println("                </td>");
		out.println("                </tr>");
		out.println("                <tr>");
		out.println("                	<td align=center><input type='reset' name='reset'");
		out.println("                		value='Refresh' class='button'></td>");
		out.println("                	<td align=center><input type='submit' name='Submit'");
		out.println("                		value='Submit' class='button'></td>");
	}
	
	/**
	 * Finishes off the table for the results, prints the bottom grad, closes the html tags
	 * @param out PrintWriter
	 */
	private void endHTMLPrintout(PrintWriter out) {
		out.println("                </table>");
		out.println("            </center>");
		out.println("            </form>");
		out.println("            </div>");
		out.println("         <div id='eric-multi'>");
		out.println("              <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
	}
}