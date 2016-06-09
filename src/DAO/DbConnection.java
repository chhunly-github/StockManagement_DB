package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	private static Connection connect=null;
	/*Properties pro =new Properties();
	*/
	public static Connection getConnection(String databaseName)throws ClassNotFoundException, SQLException{

			Class.forName("org.postgresql.Driver");
			String URL="jdbc:postgresql://localhost:5432/postgres";//+databaseName;
			String USER="postgres";
			String PASSWORD="123";
			connect=DriverManager.getConnection(URL, USER, PASSWORD);
			return connect;
	}
	
	public static boolean closeConnection(){
		try {
			connect.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean isDatabaseExist(String databaseName){
		try{
			Class.forName("org.postgressql.Driver");
			String USER="postgres";
			String PASSWORD="123";
			DriverManager.getConnection(databaseName,USER,PASSWORD);
			return true;
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			
		}
		return false;
	}
}
