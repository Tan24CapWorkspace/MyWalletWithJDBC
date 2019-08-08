package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private DBHelper() {}
	
	public static Connection getConnection() {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user= "hr";
			String pass = "hr"; 
			
			Connection con = DriverManager.getConnection(url,user,pass);
			return con;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
}
