package UI_Main;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import Controller.Input;
import DAO.ProductDAO;
import DAO.StudentDAO;
import DTO_Model.Student;
import Operations.OperationsControl;
import Pagination.Pagination;
import Product.Product;
import UI_Main.*;
import Viewer.Viewer;

public class UI_Function {
	private UI_Function(){
		
	}
	public static void display(UserInterface ui){
		System.out.println("Display Data");
		ui.page.calculate(ui.currentData.size());
		Viewer.displayProduct(ui.currentData, ui.page);
	}
	
	//----------------------------------write new product----------------------//
	public static void write(UserInterface ui){
		System.out.println("Write Data");
		
		System.out.println("--------------------------**************************-----------------------");
		System.out.println("-------------------------------Add new product-------------------------");
		int id=(int)Input.inputFloat("Input id:");
		String name=Input.inputString("Input name:");
		float unit=Input.inputFloat("Unit Price");
		float qty=Input.inputFloat("Stock quantity:");
		System.out.print("Content: ");
		String content=Input.inputContent();
		//Date date=Input.inputDate("Input date:");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String date=sdf.format(new Date());
		//System.out.println(sdf.format(new java.util.Date()));
		if(!ui.proDao.insertData(new Product(id,name,unit,qty,date,content))){
			System.out.println("Failed to insert data, data may duplicate primary key.");
			return;
		}
		System.out.println("Insertion succeed");
		
	}
	
	//------------------------------view specific product------------------//
	public static void read(UserInterface ui){
		System.out.println("Read Data");
		if(ui.proDao.numberOfProduct()==0){
			System.out.println("There's no product to view.");
			return;
		}
		int rId=(int)Input.inputFloat("Product id ");
		ArrayList<Product> prds=ui.proDao.searchProductById(rId);
		if(prds.size()==0){
			System.out.println("Could not find product id:"+rId);
			return;
		}
		Product prd=prds.get(0);
		Viewer.viewProduct(prd);
		
	}
	
	//--------------------------------delete data------------------------//
	public static void delete(UserInterface ui){
		System.out.println("Delete Data");
		int dId=(int)Input.inputFloat("Product id");
		if(ui.proDao.searchProductById(dId).size()==0){
			System.out.println("Could not find product id:"+dId);
			return;
		}
		if(!ui.proDao.deleteDataById(dId)){
			System.out.println("Delete data failed!");
			return;
		}
		System.out.println("Delete data succeed!");
		
	}
	
	//---------------------------------update data-------------------------//
	public static void update(UserInterface ui){
		System.out.println("Update Data");
		int uId=(int)Input.inputFloat("Product id");
		ArrayList<Product> prds=ui.proDao.searchProductById(uId);
		if(prds.size()==0){
			System.out.println("Cannot find id you entered. Please try again.");
			return;
		}
		Scanner sc=new Scanner(System.in);
		String ch;
		Product pro=prds.get(0);
		do{	
			System.out.println("(1)Update All\t(2)Update Name\t(3)Update UnitPrice\t(4)Stock Quanity\t(5)Update Content\t(6)exit");
			ch=sc.nextLine();
			String name="";
			float unit=0;
			float qty=0;
			String content="";
			switch(ch){
				case "1":
					name=Input.inputString("name");
					unit=Input.inputFloat("Unit Price");
					qty=Input.inputFloat("Stock quantity:");
					System.out.print("Content: ");
					content=Input.inputContent();
					pro.setName(name);
					pro.setUnitPrice(unit);
					pro.setStockQty(qty);
					pro.setContent(content);
					
					if(!Input.Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						ui.proDao.updateData(pro);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate all success");
					break;
					//return;
				case "2": 
					name=Input.inputString("name");
					pro.setName(name);
					if(!Input.Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						ui.proDao.updateData(pro);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate name success");
					break;
				//return;
				case "3": 
					unit=Input.inputFloat("Unit Price");
					pro.setUnitPrice(unit);
					if(!Input.Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						ui.proDao.updateData(pro);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate unitprice success");
					break;
				//return;
				case "4": 
					qty=Input.inputFloat("Stock quantity:");
					pro.setStockQty(qty);
					if(!Input.Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						ui.proDao.updateData(pro);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("upate quantity success");
					break;
				//return;
				case "5": 
					System.out.print("Content: ");
					content=Input.inputContent();
					pro.setContent(content);
					if(!Input.Confirmation("Are you sure to udpate")){
						System.out.println("cancel!");
						break;
					}
					System.out.println("Updating...");
					try {
						ui.proDao.updateData(pro);
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
	
	
	//------------------------------------go to next page-----------------------------//
	public static void next(UserInterface ui){
		System.out.println("Next Data");
		ui.page.nextPage();
		Viewer.displayProduct(ui.currentData, ui.page);
	}
	
	//-------------------------------------go to previous page--------------------------//
	public static void previous(UserInterface ui){
		System.out.println("Previous Data");
		ui.page.previousPage();
		Viewer.displayProduct(ui.currentData, ui.page);
	}
	
	//-------------------------------------go to first page---------------------------//
	public static void first(UserInterface ui){
		System.out.println("First Data");
		ui.page.firstPage();
		Viewer.displayProduct(ui.currentData, ui.page);
	}
	
	//----------------------------------------go to last page--------------------//
	public static void last(UserInterface ui){
		System.out.println("Last Data");
		ui.page.lastPage();
		Viewer.displayProduct(ui.currentData, ui.page);
	}
	

	//-----------------goto page?----------------------*/
	public static void gotoPage(int page,Pagination pages){
		if(pages.getTotalPage()<page||page<=0){
			System.out.println("The page you entered is not invalid! Please try again!(1 - "+pages.getTotalPage()+")");
			return;
		}
		pages.setCurrentPage(page);
	}
	///----------------------------------------set row in a page---------------------//
	public static void setRow(UserInterface ui){
		System.out.println("Set rows Data");
		int rowSet=(int)Input.inputFloat("row in a page");
		ui.page.setRecordPerPage(rowSet);
	}
	
	
///------------------------------------------backup file here------------------------------------///
	public static void backup(){
		Date d=new Date();
		String filename="Backup Files/backup_"+(d.getYear()+1900)+"_"+(d.getMonth()+1)+"_"+ d.getDate()+"_"+d.getTime()+".bac";
		/*File back=new File(filename);
		
         if (back.getParentFile() != null) {
             back.getParentFile().mkdirs();
         }
         OperationsControl.copyFile(UserInterface.usingFile, back);*/
		
		//change to data backup with db
	}
	
	//-----------------------------------------restore data here----------------------------------///
	public static void restore(){
		
		File back=new File("Backup Files");
		if(back.listFiles().length==0){
			System.out.println("There's no backup file here!");
			return;
		}
		int ind=0;
		for(File s:back.listFiles()){
			System.out.println((ind+1)+". "+s.getName());
			ind++;
		}
		
		while(true){
			System.out.println("Choose backup file by enter number to restore:\nType \"c\" to cancel restore");
			try{
				String s;
				s=Input.inputString("Your choice");
				if(s.equalsIgnoreCase("c")){
					return;
				}
				ind=Integer.parseInt(s);
				if(ind<=0||ind>back.listFiles().length){
					System.err.println("\nChoice you enter is not available! Try again!\n");
					continue;
				}
				break;
				
			}catch(Exception e){
				System.err.println("\nInvalid input! Try again!\n");
			}
			
		}
		if(!Input.Confirmation("Are you sure to restore")){
			System.out.println("Restore cancel!");
			return;
		}
		System.out.println("Your product has been restored!");
	}
	
	////----------------------------------------------input search data-------------------------------------///
	public static void Search(UserInterface ui){
		String choice;
		do{
			System.out.println("--------------------------**************************-----------------------");
			System.out.println("--------------------------Student searching data------------------------");
			System.out.println("1.)Search by id");
			System.out.println("2.)Search by name");
			System.out.println("3.)Search with all fields");
			System.out.println("exit.) back to menu");
			choice=Input.inputString("Option >");
			switch(choice){
			case "1":
				int id=(int)Input.inputFloat("Input search id:");
				ArrayList<Product> idFound=ui.proDao.searchProductById(id);
				
				if(idFound.size()==0){
					System.out.println("Could not find data id:"+id);
					break;
				}
				//Viewer.displayData(Product.getFields(), idFound.toArray());
				Viewer.displayProduct(idFound, ui.page);
				System.out.println("Total found:"+idFound.size()+" products");
				break;
			case "2":
				String name=Input.inputString("Input search name:");
				ArrayList<Product> nameFound=ui.proDao.searchProductByName(name);
				if(nameFound.size()==0){
					System.out.println("Could not match any name:"+name);
					break;
				}
				//Viewer.displayData(Product.getFields(), nameFound.toArray());
				Viewer.displayProduct(nameFound, ui.page);
				System.out.println("Total found:"+nameFound.size()+"products");
				break;
			case "3":
				String rand=Input.inputString("Input search word:");
				ArrayList<Product> randFound=ui.proDao.searchProductByRandom(rand);
				if(randFound.size()==0){
					System.out.println("Could not match any word:"+rand);
					break;
				}
				//Viewer.displayData(Product.getFields(), randFound.toArray());
				Viewer.displayProduct(randFound, ui.page);
				System.out.println("Total found:"+randFound.size()+"products");
				break;
			default:
			}
			
		}while(!choice.equalsIgnoreCase("exit"));
	}
	
	///--------------------------------------exiting program-----------------------------------///
	public static boolean exit(){
		if(!Input.Confirmation("Do you want to exit")){
			return false;
		}
		return true;
	}
	
	///----------------------------------------shortcut input-----------------------------------///
	public static void shortcut(String c, UserInterface src){
		String[] arr=c.split("#");
		switch(arr[0].toUpperCase()){
		case "W":
			try{
				Product p=new Product();
				int id=0;
				if(!UserInterface.defaultRecords.isEmpty()){
					id=UserInterface.defaultRecords.get(UserInterface.defaultRecords.size()-1).getId()+1;
				}
				p.setData(id+"-"+arr[1]);
				UserInterface.defaultRecords.add(p);
				System.out.println("Writing data...");
				OperationsControl.saveTemp(UserInterface.defaultRecords);
				System.out.println("Writing data success!");
			}catch(Exception e){
				System.out.println("Invalid format for shortcut writing data.");
				break;
			}
			break;
		case "R":
			try{
				int id=Integer.parseInt(arr[1]);
				OperationsControl.viewProduct(UserInterface.defaultRecords, id);
			}catch(Exception e){
				System.out.println("Product id must be integer!");
			}
			break;
		case "U":
			try{
				String[] u=arr[1].split("-");
				if(u.length<5){
					System.out.println("Invalid format for shortcut updating data.");
					break;
				}
				int id=Integer.parseInt(u[0]);
				
				Product pro=null;
				for(Product p:UserInterface.defaultRecords){
					if(p.getId()==id){
						pro=p;
						break;
					}
				}
				if(pro==null){
					System.out.println("Cannot find id you entered. Please try again.");
					break;
				}
				
				Product temp=new Product();
				temp.setData(arr[1]);
				if(OperationsControl.Confirmation("Are you sure to udpate")){
					System.out.println("Updating...");
					pro.setContent(temp.getContent());
					pro.setName(temp.getName());
					pro.setUnitPrice(temp.getUnitPrice());
					pro.setStockQty(temp.getStockQty());
					OperationsControl.saveTemp(UserInterface.defaultRecords);
					System.out.println("Data has been updated!");
					break;
				}
				System.out.println("Update cancel!");
			}catch(Exception e){
				
			}
			break;
		case "D":
			try{
				String[] d=arr[1].split("-");
				if(d.length>1){
					System.out.println("Invalid format for shortcut updating data.");
					break;
				}
				if(d[0].equalsIgnoreCase("a")){
					OperationsControl.deleteAll();
					break;
				}
				int id=Integer.parseInt(d[0]);
				OperationsControl.delete(id);
				
			}catch(Exception e){
				System.out.println("Invalid format for shortcut updating data.");
			}
			break;
		case "S":
			try{
				System.out.println("Searching...");
				if(arr.length>2){
					System.out.println("Invalid format for shortcut updating data.");
					break;
				}
				System.out.println(arr[1]);
				ArrayList<Product> result=OperationsControl.search(UserInterface.defaultRecords, arr[1]);
				if(result.size()==0){
					System.out.println("Search not found!");
					break;
				}
				UserInterface.prdRecords=result;
				src.currentPage.calculate(UserInterface.prdRecords.size());
				OperationsControl.displayProduct(UserInterface.prdRecords, src.currentPage);
			}catch(Exception e){
				
			}
			break;
		}
	}
	

	public static void showHelp(){
		System.out.printf("%70s","+==============================================================================================+\n");
		System.out.println("|	Helps                                                                                  |");
		System.out.println("|	1.Write                                                                                |");
		System.out.println("|	  - Press w and then enter to write normally.                                          |");
		System.out.println("|	  - Press w#name-unitprice-quantity-content to write with shortcut use ; for enter.    |");
		System.out.println("|	2.Read                                                                                 |");
		System.out.println("|	  - Press r and then enter to read normally.                                           |");
		System.out.println("|	  - Press r#id and then enter to read with shortcut.                                   |");
		System.out.println("|	3.Update                                                                               |");
		System.out.println("|	  - Press u and then enter to update normally.                                         |");
		System.out.println("|	  - Press u#id and then enter to update with shortcut.                                 |");
		System.out.println("|	  - Press u#id-[name]-[unitprice]-[quantity]-[content]) to update with shortcut.       |");
		System.out.println("|	4.Delete                                                                               |");
		System.out.println("|	  - Press d and then enter to delete normally.                                         |");
		System.out.println("|	  - Press d#id and then enter to delete with shortcut.                                 |");
		System.out.println("|	  - Press d#a enter to delete all records.                                             |");
		System.out.println("|	5.Search                                                                               |");
		System.out.println("|	  - Press s and then enter to serach normally.                                         |");
		System.out.println("|	  - Press s# then enter to search with shortcut.                                       |");
		System.out.printf("%70s","+==============================================================================================+\n");
		
		String gotomenu;
		Scanner cin=new Scanner(System.in);
		System.out.println("Press b to go back main menu\n");
		System.out.print(">");
		do{
			gotomenu = cin.next();
		}while(!gotomenu.toUpperCase().equals("B"));
		
	}
}
