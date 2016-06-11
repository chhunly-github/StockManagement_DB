package Viewer;

import java.util.ArrayList;

import Pagination.Pagination;
import Product.Product;

public class TableViewer {
	
	private TableViewer(){
		
	}
	/*-------------------------print header of table-------------------------*/
	public static void printHeader(){
		System.out.println();
		System.out.print("+");//"+"
		for(int i=0;i<Product.getFields().length;i++){
			for(int j=0;j<15;j++)
				System.out.print("-");
			if(i!=Product.getFields().length-1)
				System.out.print("+");
		}
		System.out.println("+");
		System.out.print("|");
		for(String s:Product.getFields()){
			System.out.printf("%-15s",s);
			System.out.print("|");
		}
		System.out.println();
		System.out.print("+");
		for(int i=0;i<Product.getFields().length;i++){
			for(int j=0;j<15;j++)
				System.out.print("-");
			if(i!=Product.getFields().length-1)
				System.out.print("+");
		}
		System.out.println("+");
	}
/*--------------------end of print header of table-------------------------*/

/*-------------------------print data of table-------------------------*/	
	public static void printData(ArrayList<Product> prdRecords,Pagination page){
		
		if(page.getTotalPage()==0)
			return;
		int t=0;
		
		/*int dec=page.getTotalPage()*page.getRecordPerPage()>prdRecords.size()?page.getTotalPage()*page.getRecordPerPage()-prdRecords.size():0;
		int start=page.getRecordPerPage()*(page.getTotalPage()-page.getCurrentPage()+1)-1-dec;
		int lastdata=start-page.getRecordPerPage();
		if(lastdata<0)
			lastdata=0;*/
		
		for(Product p:prdRecords){
			System.out.print("|");
			for(int j=0;j<p.getData().length;j++){
				System.out.printf("%-15s",p.getData()[j]);
				if(j!=Product.getFields().length-1)
					System.out.print("|");
			}
			System.out.println("|");
			
			System.out.print("+");
			for(int k=0;k<Product.getFields().length;k++){
				for(int j=0;j<15;j++)
					System.out.print("-");
				if(k!=Product.getFields().length-1)
					System.out.print("+");
			}
			System.out.println("+");
			t++;
			if(t==page.getRecordPerPage())
				break;
		}
		

	}
/*--------------------end of print data of table-------------------------*/

/*-------------------------print footer of table-------------------------*/	
	public static void printFooter(int record, Pagination p){
		
		System.out.print("|");
		String margin="      ";
		String page="Page: "+p.getCurrentPage()+"/"+p.getTotalPage();
		String totalRecord="Total Records: "+record;
		int leftspace=15*(Product.getFields().length) - (margin.length()*2+page.length()+totalRecord.length())+Product.getFields().length-1;
		System.out.print(margin+page);
		for(int i=0;i<leftspace;i++)
			System.out.print(" ");
		System.out.print(totalRecord+margin);
		System.out.println("|");
		
		System.out.print("+");
		for(int i=0;i<Product.getFields().length;i++){
			for(int j=0;j<15;j++)
				System.out.print("-");
			if(i!=Product.getFields().length-1)
				System.out.print("-");
		}
		System.out.println("+");
	}
/*--------------------end of print footer of table-------------------------*/

}
