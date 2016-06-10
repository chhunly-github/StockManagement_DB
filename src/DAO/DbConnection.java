package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {
	private static Connection connect=null;
	/*Properties pro =new Properties();
	*/
	public static Connection getConnection(String databaseName)throws ClassNotFoundException, SQLException{

			Class.forName("org.postgresql.Driver");
			String URL="jdbc:postgresql://localhost:5432/"+databaseName;
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
	public static boolean isDatabaseExist(String databaseName){
		try{
			DbConnection.getConnection(databaseName);
			return true;
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			
		}finally{
			DbConnection.closeConnection();
		}
		return false;
	}
	/*----------------------------------check and create table----------------------*/
	public static boolean checkExistTable(String dbname, String tbname){
		if(!isDatabaseExist(dbname)){
			return false;
		}
		Connection cnn=null;
		try{
			cnn=DbConnection.getConnection(dbname);
			Statement st=cnn.createStatement();
			st.executeQuery("SELECT * FROM "+tbname);
			return true;
		}catch(Exception e){
			return false;
		}finally{
			DbConnection.closeConnection();
		}
	}
	
	public static boolean createTable(String tbname){
		//if(s)
		return true;
	}
	public static boolean createDatabase(String dbname){
		try{
			Connection cnn=DbConnection.getConnection("");
			String sql="CREATE DATABASE "+dbname+";";
			Statement st=cnn.createStatement();
			//ps.setString(1, dbname);
			st.executeQuery(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnection.closeConnection();
		}
		return false;
	}
}
