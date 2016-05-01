package Demos;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Blob_Demo_2 {

	public static void main(String[] args) throws SQLException {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			File theFile = new File("C:/Users/Nebojsa/Desktop/basic-resume-template-1.pdf");
			FileInputStream inputStream = new FileInputStream(theFile);
			
			String sql = "update info_2 set resume=? where name='Nebojsa'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setBinaryStream(1, inputStream);
			
			statement.executeUpdate();
			
			System.out.println("Completed!");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
