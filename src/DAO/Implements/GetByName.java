package DAO.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DbConnection;
import DAO.ProductDAO;
import DAO.Interfaces.IGetData;
import Pagination.Pagination;
import Product.Product;

public class GetByName implements IGetData{
	private String searchName;
	public GetByName(String searchname){
		this.searchName=searchname;
	}
	@Override
	public ArrayList<Product> getData(Pagination page, String arg) {
		ArrayList<Product> products=new ArrayList<>();
		Connection cnn = null;
		try {
			cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT * FROM tbproduct WHERE name like ? ORDER BY id limit ? offset ?";
			//Statement st=cnn.createStatement();
			
			PreparedStatement ps=cnn.prepareStatement(sql);
			ps.setString(1, "%"+this.searchName+"%");
			ps.setInt(2, page.getRecordPerPage());
			ps.setInt(3, page.offSet());
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
	}

}
