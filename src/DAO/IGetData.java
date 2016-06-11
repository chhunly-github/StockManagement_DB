package DAO;

import java.util.ArrayList;

import Pagination.Pagination;

public interface IGetData {
	public ArrayList<Object> getData(Pagination page, String arg);
}
