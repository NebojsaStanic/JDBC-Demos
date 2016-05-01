package Demos;

import java.awt.DisplayMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;

public class StoredProcedures_IN {

	public static void main(String[] args) throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			int age_increment  = 10;
			
			System.out.println("Before calling stored procedure:");
			displayAll(myConn);
			
			java.sql.CallableStatement myCallableStatement = myConn.prepareCall("{call increase_age(?)}");
			
			myCallableStatement.setInt(1, age_increment);
			
			myCallableStatement.execute();
			
			System.out.println("After calling stored procedure:");
			displayAll(myConn);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	private static void displayAll(Connection conn) throws SQLException{
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = conn.prepareStatement("select * from info");
			rs = prep.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getInt(1) + " " + rs.getString(2)+ "  "  + rs.getString(3)+ "  "  + rs.getInt(4)+ " "  + rs.getString(5));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
