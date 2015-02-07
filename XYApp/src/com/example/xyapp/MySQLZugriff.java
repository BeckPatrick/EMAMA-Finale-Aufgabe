package com.example.xyapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLZugriff{
		
	public static void main(String[] args) {
		Connection con;
		Statement stmt;
		ResultSet rs;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/App?user=root&password=");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from App");
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
}