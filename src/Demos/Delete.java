package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Delete {

	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// 1. Get a connection to the database
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			// 2. Create Statement object
			
			Statement myStmt = myConn.createStatement();
			
			// 3. Create SQL string
			
			String sql = "delete from info where last_name='bozic'";
			
			// 4. Execute SQL Update
			
			int rowsAffected = myStmt.executeUpdate(sql);
			
			System.out.println("Row affecteted:" + rowsAffected);
			System.out.println("Delete complete.");
			
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

	}

}
