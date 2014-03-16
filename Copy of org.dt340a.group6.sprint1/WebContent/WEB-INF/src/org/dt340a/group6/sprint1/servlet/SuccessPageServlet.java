package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuccessPageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String username = (String) session.getAttribute("loggedUser");
		String password = (String) session.getAttribute("loggedPass");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	//	TODO none of this javascript worksworks
//		out.println("<script type='text/javascript'>");		
//		
//		out.println("function AreAnyCheckboxesChecked() {");
//		out.println("var form = document.getElementById('delete');");
//		out.println("var inputs = form.getElementsByTagName('input');");
//		out.println("var is_checked = false;");
//		out.println("for(var x = 0; x < inputs.length; x++) {");
//			out.println("    if(inputs[x].type == 'checkbox') {");
//		    	out.println("        is_checked = inputs[x].checked;");
//		        out.println("        if(is_checked) return true;");
//		out.println("}");
//		out.println("}");
//		out.println("alert('Please select something');");
//		out.println("return false;");
//		out.println("}");
//		out.println("</script>");
		
		out.println("<body bgcolor=#F3EEF0><h1>");
		out.println("<center>");
		out.println("Todo List Page For : " + username);
		out.println("</center>");
		out.println("</h1>");
				
		List<String> toDoList = (List<String>) session.getAttribute("loggedToDoList");
		if(toDoList.size() > 0){
			out.println("<form name='delete' action='http://localhost:8080/org.dt340a.group6.sprint1/delete' method='get'>");					
			for(int i=0; i < toDoList.size(); i++){
				out.println((i+1) + ". " + toDoList.get(i));
				out.println("<input type='checkbox' name='item"+i+"' value='"+toDoList.get(i)+"'><br>");	
				out.println("<br>");
			}
			out.println("<input type='submit' value='Delete Selected'>");
			out.println("</form>");
		}

		out.println("<br>");
		
	//	out.println("<form onsubmit='return validate(this)'>");
		
		out.println("<form name='insert' action='http://localhost:8080/org.dt340a.group6.sprint1/insert' method='post'>");
		out.println("Add Item: <input type='text' name='itemToAdd' required='required'>");
		out.println("<input type='submit' value='Add Item'>");
		out.println("</form>");
		out.println("<br>");
		out.println(" <a href='index.html'>Click Here To Logout</a>");

		//out.println("</p>");
		out.println("</body>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
