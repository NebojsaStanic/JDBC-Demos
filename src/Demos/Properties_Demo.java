package Demos;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Properties_Demo {

	public static void main(String[] args) throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("C:/Users/Nebojsa/workspace/JDBC Demos/demos.properties"));
			
			String theUser = properties.getProperty("user");
			String thePassword = properties.getProperty("password");
			String theDbURL = properties.getProperty("dburl");
			
			System.out.println("Connecting to database....");
			System.out.println("Database URL: " + theDbURL);
			System.out.println("User: " + theUser);
			
			connection = DriverManager.getConnection(theDbURL, theUser, thePassword);
			
			System.out.println("\nConnection successful!\n");
			
			statement = connection.createStatement();
			String sql = "select first_name,last_name from employees";
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
