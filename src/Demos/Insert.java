package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

	public static void main(String[] args) throws SQLException{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		
		// 1. Get a connection to the database
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
		
		// 2. Create Statement object
		
		Statement myStmt = myConn.createStatement();
		
		// 3. Create SQL string
		
		String sql = "insert into info(first_name,last_name,age,email) "
				+ "values('Dragan','Bozic',59,'dragan.bozic@gmail.com')";
		
		// 4. Execute SQL Update
		
		myStmt.executeUpdate(sql);
		
		System.out.println("Update complete.");
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
