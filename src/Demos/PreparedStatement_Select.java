package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement_Select {

	public static void main(String[] args) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
			
			java.sql.PreparedStatement myStmt = conn.prepareStatement("select * from employees where salary>? and department=?");
			
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "legal");
			
			ResultSet myRs = myStmt.executeQuery();
			
			display(myRs);
			
			System.out.println("\nReuse of prepared statements.");
			
			myStmt.setDouble(1, 25000);
			myStmt.setString(2, "hr");
			
			myRs = myStmt.executeQuery();
			
			display(myRs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void display(ResultSet myRs) throws SQLException {
		
		while (myRs.next()) {
					
		String lastName = myRs.getString("last_name");
					
		String firstName = myRs.getString("first_name");
					
		double salary = myRs.getDouble("salary");
					
		String department = myRs.getString("department");
					
					
		System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
				
		}
			
	}


}
