package Demos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaData {

	public static void main(String[] args) throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
			
			java.sql.DatabaseMetaData metaData = connection.getMetaData();
			
			System.out.println("Product name: " + metaData.getDatabaseProductName());
			System.out.println("Product version: " + metaData.getDatabaseProductVersion() + "\n");
			System.out.println("Driver name: " + metaData.getDriverName());
			System.out.println("Driver version: " + metaData.getDriverVersion());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
