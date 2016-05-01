package Demos;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Clob_Demo_2 {

	public static void main(String[] args) {
		
		Connection  connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		Reader reader = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			String sql = "select resume from info_3 where name='John'";
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery(sql);
			
			File file = new File("resume_from_db.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			if(rs.next()){
				reader = rs.getCharacterStream("resume");
				
				int theChar;
				while((theChar = reader.read())>0){
					fileWriter.write(theChar);
				}
			}
			
			System.out.println("Done.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
