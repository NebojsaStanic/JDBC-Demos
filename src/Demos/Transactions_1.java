package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transactions_1 {

	public static void main(String[] args) throws SQLException{
		
		Connection myConn = null;
		Statement myStatement = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
			
			myConn.setAutoCommit(false);
			
			System.out.println("Salaries before:\n");
			showSalaries(myConn,"HR");
			showSalaries(myConn,"Engineering");
			
			myStatement = myConn.createStatement();
			myStatement.executeUpdate("delete from employees where department='hr'");
			
			myStatement.executeUpdate("update employees set salary=300000 where department='Engineering'");
			
			System.out.println("Changes made. Should we proceed?");
			
			boolean ok = askTheUserIfOkToSave();
			
			if(ok){
				myConn.commit();
				System.out.println("Transaction COMMITED");
			}
			else{
				myConn.rollback();
				System.out.println("Transaction ROLLED BACK");
			}
			
			System.out.println("Salaries after:");
			
			showSalaries(myConn,"HR");
			showSalaries(myConn,"Engineering");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn, myStatement, null);
		}
		

	}
	
	private static void showSalaries(Connection conn, String department) throws SQLException{
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("select * from employees where department=(?)");
			stmt.setString(1, department);
			rs = stmt.executeQuery();
			System.out.println("\nPrinting for the department: " + department);
			while(rs.next()){
				System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(5) + " " + rs.getDouble(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(stmt, rs);
		}
		
	}
	
	private static boolean askTheUserIfOkToSave(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Is it OK to save? yes/no");
		String input = scanner.nextLine();
		scanner.close();
		return input.equalsIgnoreCase("yes");
	}
	
	private static void close(Connection myConn, Statement myStmt,ResultSet myRs) throws SQLException {
			if (myRs != null) {			
			myRs.close();
			}	
			if (myStmt != null) {		
			myStmt.close();
			}	
			if (myConn != null) {		
			myConn.close();		
			}	
	}
	
	private static void close(Statement myStmt, ResultSet myRs) throws SQLException {
				close(null, myStmt, myRs);
			}





}
