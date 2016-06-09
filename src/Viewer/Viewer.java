package Viewer;

import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public class Viewer {
	///----------------------------view product--------------------------///
		public static void viewProduct(ArrayList<Product> prd,int id){
			
			for(Product p:prd){
				if(p.getId()==id){
					int i=0;
					for(String s:Product.getFields()){
						System.out.printf("%-15s :",s);
						System.out.println(p.getData()[i]);
						i++;
					}
					System.out.printf("%-15s :","Content");
					System.out.println(p.getContent());
					return;
				}
			}
			System.err.println("Data not found!");
		}
		
		//-------------------------------display product-----------------------////
		public static void displayProduct(ArrayList<Product> prds,Pagination page){
			TableViewer.printHeader();
			TableViewer.printData(prds, page);
			TableViewer.printFooter(prds, page);
		}
}
