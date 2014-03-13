package org.dt340a.group6.sprint1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.validation.PrimitiveCheck;

@WebServlet("/ListIMSIs")
public class ListIMSIs extends HttpServlet {

	private static final long serialVersionUID = -4182847637670709900L;
	String fromText;
	String toText;
	Date from;
	Date to;

	public ListIMSIs() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		fromText = request.getParameter("from");
		toText = request.getParameter("to");
		

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			from = sdf.parse(fromText);
			to = sdf.parse(toText);
		} catch (java.text.ParseException exception) {
			exception.printStackTrace();
		}

		topText(out);

		printMain(out);

		bottomText(out);
	}

	private void printMain(PrintWriter out) {
		// if (!PrimitiveCheck.isLong(imsi)) {
		// notAValidImsi(out);
		// return;
		// }

		List<CallFailure> callFailures = PersistenceUtil.findAllBetween(from,to);

		if (callFailures.isEmpty()) {
			noResultsFound(out);
			return;
		}

		midText(out, callFailures);
	}

	// private void notAValidImsi(PrintWriter out) {
	// out.println("                    <tr>");
	// out.println("                      <td>Warning Message:</td>");
	// out.println("                    </tr>");
	// out.println("                    <tr>");
	// out.println("                      <td>The value '" + imsi
	// + "' is not a valid IMSI. Please enter a valid IMSI.</td>");
	// out.println("                    </tr>");
	// }

	private void noResultsFound(PrintWriter out) {
		out.println("                    <tr>");
		out.println("                      <td>Information Message:</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                      <td>The search for this time period has returned 0 results. </td>");
		out.println("                    </tr>");
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
		out.println("                <h3>Support Engineer View</h3>");
		out.println("            </div>");
		out.println("            <div id='inner-container' >  ");
		out.println("<p>List all IMSIs that have failures between these times.</p>");
		out.println("		<form method=GET action='ListIMSIs'>			"
				+ "From (date and time): <input type='datetime-local'			"
				+ "	name='from'> To (date and time): <input		"
				+ "		type='datetime-local' name='to'> <input type='submit'>	"
				+ "	</form>");
		out.println("            </div>");
		out.println("            <div id='inner-container'>");
	}

	private void midText(PrintWriter out, List<CallFailure> callFailures) {

		out.println("                <table id='selectedTime'>");
		out.println("                    <tr>");
		out.println("                      <td>From: </td>");
		out.println("                      <td>" + from + "</td>");
		out.println("                    </tr>");
		out.println("                    <tr class='alt'>");
		out.println("                      <td>To:</td>");
		out.println("                      <td>" + to + "</td>");
		out.println("                    </tr>");
		out.println("                </table>");
		out.println("                <p></p>");
		
		out.println("                <table id='customers'>");
		out.println("                    <tr>");
		out.println("                      <th>IMSI Number</th>");
		out.println("                      <th>Time</th>");
		out.println("                      <th>Failure Reason</th>");
		out.println("                    </tr>");

		int count = 0;
		for (CallFailure fail : callFailures) {
				if (count % 2 == 0) {
					out.println("                    <tr>");
				} else {
					out.println("                    <tr class='alt'>");
				}

				out.println("                      <td>" + fail.getiMSI()
						+ "</td>");
				out.println("                      <td>"
						+ fail.getDateTime() + "</td>");
				out.println("                      <td>"
						+ fail.getCause().getDescription() + "</td>");
				count++;		
		}
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