package Demos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blob_Demo_3 {

	public static void main(String[] args) throws SQLException , ClassNotFoundException {
		
		Connection  connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		InputStream inputStream = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
			
			statement = connection.createStatement();
			
			String sql = "select resume from info_2 where name='Nebojsa'";
			
			rs = statement.executeQuery(sql);
			
			File file = new File("new_resume.pdf");
			FileOutputStream outputStream = new FileOutputStream(file);
			
			if(rs.next()){
				inputStream = rs.getBinaryStream("resume");
				
				byte[] buffer = new byte[1024];
				while(inputStream.read(buffer)>0){
					outputStream.write(buffer);
				}
			}
			
			System.out.println("Completed.");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 

	}

}
