package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		//We need to register our driver. This process makes the application aware of what particular Driver class we are using.
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://appian-210419.c98bhzcapksg.us-east-1.rds.amazonaws.com:5432/project1";
		String username = "postgres"; //you can use environment variables to hide the raw values to protect this information
		String password = "password"; //System.getenv("keyName");
		
		return DriverManager.getConnection(url, username, password);
	}
	
//	public static void main(String[] args) {
//		/*
//		 * Finally blocks are so commonly used to close resources (open connections outside of Java) that there
//		 * is a shortcut to creating a finally block that does that. It is a "try with resources block" that
//		 * declares the resource to open and then close at the try declaration.
//		 */
//		try(Connection conn = ConnectionUtil.getConnection()){
//			System.out.println("Connection Successful!");
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
