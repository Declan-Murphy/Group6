package org.dt340a.group6.sprint1.servlet;
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FailureServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//
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
		out.println("                <h3>Customer Service Representative View</h3>");
		
		out.println("            <div id='inner-container' >  ");
		out.println("<center>");
		out.println("<h3>Login fail<h3><br> ");
		out.println(" <a href='index.html'><button class='button'>Try Again</button></a>");	
		out.println("</center>");
		out.println("         </div>");
		out.println("         <div id='eric-multi'>");
		out.println("              <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
		//

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}