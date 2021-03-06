package DAO.Implements;

import java.util.ArrayList;

import DAO.Interfaces.IGetData;
import Pagination.Pagination;
import Product.Product;

public class GetByReport implements IGetData{
	private ArrayList<Product> prdReport;
	public GetByReport(ArrayList<Product> prds){
		this.prdReport=prds;
	}
	@Override
	public ArrayList<Product> getData(Pagination page, String arg) {
		ArrayList<Product> prd=new ArrayList<>();
		if(this.prdReport==null){
			return new ArrayList<>();
		}
		System.out.println("total="+this.prdReport.size());
		for(int i=page.offSet();i<page.getLastData();i++){
			prd.add(prdReport.get(i));
		}
		return prd;
	}
	
}
