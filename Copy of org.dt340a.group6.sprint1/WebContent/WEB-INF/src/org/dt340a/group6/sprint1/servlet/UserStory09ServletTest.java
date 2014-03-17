package org.dt340a.group6.sprint1.servlet;

import com.kizna.servletunit.*;
import javax.servlet.ServletException;
import java.io.IOException;

import junit.framework.*;
import org.junit.Test;

public class UserStory09ServletTest extends TestCase {
    
    UserStory09Servlet servlet = new UserStory09Servlet();
    
    public UserStory09ServletTest(String testName) {
        super(testName);
    }
    
    public static TestSuite suite() {
        TestSuite suite = new TestSuite(UserStory09ServletTest.class);
        return suite;
    }
    
    @Test
    public void testGetPost() 
    {
            try
            {
                    HttpServletRequestSimulator req = new HttpServletRequestSimulator();
                    HttpServletResponseSimulator res = new HttpServletResponseSimulator();
                    UserStory09Servlet servlet = new UserStory09Servlet();
                    req.addParameter("startdate", "2013-01-01T00:00");
                    req.addParameter("enddate", "2014-01-01T00:00");
                    // Do the get
                    servlet.doGet(req,res);
            
                    // Test the output
                    // Bring in the output
                    String result=  res.getWriterBuffer().toString();

                    // Instantiate the expected data
                      String expected =
                     "<html>\r\n"+
                             "<head>\r\n"+
                                     "<title>"+
                                             "Hello John Doe"+
                                     "</title>\r\n"+
                             "</head>\r\n"+
                             "<body>\r\n"+
                                     "<h1>Hello John Doe!</h1>\r\n"+
                             "</body>\r\n"+
                     "</html>\r\n";
                    
                    // Check if the result is the same as expected
                    assertEquals("doPost result", expected,result);
            }
            catch (IOException e)
            {
                assertTrue("IOException occurred while talking to servlet",false);
            }
            catch (ServletException e)
            {
                assertTrue("ServletException occurred while talking to servlet",false);
            }
    }


}
