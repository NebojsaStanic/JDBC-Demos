package Demos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class StoredProcedure_Call {

	public static void main(String[] args) throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			CallableStatement stmt = myConn.prepareCall("{call GetAllPerson()}");
			
			stmt.execute();
			
			System.out.println("Procedure called.");
			
			displayAll(myConn);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private static void displayAll(Connection conn) throws SQLException{
		
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try{
			prepStmt = conn.prepareStatement("select * from info");
			rs = prepStmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getInt(1) + " " + rs.getString(2)+ "  "  + rs.getString(3)+ "  "  + rs.getInt(4)+ " "  + rs.getString(5));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}
