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
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Equipment;

public class ModelQueryServlet extends HttpServlet{
	
	String model;
	String startTimeString;
	String endTimeString;
	
	Date startDate;
	Date endDate;
	
	
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();		
		model = req.getParameter("model");
		startTimeString = req.getParameter("starttime");
		endTimeString = req.getParameter("endtime");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");		
		try {
			startDate = sdf.parse(startTimeString);
			endDate = sdf.parse(endTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		System.out.println("startTime:--" + startDate);
				
		List<Equipment> equipment =PersistenceUtil.findEquipmentByModel(model);
		
		topText(out);
		
//		if (PrimitiveCheck.isString(model)) {
			if (equipment == null) {
				noResultsFound(out);
			}
			else {
				mid(out, equipment);			
			}
//		}
//		else {
//			notAValidImsi(out);
//		}
		
		bottomText(out);		
	}
	
	private void notAValidImsi(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Warning Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>The value '" + model + "' is not a valid Model. Please enter a valid Model.</td>");
		out.println("                    </tr>");
	}
	
	private void noResultsFound(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>This search has returned 0 results for Model: " + model + " </td>");
		out.println("                    </tr>");
    }
	
	private void mid(PrintWriter out, List<Equipment> equipment) {
		int count=0;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        out.println("                    <tr class='alt'>");
        out.println("                      <th>From date:</th>");
        out.println("                      <td align='center'>" + dateFormatter.format(startDate) + "</td>");
        out.println("                      <th>To date:</th>");
        out.println("                      <td align='center'>" + dateFormatter.format(endDate) + "</td>");
        out.println("                    </tr>");
		out.println("                    <tr>");		
		out.println("                      <th>Model</th>");
        out.println("                      <th>TAC</th>");
        out.println("                      <th>Total failures</th>");
        out.println("                      <th>Fails withing date range</th>");
		out.println("                    </tr>");
		for(Equipment equip : equipment){
			
			if (count%2==0) {
				out.println("                    <tr>");
			}
			else {
					out.println("                    <tr class='alt'>");
			}
				out.println("                      <td>" + equip.getModel()+ "</td>");
                out.println("                      <td>" + equip.gettAC() + "</td>");
                
                //List<CallFailure> callFailures = PersistenceUtil.findCallFailureByTAC(equip.gettAC());
                List<CallFailure> callFailures = PersistenceUtil.findCallFailureByTACInTime(equip.gettAC(),startDate,endDate);
                if(callFailures != null){
                	out.println("                      <td>" + callFailures.size() + "</td>");
                	//int validCount=0;
                	//for(CallFailure cf : callFailures ){
                		//if( cf.getDateTime().after(startDate) && cf.getDateTime().before(endDate)){
                		//	validCount++;
                		//}
               		
                	//}
                	//out.println("                      <td>" + validCount + "</td>");
                	out.println("                      <td>" + callFailures.size() + "</td>");
                }
                else{
                	out.println("                      <td>" + 0 + "</td>");
                	out.println("                      <td>" + 0 + "</td>");
                }
				out.println("                    </tr>");
			
			
			count++;
		}

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
		out.println("                <h3>Support Engineer</h3>");
        out.println("                <div class='wrapper'>");
        out.println("                    <form class='alignleft' method='GET' action='logout'><input class='button' type='submit' value='Logout'/></form>  ");              
        out.println("                    <a href='netMgmtEngMenu.html'><button class='button alignright'>Home Page</button></a>");
        out.println("                </div>");
		out.println("            </div>");
		out.println("            <div id='inner-container' >  ");
		out.println("            <form method=GET action='us08Query'>");
		out.println("               <input class='submissionfield' type='text' name='model' placeholder='Please Enter a Model Name Here' required='required' ><br>");
		out.println("                Start (date and time): <input type='datetime-local' name='starttime'  value='2013-01-01T00:00' required='required'><br> ");
		out.println("                 End (date and time): <input type='datetime-local' name='endtime'  value='2014-01-01T00:00' required='required'> <br>");
		out.println("            <center><input class='button' type='submit'></center>");
		out.println("            </form>");
		out.println("            </div>");
		out.println("            <div id='inner-container'>");
		out.println("                <table id='customers'>");
	}
	
	private void bottomText(PrintWriter out) {
		out.println("");
		out.println("                </table>");
		out.println("            </div>");
		out.println("            <div class='wrapper'>");
		out.println("            <a href='supEngMenu.html'><button class='button centre'>Back</button></a>");
		out.println("            </div>");		
		out.println("        </div>");
		out.println("         <div id='eric-multi'>");
		out.println("                       <img src='images/ebottomgrad.jpg' >");
		out.println("         </div>");
		out.println("    </body>");
		out.println("</html>");
	}


}