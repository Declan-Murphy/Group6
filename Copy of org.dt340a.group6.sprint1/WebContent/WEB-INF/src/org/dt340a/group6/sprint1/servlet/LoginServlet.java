package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	Connection connection = null;
	PreparedStatement loginStatement = null;
	PreparedStatement toDoStatement = null;
	ResultSet loginResultSet = null;
	ResultSet toDoResultSet = null;
	int sessionMaxTimeInMinutes = 10;

	public LoginServlet() {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPassWord");

		try {
			String loginQueryString = "SELECT username, password, userType FROM User WHERE username = ? and password=?";
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, userName);
			loginStatement.setString(2, userPass);
			loginResultSet = loginStatement.executeQuery();

			if (loginResultSet.next()) {
                Cookie userCookie = new Cookie("user", loginResultSet.getString(1));
                userCookie.setMaxAge(sessionMaxTimeInMinutes*60);
                response.addCookie(userCookie);
                HttpSession session = request.getSession(true);
				session.setAttribute("loggedUser", loginResultSet.getString(1));
				session.setAttribute("loggedPass", loginResultSet.getString(2));
				session.setAttribute("loggedType", loginResultSet.getString(3));

                
				if (loginResultSet.getString(3).equals("Support Engineer")) {
//					ServletContext context = getServletContext();
//					RequestDispatcher dispatcher = context.getRequestDispatcher("/supEngMenu.html");
//					dispatcher.forward(request, response);
					
					response.sendRedirect("supEngMenu.html");
				} else if (loginResultSet.getString(3).equals("Administrator")) {
//					ServletContext context = getServletContext();
//					RequestDispatcher dispatcher = context.getRequestDispatcher("/adminMenu.html");
//					dispatcher.forward(request, response);
					
					response.sendRedirect("adminMenu.html");
				} else if (loginResultSet.getString(3).equals("Customer Service")) {
//					ServletContext context = getServletContext();
//					RequestDispatcher dispatcher = context.getRequestDispatcher("/custSerRepMenu.html");
//					dispatcher.forward(request, response);
					
					response.sendRedirect("custSerRepMenu.html");
				} else if (loginResultSet.getString(3).equals("Network Engineer")) {
//					ServletContext context = getServletContext();
//					RequestDispatcher dispatcher = context.getRequestDispatcher("/netMgmtEngMenu.html");
//					dispatcher.forward(request, response);
					
					response.sendRedirect("netMgmtEngMenu.html");
				}

			} else {
				request.setAttribute("wrongUser", userName);

				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/fail");
				dispatcher.forward(request, response);

				System.out.println("Fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
