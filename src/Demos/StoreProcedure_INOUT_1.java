package Demos;

import java.sql.*;

public class StoreProcedure_INOUT_1 {

	public static void main(String[] args) throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
			
			java.sql.CallableStatement myStatement = myConn.prepareCall("{call greet_the_department(?)}");
			
			myStatement.registerOutParameter(1, Types.VARCHAR);
			myStatement.setString(1, "Legal");
			
			myStatement.execute();
			
			
			String theResult = myStatement.getString(1);
			
			System.out.println("\n The result is: " + theResult);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
