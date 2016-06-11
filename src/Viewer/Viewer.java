package Viewer;

import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public class Viewer {
	///----------------------------view product--------------------------///
	public static void viewProduct(Product prd){
		
		int i=0;
		for(String s:Product.getFields()){
			System.out.printf("%-15s :",s);
			System.out.println(prd.getData()[i]);
			i++;
		}
		System.out.printf("%-15s :","Content");
		System.out.println(prd.getContent());
		return;
	}
		
		//-------------------------------display product-----------------------////
		public static void displayProduct(ArrayList<Product> prds,Pagination page){
			
			TableViewer.printHeader();
			TableViewer.printData(prds, page);
			TableViewer.printFooter(page.getTotalRecord(), page);
		}
}
