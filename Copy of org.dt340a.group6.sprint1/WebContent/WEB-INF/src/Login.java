import org.dt340a.group6.sprint1.servlet.ConnectionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	Connection connection = null;
	PreparedStatement loginStatement = null;
	PreparedStatement toDoStatement = null;
	ResultSet loginResultSet = null;
	ResultSet toDoResultSet = null;

	public Login() {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPassWord");

		try {
			String loginQueryString = "SELECT username, password, userType FROM user WHERE username = ? and password=?";
			// String loginQueryString =
			// "select distinct e.model, c.cause_eventId, c.cause_causeCode, count(*) from equipment e, callfailure c where c.equipment_tac=e.tAC and e.model='vea3' group by c.cause_eventId, c.cause_causeCode;";
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, userName);
			loginStatement.setString(2, userPass);
			loginResultSet = loginStatement.executeQuery();

			if (loginResultSet.next()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("loggedUser", loginResultSet.getString(1));
				session.setAttribute("loggedPass", loginResultSet.getString(2));
				session.setAttribute("loggedType", loginResultSet.getString(3));

				// System.out.print("=-------------------------Query Result ---------"
				// + loginResultSet.getString(1));
				// System.out.print(loginResultSet.getString(2));
				// System.out.print(loginResultSet.getString(3));
				// System.out.print(loginResultSet.getString(4));

				// Now run a query to get their todo list
				// String toDoQueryString =
				// "SELECT username, password,  itemContent FROM user, listItem WHERE username = ? and password=? and user.id = listItem.user_id";
				// toDoStatement = connection.prepareStatement(toDoQueryString);
				// toDoStatement.setString(1,userName);
				// toDoStatement.setString(2,userPass);
				// toDoResultSet = toDoStatement.executeQuery();
				// List<String> toDoList = new ArrayList<String>();
				// This will add all list items for current user
				// while(toDoResultSet.next()){
				// toDoList.add( toDoResultSet.getString(3) );
				// }
				// session.setAttribute("loggedToDoList", toDoList);

				// ServletContext context=getServletContext();
				// RequestDispatcher
				// dispatcher=context.getRequestDispatcher("/success");
				// dispatcher.forward(request, response);
				if (loginResultSet.getString(3).equals("Support Engineer")) {
					ServletContext context = getServletContext();
					RequestDispatcher dispatcher = context
							.getRequestDispatcher("/supEngMenu.html");
					dispatcher.forward(request, response);
				} else if (loginResultSet.getString(3).equals("Administrator")) {
					ServletContext context = getServletContext();
					RequestDispatcher dispatcher = context
							.getRequestDispatcher("/adminMenu.html");
					dispatcher.forward(request, response);
				} else if (loginResultSet.getString(3).equals(
						"Customer Service")) {
					ServletContext context = getServletContext();
					RequestDispatcher dispatcher = context
							.getRequestDispatcher("/custSerRepMenu.html");
					dispatcher.forward(request, response);
				} else if (loginResultSet.getString(3).equals(
						"Network Engineer")) {
					ServletContext context = getServletContext();
					RequestDispatcher dispatcher = context
							.getRequestDispatcher("/netMgmtEngMenu.html");
					dispatcher.forward(request, response);
				}

			} else {
				request.setAttribute("wrongUser", userName);

				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context
						.getRequestDispatcher("/fail");
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
