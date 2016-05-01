package Demos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaData_Schema_Info {

	public static void main(String[] args) throws SQLException{
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		Connection connection = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
			
			DatabaseMetaData metaData = connection.getMetaData();
			
			System.out.println("List of Tables");
			System.out.println("--------------");
			
			rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(rs.next()){
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			System.out.println("\n\nList of Columns");
			System.out.println("---------------");
			
			rs = metaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			
			while(rs.next()){
				System.out.println(rs.getString("COLUMN_NAME"));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
