package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public class GetById implements IGetData{
	private int id;
	public GetById(int id) {
		this.id=id;
	}
	@Override
	public ArrayList<Product> getData(Pagination page, String arg) {
		ArrayList<Product> products=new ArrayList<>();
		Connection cnn = null;
		try {
			cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT * FROM tbproduct WHERE id = ? ";
			//Statement st=cnn.createStatement();
			PreparedStatement ps=cnn.prepareStatement(sql);
			ps.setInt(1, this.id);
			System.out.println(page.offSet()+"offset here;");
			ResultSet rs=ps.executeQuery();
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
		return /*(ArrayList<Object>)*/products;
		//return null;
	}
	public Product getProduct(){
		Product products=new Product();
		Connection cnn = null;
		try {
			cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT * FROM tbproduct WHERE id = ? ";
			//Statement st=cnn.createStatement();
			PreparedStatement ps=cnn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				Float unitprice=rs.getFloat("unitprice");
				Float quantity=rs.getFloat("stockqty");
				String impdate=rs.getString("impdate");
				String content=rs.getString("content");
				products=new Product(id, name, unitprice, quantity, impdate, content);
			}
			return products;
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
		return /*(ArrayList<Object>)*/null;
		//return null;
	}

}
