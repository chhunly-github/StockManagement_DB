package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Product.Product;

public class ProductDAO {
	//--------------------------search product-------------------------------///
	public static ArrayList<Product> search(ArrayList<Product> prd, String search){
		ArrayList<Product> result=new ArrayList<>();
		for(Product p:prd){
			if(p.toString().toUpperCase().contains(search.toUpperCase()))
				result.add(p);
		}
		return result;
	}
	
	
	public ArrayList<Product> getAllProducts(){
		ArrayList<Product> products=new ArrayList<>();
		Connection cnn = null;
		try {
			cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT id FROM tbproduct ORDER BY id";
			Statement st=cnn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				Float unitprice=rs.getFloat("unitprice");
				Float quantity=rs.getFloat("stockqty");
				String impdate=rs.getString("impdate");
				String content=rs.getString("content");
				products.add(new Product(id, name, unitprice, quantity, impdate, content));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(cnn!=null)
				try {
					cnn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return products;	
	}
	
	public int searchCountProductById(int id){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT COUNT(1) FROM tbproduct WHERE id=?";
			PreparedStatement st=cnn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int searchCountProductByName(String sname){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT COUNT(1) FROM tbproduct WHERE name like ?";
			PreparedStatement st=cnn.prepareStatement(sql);
			st.setString(1, "%"+sname+"%");
			ResultSet rs=st.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int searchCountProductByContent(String search){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT COUNT(1) FROM tbproduct WHERE content like ?;";
			PreparedStatement st=cnn.prepareStatement(sql);
			st.setString(1, "%"+search+"%");
			ResultSet rs=st.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*------------------------------------insert data----------------------------*/
	public boolean insertData(Product prd){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES(?,?,?,?,?)";
			PreparedStatement pps=cnn.prepareStatement(sql);
			pps.setString(1, prd.getName());
			pps.setFloat(2, prd.getUnitPrice());
			pps.setFloat(3, prd.getStockQty());
			pps.setString(4, prd.getImpDate());
			pps.setString(5, prd.getContent());
			
			pps.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*------------------------------------test data-------------------------------*/
	public boolean insertTestData(){
		String[] sql={"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('anchor',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('cocacola',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('vital',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('hi-tech',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('dazani',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('euro-tech',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('mama',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('abc',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('ankor',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('doubleA',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('number 1',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('nike',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('gatsby',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('aloe vera',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('sagiko',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('freshy',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('bacchus',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('aquarius',?,?,?,?)",
				"INSERT INTO tbproduct(name,unitprice,stockqty,impdate,content) VALUES('pokarisvet',?,?,?,?)"
		
		};
		int i=0;
		while(i<sql.length){
			try {
				Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
				PreparedStatement pps=cnn.prepareStatement(sql[i]);
				pps.setFloat(1, i*5);
				pps.setFloat(2, i*8);
				pps.setString(3, 2016+"/"+2+"/"+2);
				pps.setString(4, "content"+i);
				pps.executeUpdate();
				i++;
				continue;
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				System.out.println("Driver not found!");
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Cannot insert data");
				return false;
			}
		}
		return true;
	}
	
	/*--------------------------------------update data-----------------------*/
	public boolean updateData(Product prd){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="UPDATE tbproduct SET name=?,unitprice=?,stockqty=?,content=? WHERE id=?;";
			PreparedStatement pps=cnn.prepareStatement(sql);
			
			pps.setString(1, prd.getName());
			pps.setFloat(2, prd.getUnitPrice());
			pps.setFloat(3, prd.getStockQty());
			pps.setString(4, prd.getContent());
			pps.setInt(5, prd.getId());

			pps.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*--------------------------------------delete data-------------------------------*/
	public boolean deleteDataById(int id){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="DELETE FROM tbproduct WHERE id=?";
			PreparedStatement pps=cnn.prepareStatement(sql);
			pps.setInt(1, id);
			pps.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteDataByName(String name){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="DELETE FROM tbproduct WHERE name=?";
			PreparedStatement pps=cnn.prepareStatement(sql);
			pps.setString(1, name);
			pps.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteAll(){
		try {
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="DELETE FROM "+ProductDAO.tableName()+";";
			PreparedStatement pps=cnn.prepareStatement(sql);
			pps.executeUpdate();
			cnn.close();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*------------------------------------get all amount of product-------------------*/
	public int numberOfProduct(){
		try{
			Connection cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String getCount="SELECT COUNT(1) FROM tbproduct;";
			Statement stm=cnn.createStatement();
			ResultSet rs=stm.executeQuery(getCount);
			rs.next();
			int count=rs.getInt(1);
			
			//System.out.println("hello!");
			return count;
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	/*----------------------------------create database generator-------------------*/
	public static String tableName(){
		return "tbproduct";
	}
	public static String databaseName(){
		return "dbproduct";
	}
	

}
