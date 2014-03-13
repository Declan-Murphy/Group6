import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.UserConfig;


public class Insert extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String username = (String) session.getAttribute("loggedUser");
		String password = (String) session.getAttribute("loggedPass");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String item;
		
		out.println("<body bgcolor=#F3EEF0><h1>");
		out.println("<center>");
		
		item=request.getParameter("itemToAdd");
		out.println("The user " + username + " has added " + item);
		
		out.println("<br>");	
		out.println("<a href='http://localhost:8080/org.dt340a.group6.sprint1/login?userName="+username+"&userPassWord="+password+"&Submit=Submit'>Back</a>");
		out.println(" <a href='index.html'>Click Here To Logout</a>");	
				
		UserConfig Persist = new UserConfig();
		Persist.createListItem(username,item);			

		out.println("</center>");
		out.println("</h1></body>");	
	}

}
