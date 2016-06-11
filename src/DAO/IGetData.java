package DAO;

import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public interface IGetData {
	public ArrayList<Product> getData(Pagination page, String arg);
}
