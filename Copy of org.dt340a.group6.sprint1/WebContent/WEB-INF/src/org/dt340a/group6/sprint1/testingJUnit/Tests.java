package org.dt340a.group6.sprint1.testingJUnit;
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
		connection = ConnectionFactory.getInstance().getConnection();
		Persist = new UserConfig();
		Persist.createUser("TestUser","TestPassword", "Support Engineer");	
	}
	
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

}
