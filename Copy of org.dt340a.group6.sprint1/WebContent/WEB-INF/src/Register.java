
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dt340a.group6.sprint1.persistence.UserConfig;

import main.*;
import persistence.*;
import entity.*;



public class Register extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPassWord");
		String userRePass=request.getParameter("userRePassWord");
		String userType=request.getParameter("userType");
		
		out.println("<body bgcolor=#F3EEF0><h1>");
		out.println("<center>");
		HttpSession session=request.getSession(false);
		out.println(userName + " has been registered ");		
		out.println("<br>");
		//out.println("<a href='http://localhost:8080/org.dt340a.group6.sprint1/login?userName="+userName+"&userPassWord="+userPass+"&Submit=Submit'>Login</a>");	
		out.println("<a href='register.html'>Back</a>");	
		out.println("</center>");
		out.println("</h1></body>");
		
		UserConfig Persist = new UserConfig();
		Persist.createUser(userName,userPass, userType);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
