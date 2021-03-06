package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static Connection connect=null;

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
	
	public static boolean createTable(String tbname,String dbname){
		try{
			Connection cnn=DbConnection.getConnection(dbname);
			String sql="CREATE TABLE "+tbname+"(id serial PRIMARY KEY , name VARCHAR(30), unitprice FLOAT, stockqty FLOAT, impdate VARCHAR(15), content VARCHAR(200));";
			Statement st=cnn.createStatement();
			st.executeUpdate(sql);
			return true;
		}catch(SQLException e){
			//e.printStackTrace();
			System.out.println("table existed!");
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.err.println("Driver not found!");
		}finally{
			DbConnection.closeConnection();
		}
		return false;
	}
	public static boolean createDatabase(String dbname){
		try{
			Connection cnn=DbConnection.getConnection("");
			String sql="CREATE DATABASE "+dbname+";";
			Statement st=cnn.createStatement();
			st.executeUpdate(sql);
			return true;
		}catch(SQLException e){
			//e.printStackTrace();
			//System.err.println("Can't connect to database!");
			
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.err.println("Driver not found!");
		}finally{
			DbConnection.closeConnection();
		}
		return false;
	}
}
