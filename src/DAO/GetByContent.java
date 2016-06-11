package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public class GetByContent implements IGetData{

	@Override
	public ArrayList<Product> getData(Pagination page, String arg) {
		ArrayList<Product> products=new ArrayList<>();
		Connection cnn = null;
		try {
			cnn=DbConnection.getConnection(ProductDAO.databaseName());
			String sql="SELECT * FROM tbproduct WHERE content like ? ORDER BY id limit ? offset ?";
			
			PreparedStatement ps=cnn.prepareStatement(sql);
			ps.setString(1, "%"+arg+"%");
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
