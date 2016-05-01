package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transactions_2 {

	public static void main(String[] args) throws SQLException{
		
		Connection myConn = null;
		Statement myStatement = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			myConn.setAutoCommit(false);
			
			System.out.println("Table before transactions:\n");
			display(myConn,"Nebojsa");
			display(myConn, "Jovan");
			
			myStatement = myConn.createStatement();
			myStatement.executeUpdate("insert into info(first_name,last_name,age,email) values('Nebojsa','Stanic',29,'nebojsa.stanic86@gmail.com')");
			
			myStatement.executeUpdate("update info set email='jovan.joca54@gmail.com' where first_name='Jovan'");
			
			System.out.println("Transactions ready. Do you want to save them?yes/no");
			
			boolean answer = askTheUserToSave();
			if(answer){
				myConn.commit();
				System.out.println("Transaction COMMITED.");
			}
			else{
				myConn.rollback();
				System.out.println("Transaction ROLLED BACK.");
			}
			
			System.out.println("Table after transactions:\n");
			display(myConn,"Nebojsa");
			display(myConn, "Jovan");
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(myConn, myStatement, null);
		}
		

	}
	
	private static void display(Connection conn,String name) throws SQLException{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement("select * from info where first_name=?");
			statement.setString(1, name);
			rs = statement.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			close(statement, rs);
		}
	}
	
	private static boolean askTheUserToSave(){
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		scanner.close();
		return answer.equalsIgnoreCase("yes");
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
