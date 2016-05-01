package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;

public class StoredProcedure_OUT_1 {

	public static void main(String[] args) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
			
			java.sql.CallableStatement myStatement = conn.prepareCall("{call get_count_for_department(?,?)}");
			
			String department = "Legal";
			
			myStatement.setString(1, department);
			myStatement.registerOutParameter(2, Types.INTEGER);
			
			myStatement.execute();
			
			int count = myStatement.getInt(2);
			
			System.out.println("Number of employees in the department " + department + " is: " + count);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
