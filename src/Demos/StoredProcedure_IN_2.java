package Demos;

import  java.sql.*;

import com.mysql.jdbc.CallableStatement;

public class StoredProcedure_IN_2 {

	public static void main(String[] args) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
			
			String department = "Legal";
			double increment = 10000.00;
			
			System.out.println("Before calling stored procedure:");
			showSalaries(myConn, department);
			
			java.sql.CallableStatement myCallableStatement = myConn.prepareCall("{call increase_salary(?,?)}");
			
			myCallableStatement.setString(1, department);
			myCallableStatement.setDouble(2, increment);
			
			myCallableStatement.execute();
			
			System.out.println("\nAfter calling stored procedure:");
			showSalaries(myConn, department);
			
			
		
		}
		catch(Exception  e){
			e.printStackTrace();
		}

	}
	
	private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
		
		PreparedStatement myStmt = null;
				
		ResultSet myRs = null;

				
		try {
					
		// Prepare statement
					
		myStmt = myConn.prepareStatement("select * from employees where department=?");

					
		myStmt.setString(1, theDepartment);
					
					
		// Execute SQL query
					
		myRs = myStmt.executeQuery();

					
		// Process result set
					
			while (myRs.next()) {
					
				String lastName = myRs.getString("last_name");
						
				String firstName = myRs.getString("first_name");
						
				double salary = myRs.getDouble("salary");
						
				String department = myRs.getString("department");
							
				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
