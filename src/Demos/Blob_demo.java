package Demos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Blob_demo {

	public static void main(String[] args) throws SQLException,Exception,FileNotFoundException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			String sql = "update info_2 set resume=? where name='nebojsa'";
			
			preparedStatement = connection.prepareStatement(sql);
			
			File theFile = new File("C:/Users/Nebojsa/Desktop/sample_resume.doc");
			FileInputStream inputStream = new FileInputStream(theFile);
			preparedStatement.setBinaryStream(1, inputStream);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			System.out.println("\nStoring resume in the database: " + theFile);
			System.out.println(sql);
			
			preparedStatement.executeUpdate();
			
			System.out.println("\nCompleted successfully!");
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
