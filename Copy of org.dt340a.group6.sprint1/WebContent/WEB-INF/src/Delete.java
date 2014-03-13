import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.UserConfig;


public class Delete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String username = (String) session.getAttribute("loggedUser");
		String password = (String) session.getAttribute("loggedPass");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String item;
		
		out.println("<body bgcolor=#F3EEF0><h1>");
		out.println("<center>");
		
		Enumeration e = request.getParameterNames();
		if(!e.hasMoreElements()){
			out.println("You Did not select anything to be deleted please go back and try again: " );
		}
		else{
			while(e.hasMoreElements()){
				item=request.getParameter((String) e.nextElement());
				out.println("<br>");	
				out.println("Item deleted: " + item);
	
				UserConfig Persist = new UserConfig();
				Persist.removeListItem(username,item);
			}		
		}
		out.println("<br>");		
		out.println("<a href='http://localhost:8080/org.dt340a.group6.sprint1/login?userName="+username+"&userPassWord="+password+"&Submit=Submit'>Back</a>");
		out.println(" <a href='index.html'>Click Here To Logout</a>");	
		
		out.println("</center>");
		out.println("</h1></body>");
	
	}

}
