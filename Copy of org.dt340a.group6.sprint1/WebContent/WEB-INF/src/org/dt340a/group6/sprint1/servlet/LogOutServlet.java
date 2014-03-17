package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutServlet extends HttpServlet {
   
    private static final long serialVersionUID = 3692214945551760969L;
    String userName;
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();      

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                String logOutMsg = "Goodbye "+cookie.getValue()+", please come again.";
//                out.println("<script>alert(\""+logOutMsg+"\");window.location.replace(\"index.html\");</script>");
                cookie.setMaxAge(0);
                res.addCookie(cookie);
//                res.sendRedirect("index.html");
            }
        }
    }