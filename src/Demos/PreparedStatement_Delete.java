package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatement_Delete {

	public static void main(String[] args) throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			deleteData("Nemanja", "Panic");
			deleteData("Jovana", "Bozic");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void deleteData(String fname,String lname) throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Person","root","");
		
		PreparedStatement stmt = conn.prepareStatement("delete from info where first_name=? and last_name=?");
		
		stmt.setString(1, fname);
		stmt.setString(2, lname);
		
		stmt.executeUpdate();
		
		System.out.println("Delete completed.");
	}

}
