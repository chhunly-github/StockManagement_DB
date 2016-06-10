package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
			cnn=DbConnection.getConnection("dbProduct");
			String sql="SELECT * FROM tbproduct ORDER BY id";
			Statement st=cnn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				Float unitprice=rs.getFloat("unitprice");
				Float quantity=rs.getFloat("quantity");
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
	public ArrayList<Product> searchProductById(int id){
		ArrayList<Product> products=new ArrayList<>();
		try {
			Connection cnn=DbConnection.getConnection("dbStudent");
			String sql="SELECT * FROM tbproduct WHERE id=?";
			PreparedStatement st=cnn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				//int id=rs.getInt("id");
				String name=rs.getString("name");
				Float unitprice=rs.getFloat("unitprice");
				Float quantity=rs.getFloat("quantity");
				String impdate=rs.getString("impdate");
				String content=rs.getString("content");
				products.add(new Product(id, name, unitprice, quantity, impdate, content));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public ArrayList<Product> searchProductByName(String sname){
		ArrayList<Product> products=new ArrayList<>();
		try {
			Connection cnn=DbConnection.getConnection("dbProduct");
			String sql="SELECT * FROM tbproduct WHERE name like ? ORDER BY id";
			PreparedStatement st=cnn.prepareStatement(sql);
			st.setString(1, "%"+sname+"%");
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				Float unitprice=rs.getFloat("unitprice");
				Float quantity=rs.getFloat("quantity");
				String impdate=rs.getString("impdate");
				String content=rs.getString("content");
				products.add(new Product(id, name, unitprice, quantity, impdate, content));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public ArrayList<Product> searchProductByRandom(String search){
		ArrayList<Product> prds=new ProductDAO().getAllProducts();
		ArrayList<Product> products=new ArrayList<>();
		for(Product prd:prds){
			if((prd.toString().replace('/', ' ')).toUpperCase().contains(search.toUpperCase())){
				products.add(prd);
			}
			//System.out.println(st.toString().replace('/', ' '));
		}
		return products;
	}
	
	public boolean insertData(Product prd){
		try {
			Connection cnn=DbConnection.getConnection("dbProduct");
			String sql="INSERT INTO tbproduct VALUES(?,?,?,?,?)";
			PreparedStatement pps=cnn.prepareStatement(sql);
			pps.setInt(1, prd.getId());
			pps.setString(2, prd.getName());
			pps.setFloat(3, prd.getUnitPrice());
			pps.setFloat(4, prd.getStockQty());
			pps.setString(5, prd.getImpDate());
			pps.setString(6, prd.getContent());
			
			pps.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
		}
		return false;
	}
	public boolean updateData(Product prd){
		try {
			Connection cnn=DbConnection.getConnection("dbProduct");
			String sql="UPDATE tbproduct SET name=?,unitprice=?,quantity=?,content=? WHERE id=?;";
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
			Connection cnn=DbConnection.getConnection("dbProduct");
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
			Connection cnn=DbConnection.getConnection("dbProduct");
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
			Connection cnn=DbConnection.getConnection("dbProduct");
			String sql="DELETE FROM tbproduct";
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
			Connection cnn=DbConnection.getConnection("dbProduct");
			String getCount="SELECT COUNT FROM tbproduct";
			Statement stm=cnn.createStatement();
			int count=stm.executeQuery(getCount).getRow();
			return count;
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			
		}
		return 0;
	}
	
	/*----------------------------------id generator-------------------*/
	public int idGenerated(){
		int i=this.numberOfProduct();
		return ++i;
	}
	

}
