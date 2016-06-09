package Operations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Pagination.Pagination;
import Product.Product;
import UI_Main.UserInterface;
import Viewer.TableViewer;
public class OperationsControl {//not allowed to create object
	
	

		////-------------------------------update the product------------------------//
	public static void update(int id){
		
		Product pro=null;
		for(Product p:UserInterface.defaultRecords){
			if(p.getId()==id){
				pro=p;
				break;
			}
		}
		if(pro==null){
			System.out.println("Cannot find id you entered. Please try again.");
			return;
		}
		
		Scanner sc=new Scanner(System.in);
		String ch;
		boolean b=false;
		do{	
			System.out.println("(1)Update All\t(2)Update Name\t(3)Update UnitPrice\t(4)Stock Quanity\t(5)Update Content\t(6)exit");
			ch=sc.nextLine();
			String name="";
			float unit=0;
			float qty=0;
			String content="";
			
			switch(ch){
				case "1":
					name=inputString("name");
					unit=inputFloat("Unit Price");
					qty=inputFloat("Stock quantity:");
					System.out.print("Content: ");
					content=inputContent();
					pro.setName(name);
					pro.setUnitPrice(unit);
					pro.setStockQty(qty);
					pro.setContent(content);
					
					if(!Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						saveTemp(UserInterface.defaultRecords);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate all success");
					break;
					//return;
				case "2": 
					name=inputString("name");
					pro.setName(name);
					if(!Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						saveTemp(UserInterface.defaultRecords);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate name success");
					break;
				//return;
				case "3": 
					unit=inputFloat("Unit Price");
					pro.setUnitPrice(unit);
					if(!Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						saveTemp(UserInterface.defaultRecords);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate unitprice success");
					break;
				//return;
				case "4": 
					qty=inputFloat("Stock quantity:");
					pro.setStockQty(qty);
					if(!Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						saveTemp(UserInterface.defaultRecords);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate quantity success");
					break;
				//return;
				case "5": 
					System.out.print("Content: ");
					content=inputContent();
					pro.setContent(content);
					if(!Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						saveTemp(UserInterface.defaultRecords);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate content success");
					break;
					//return;
				default: break;
			}
		}while(!ch.equals("6"));
	}

	//private constructor here preventing for creating object
	private OperationsControl(){
		
	}
	
	/*----------------reading data-----------------*/
	public static ArrayList<Product> readData(){
		ArrayList<Product> prdRecords=null;
		System.out.println("loading data...");
		FileInputStream fos;
		ObjectInputStream ois=null;
		try {
			fos = new FileInputStream("Files/prds.prd");
			BufferedInputStream bis=new BufferedInputStream(fos);
			ois=new ObjectInputStream(bis);
			prdRecords=(ArrayList<Product>) ois.readObject();
			System.out.println(prdRecords.size());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("\n\nThere's no product here!");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Successfully read!");
		if(prdRecords==null){
			return new ArrayList<>();
		}
		return prdRecords;
		
	}
	
	

}
