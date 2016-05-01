package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Submit {

	public static void main(String[] args) throws SQLException{
		
			try{
				Class.forName("com.mysql.jdbc.Driver");
				
				// 1. Get a connection to the database
				
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
				
				// 2. Create Statement object
				
				Statement myStmt = myConn.createStatement();
				
				// 3. Execute SQL query
				
				ResultSet myRs = myStmt.executeQuery("select * from info");
				
				// 4. Process the Result Set
				
				while(myRs.next()){
					System.out.println(myRs.getString(2) + " " + myRs.getString(3));
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
	}

}
