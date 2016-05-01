package Demos;

import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;

import java.sql.*;

public class StoredProcedure_OUT_2 {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		java.sql.CallableStatement myStatement = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
			
			myStatement = conn.prepareCall("{call get_all_older_people(?)}");
			
			myStatement.setInt(1, 40);
			
			myStatement.execute();
			
			rs = myStatement.getResultSet();
			
			display(rs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static void display(ResultSet resultSet) throws SQLException{
		while(resultSet.next()){
			System.out.println(resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getInt(4));
		}
	}

}
