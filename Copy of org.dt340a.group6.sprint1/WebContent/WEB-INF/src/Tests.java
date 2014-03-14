import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dt340a.group6.sprint1.persistence.UserConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class Tests {
	static Connection connection = null;
	PreparedStatement loginStatement = null;
	PreparedStatement toDoStatement = null;
	ResultSet loginResultSet = null;
	ResultSet toDoResultSet = null;
	static UserConfig Persist;
	
	@BeforeClass
	public static void testSetup() throws SQLException {  
		connection =ConnectionFactory.getInstance().getConnection();
		Persist = new UserConfig();
		Persist.createUser("TestUser","TestPassword", "Support Engineer");
		Persist.createListItem("TestUser","TestListItem");		
	}
	
//	@AfterClass
//	public static void testCleanup() {
//		Persist.removeUser("TestUser");	
//		Persist.removeListItem("TestUser","TestPassword");		
//	}
	
	@Test
	public void readUser() {		
		
		String loginQueryString = "SELECT username, password FROM user WHERE username = ? and password=?";
		try {
		
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1,"TestUser");
			loginStatement.setString(2,"TestPassword");
			loginResultSet = loginStatement.executeQuery();
			loginResultSet.next();
			assertEquals("Username Must be TestUser", "TestUser", loginResultSet.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	@Test
	public void readList() {			
		String toDoQueryString = "SELECT username, password,  itemContent FROM user, listItem WHERE username = ? and password=? and user.id = listItem.user_id";
		
		try {
			toDoStatement = connection.prepareStatement(toDoQueryString);
			toDoStatement.setString(1,"TestUser");
			toDoStatement.setString(2,"TestPassword");
			toDoResultSet = toDoStatement.executeQuery();
			
			toDoResultSet.next();
			
			assertEquals("List content Must be TestListItem", "TestListItem", toDoResultSet.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
