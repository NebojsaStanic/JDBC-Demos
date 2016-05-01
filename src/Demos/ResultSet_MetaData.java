package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

public class ResultSet_MetaData {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select id,last_name,first_name,salary from employees");
			
			java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			
			int columnCount = resultSetMetaData.getColumnCount();
			
			for(int column=1 ; column<=columnCount ; column++){
				System.out.println("Column name: " + resultSetMetaData.getColumnName(column));
				System.out.println("Column type name: " + resultSetMetaData.getColumnTypeName(column));
				System.out.println("Is nullable: " + resultSetMetaData.isNullable(column));
				System.out.println("Is auto increment: " + resultSetMetaData.isAutoIncrement(column) + "\n");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
