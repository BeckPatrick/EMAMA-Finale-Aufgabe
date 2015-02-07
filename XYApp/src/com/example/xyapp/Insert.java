package com.example.xyapp;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;





public class Insert {
	
	public int eintragU(String User);
	public int eintragK(String Kommentar); 
	public int eintragen(String BildJ);
	public int eintragBP(String BildP);
	public int eintragenSW(String StaWi);
	public int eintragenSL(String StaLe);
	
	
	{
		
	
	Connection con = null;
	try {
		con = DriverManager
				.getConnection("jdbc:mysql://localhost/App?user=root&password=");
		Statement stmt = con.createStatement();
		return stmt.executeUpdate("INSERT INTO App(User) Values ('"+User+"')");
		return stmt.executeUpdate("INSERT INTO App(Kommentar) Values ('" + Kommentar + "')");
		return stmt.executeUpdate("INSERT INTO App(BildJ) Values ('" + BildJ + "')");
		return stmt.executeUpdate("INSERT INTO App(BildP) Values ('" + BildP + "')");
		return stmt.executeUpdate("INSERT INTO App(StaWi) Values ('" + StaWi + "')");
		return stmt.executeUpdate("INSERT INTO App(StaLe) Values ('" + StaLe + "')");
		
	
		
	} catch (SQLException e) {
			
		return 0;
	}
	
  }
}