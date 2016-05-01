package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement_Insert {

	public static void main(String[] args) throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			insertData("Jovana", "Bozic", 25, "jovana.bozic@gmail.com");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void insertData(String fname, String lname, int age, String email) throws SQLException{
		 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
		
		PreparedStatement stmt = conn.prepareStatement("insert into info(first_name,last_name,age,email) values(?,?,?,?)");
		
		stmt.setString(1, fname);
		stmt.setString(2, lname);
		stmt.setInt(3, age);
		stmt.setString(4, email);
		
		stmt.executeUpdate();
		
		System.out.println("Update complete.");
	}

}
