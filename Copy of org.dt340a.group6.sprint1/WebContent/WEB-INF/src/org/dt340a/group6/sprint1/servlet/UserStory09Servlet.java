package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;

public class UserStory09Servlet extends HttpServlet{
    
    String startTimeString, endTimeString;
    Date startDateTime, endDateTime;
    long time;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        time = System.nanoTime();
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
                
        startTimeString = req.getParameter("startdate");
        endTimeString = req.getParameter("enddate");
        System.out.println(startTimeString + "\t\t\t" + endTimeString);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");      
        try {
            startDateTime = sdf.parse(startTimeString);
            endDateTime = sdf.parse(endTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        topText(out);
        List<List> callFailures = PersistenceUtil.findCountBetweenTimesTotalDuration(startDateTime, endDateTime);
        
        if (callFailures!=null) {
            mid(out, callFailures);                         
        }
        else {
            out.println("</table>");
            out.println("<p class='wrapper button'>This query has returned no results.</p>");
        }
        bottomText(out);
      }

    protected void mid(PrintWriter out, List<List> callFailures) {
      SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      
      out.println("<tr class='alt'>");
      out.println("<th>From date:</th>");
      out.println("<td align='center'>" + dateFormatter.format(startDateTime) + "</td>");
      out.println("<th>To date:</th>");
      out.println("<td align='center'>" + dateFormatter.format(endDateTime) + "</td>");
      out.println("</tr>");
      out.println("<tr>");        
      out.println("<th>Imsi</th>");
      out.println("<th>Number of Call Failures</th>");
      out.println("<th>Total Call Failure Duration</th>");
      out.println("<th>Average Call Failure Duration</th>");
      out.println("</tr>");
      
      int count=0;
      for(List failure : callFailures){       
          if (count%2==0) {
              out.println("<tr>");
          }
          else {
              out.println("<tr class='alt'>");
          }
          out.println("<td align='center'>" + failure.get(0) + "</td>");
          out.println("<td align='center'>" + failure.get(1) + "</td>");
          out.println("<td align='center'>" + failure.get(2) + "</td>");
          out.println("<td align='center'>" + (long)failure.get(2)/(long)failure.get(1) + "</td>");
          out.println("</tr>");
          count++;                    
      }
  }
     
    protected void topText(PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Dt340a - Group 6</title>");
        out.println("<link rel='icon' type='image/ico' href='http://www.ericsson.com/favicon.ico'/>");
        out.println("<link rel='stylesheet' type='text/css' href='css/mystyle.css'>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='container'>");
        out.println("<div id='heading-container'>");
        out.println("<div id='eir-image'> ");
        out.println("<img alt='Ericsson' src='http://www.ericsson.com/shared/eipa/images/elogo.png'>   ");
        out.println("</div> ");
        out.println("<div id='dit-image'> ");
        out.println("<img alt='DIT' src='http://www.dit.ie/media/styleimages/dit_crest.gif' width='90px' height='90px'>  ");
        out.println("</div> ");
        out.println("<h1>Call Investigation Assistant</h1>");
        out.println("<h2>Group 6</h2>");
        out.println("<h3>Network Management Engineer</h3>   ");             
        out.println("<div class='wrapper'>");
        out.println("<form class='alignleft' method='GET' action='logout'><input class='button' type='submit' value='Logout'/></form>");
        out.println("<a href='netMgmtEngMenu.html'><button class='button alignright'>Home Page</button></a>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='wrapper' id='inner-container' >  ");
        out.println("<div><h3>Select the start date and end date to average over Call Failures:</h3></div>");
        out.println("<form method=GET action='us09Query'>");
        out.println("<strong>From (date and time):</strong><input class='submissionfield' type='datetime-local' name='startdate' value='2013-01-01T00:00' required='required'>");
        out.println("<strong>To (date and time):</strong><input class='submissionfield' type='datetime-local' name='enddate' value='2014-01-01T00:00' required='required'>");
        out.println("<input class='button' type='submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("<div id='inner-container'>");
        out.println("<table id='customers'>");
    }
    
    protected void bottomText(PrintWriter out) {
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div id='eric-multi'>");
        out.println("<img src='images/ebottomgrad.jpg'>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }


}