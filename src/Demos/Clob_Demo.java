package Demos;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Clob_Demo {

	public static void main(String[] args) {
		
		Connection  connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		InputStream inputStream = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			String sql = "update info_3 set resume=? where name='John'";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			File file = new File("C:/Users/Nebojsa/Desktop/Casovi!.doc");
			FileReader fileReader = new FileReader(file);
			
			preparedStatement.setCharacterStream(1, fileReader);
			
			preparedStatement.execute();
			
			System.out.println("Completed!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
