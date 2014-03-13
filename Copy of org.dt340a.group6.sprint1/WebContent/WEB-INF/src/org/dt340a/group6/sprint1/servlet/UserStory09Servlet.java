package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.entity.CallFailure;
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
            newmid(out, callFailures);                         
        }
        else {
            out.println("<p class='button'>This query has returned no results.</p>");
        }
        bottomText(out);
      }

    private void newmid(PrintWriter out, List<List> callFailures) {
      SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      
      out.println("                    <tr class='alt'>");
      out.println("                      <th>From date:</th>");
      out.println("                      <td align='center'>" + dateFormatter.format(startDateTime) + "</td>");
      out.println("                      <th>To date:</th>");
      out.println("                      <td align='center'>" + dateFormatter.format(endDateTime) + "</td>");
      out.println("                    </tr>");
      out.println("                    <tr>");        
      out.println("                      <th>Imsi</th>");
      out.println("                      <th>Number of Call Failures</th>");
      out.println("                      <th>Total Call Failure Duration</th>");
      out.println("                      <th>Average Call Failure Duration</th>");
      out.println("                    </tr>");
      
      int count=0;
      for(List failure : callFailures){       
          if (count%2==0) {
              out.println("                <tr>");
          }
          else {
              out.println("                <tr class='alt'>");
          }
          out.println("                      <td align='center'>" + failure.get(0) + "</td>");
          out.println("                      <td align='center'>" + failure.get(1) + "</td>");
          out.println("                      <td align='center'>" + failure.get(2) + "</td>");
          out.println("                      <td align='center'>" + (long)failure.get(2)/(long)failure.get(1) + "</td>");
          out.println("                    </tr>");
          count++;                    
      }
  }
    
    
    
    
    private void mid(PrintWriter out, List<CallFailure> callFailures) {
//        
        int imsiCount = 0;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        out.println("                    <tr class='alt'>");
        out.println("                      <th>From date:</th>");
        out.println("                      <td align='center'>" + dateFormatter.format(startDateTime) + "</td>");
        out.println("                      <th>To date:</th>");
        out.println("                      <td align='center'>" + dateFormatter.format(endDateTime) + "</td>");
        out.println("                    </tr>");
        out.println("                    <tr>");        
        out.println("                      <th>Count</th>");
        out.println("                      <th>Number of Call Failures</th>");
        out.println("                      <th>Total Call Failure Duration</th>");
        out.println("                      <th>Average Call Failure Duration</th>");
        out.println("                    </tr>");

//        String currentIMSI = callFailures.get(0).getiMSI();
        String currentIMSI = "";
        int failureCount = 0;
        int totalDuration = 0;
        
        int count=0;
        for(CallFailure failure : callFailures){
            Calendar cal = Calendar.getInstance();
//            cal =failure.getDateTime();
            
            if( failure.getDateTime().after(startDateTime) && failure.getDateTime().before(endDateTime)){
                if (!currentIMSI.equals("")) {
                    if (!currentIMSI.equals(failure.getiMSI())) {                   
                        if (count%2==0) {
                            out.println("                    <tr>");
                        }
                        else {
                            out.println("                    <tr class='alt'>");
                        }
                        out.println("                      <td align='center'>" + imsiCount + "</td>");
                        out.println("                      <td align='center'>" + currentIMSI + "</td>");
                        out.println("                      <td align='center'>" + failureCount + "</td>");
                        out.println("                      <td align='center'>" + totalDuration/failureCount + "</td>");
                        out.println("                    </tr>");
                        count++;
//                        
                        imsiCount++;             

                        failureCount=0;
                        totalDuration=0;
                    }
                }
                currentIMSI = failure.getiMSI();
                failureCount++;
                totalDuration += failure.getDuration();
            }
        }

        if (count%2==0) {
            out.println("                    <tr>");
        }
        else {
            out.println("                    <tr class='alt'>");
        }
        out.println("                      <td align='center'>" + imsiCount + "</td>");
        out.println("                      <td align='center'>" + currentIMSI + "</td>");
        out.println("                      <td align='center'>" + failureCount + "</td>");
        out.println("                      <td align='center'>" + totalDuration/failureCount + "</td>");
        out.println("                    </tr>");
//      
        imsiCount++;
        out.println("The total number of IMSIs displayed is " + imsiCount);
    }
    
    private void topText(PrintWriter out) {
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
        out.println("                <h3>Network Management Engineer View</h3>");
        out.println("            </div>");
        out.println("            <div id='inner-container' >  ");
        out.println("              <div><h4>Select the start date and end date to average over Call Failures:</h4></div>");
        out.println("              <form method=GET action='us09Query'>");
        out.println("                <input class='submissionfield' type='datetime-local' name='startdate' required='required'>");
        out.println("                <input class='submissionfield' type='datetime-local' name='enddate' required='required'>");
        out.println("                <input type='submit'>");
        out.println("                <input Type='button' VALUE='Back' onClick='history.go(-1);return true;'>");
        out.println("              </form>");
        out.println("            </div>");
        out.println("            <div id='inner-container'>");
        out.println("                <table id='customers'>");
    }
    
    private void bottomText(PrintWriter out) {
        out.println("");
        out.println("                </table>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("         <div id='eric-multi'>");
        out.println("                       <img src='images/ebottomgrad.jpg' >");
        out.println("         </div>");
        out.println("    </body>");
        out.println("</html>");
    }


}