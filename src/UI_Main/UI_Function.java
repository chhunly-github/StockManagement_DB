package UI_Main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.postgresql.jdbc2.ArrayAssistantRegistry;

import DAO.GetByContent;
import DAO.GetById;
import DAO.GetByName;
import DAO.GetByReport;
import DAO.IGetData;
import DAO.IReport;
import DAO.ProductReport;
import Pagination.Pagination;
import Product.Product;
import Viewer.ImageViewer;
import Viewer.Viewer;

public class UI_Function {
	private UI_Function(){
		
	}
	public static void display(UserInterface ui){
		System.out.println("Display Data");
		//ui.isSearch=false;
		ui.page.calculate(ui.proDao.numberOfProduct());
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	
	//----------------------------------write new product----------------------//
	public static void write(UserInterface ui){
		System.out.println("Write Data");
		
		System.out.println("--------------------------**************************-----------------------");
		System.out.println("-------------------------------Add new product-------------------------");
		
		String name=Input.inputString("Input name");
		float unit=Input.inputFloat("Unit Price");
		float qty=Input.inputFloat("Stock quantity:");
		System.out.print("Content: ");
		String content=Input.inputContent();
		//Date date=Input.inputDate("Input date:");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String date=sdf.format(new Date());
		//System.out.println(sdf.format(new java.util.Date()));
		if(!ui.proDao.insertData(new Product(0,name,unit,qty,date,content))){
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
		Product prds=new GetById(rId).getProduct();
		if(prds==null){
			System.out.println("Could not find product id:"+rId);
			return;
		}
		Viewer.viewProduct(prds);
		
	}
	
	//--------------------------------delete data------------------------//
	public static void delete(UserInterface ui){
		System.out.println("Delete Data");
		int dId=(int)Input.inputFloat("Product id");
		if(ui.proDao.searchCountProductById(dId)==0){
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
		int prds=ui.proDao.searchCountProductById(uId);
		Product pro=new GetById(uId).getProduct();
		if(pro==null){
			System.out.println("Cannot find id you entered. Please try again.");
			return;
		}
		Scanner sc=new Scanner(System.in);
		String ch="";
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
		//Viewer.displayProduct(ui.proDao.getDataByPage(ui.page), ui.page);
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	
	//-------------------------------------go to previous page--------------------------//
	public static void previous(UserInterface ui){
		System.out.println("Previous Data");
		ui.page.previousPage();
		//Viewer.displayProduct(ui.proDao.getDataByPage(ui.page), ui.page);
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	
	//-------------------------------------go to first page---------------------------//
	public static void first(UserInterface ui){
		System.out.println("First Data");
		ui.page.firstPage();
		
		//Viewer.displayProduct(ui.proDao.getDataByPage(ui.page), ui.page);
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	
	//----------------------------------------go to last page--------------------//
	public static void last(UserInterface ui){
		System.out.println("Last Data");
		ui.page.lastPage();
		//Viewer.displayProduct(ui.proDao.getDataByPage(ui.page), ui.page);
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	

	//-----------------goto page?----------------------*/
	public static void gotoPage(UserInterface ui){
		int page=(int)Input.inputFloat("Go to page");
		ui.page.calculate(ui.proDao.numberOfProduct());
		if(ui.page.getTotalPage()<page||page<=0){
			System.out.println("The page you entered is not invalid! Please try again!(1 - "+ui.page.getTotalPage()+")");
			return;
		}
		ui.page.setCurrentPage(page);
		//Viewer.displayProduct(ui.proDao.getDataByPage(ui.page), ui.page);
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
	}
	///----------------------------------------set row in a page---------------------//
	public static void setRow(UserInterface ui){
		System.out.println("Set rows Data");
		int rowSet=(int)Input.inputFloat("row in a page");
		ui.page.setRecordPerPage(rowSet);
	}
	
	
///------------------------------------------backup file here------------------------------------///
	public static void shotScreen(){
		try {
			System.out.println("File has been save to file: "+new ScreenShoter().robo());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Failed to create screenshot");
		}
	}
	//---------------------------------------view screenshot-------------------------//
public static void viewScreenShot(UserInterface ui){
		
		File screenShot=new File("ScreenShots");
		File[] files=screenShot.listFiles();
		if(files.length==0){
			System.out.println("There's no report file here!");
			return;
		}
		int ind=0;
		for(File s:files){
			System.out.println((ind+1)+". "+s.getName());
			ind++;
		}
		while(true){
			System.out.println("Choose report file by enter number to view:\nType \"c\" to go back");
			try{
				String s;
				s=Input.inputString("Your choice");
				if(s.equalsIgnoreCase("c")){
					return;
				}
				ind=Integer.parseInt(s);
				if(ind<=0||ind>files.length){
					System.err.println("\nChoice you enter is not available! Try again!\n");
					continue;
				}
				//new ImageViewer(files[ind-1].toString()).view();
				File file = new File (files[ind-1].toString());
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//break;
				
			}catch(Exception e){
				System.err.println("\nInvalid input! Try again!\n");
			}
		}
		
		
	}
	
	//--------------------------------------export report to a file--------------//
	public static void exportReport(UserInterface ui){
		IReport report=new ProductReport();
		ArrayList<Product> exp=ui.igetdata.getData(ui.page, "");
		if(exp.isEmpty()){
			System.out.println("Cannot export empty data.");
			return;
		}
		if(!report.create(exp)){
			System.out.println("Failed to create report!");
			return;
		}
		System.out.println("Report has been write to file: "+report.toString());
	}
	
	//-----------------------------------------restore data here----------------------------------///
	public static void getReport(UserInterface ui){
		
		File reports=new File("Reports");
		if(reports.listFiles().length==0){
			System.out.println("There's no report file here!");
			return;
		}
		int ind=0;
		for(File s:reports.listFiles()){
			System.out.println((ind+1)+". "+s.getName());
			ind++;
		}
		while(true){
			System.out.println("Choose report file by enter number to view:\nType \"c\" to go back");
			try{
				String s;
				s=Input.inputString("Your choice");
				if(s.equalsIgnoreCase("c")){
					return;
				}
				ind=Integer.parseInt(s);
				if(ind<=0||ind>reports.listFiles().length){
					System.err.println("\nChoice you enter is not available! Try again!\n");
					continue;
				}
				break;
				
			}catch(Exception e){
				System.err.println("\nInvalid input! Try again!\n");
			}
		}
		ArrayList<Product> prd=new ArrayList<>();
		try{
			prd=(ArrayList<Product>) new ProductReport().get(reports.listFiles()[ind-1].getPath());
		}catch(Exception e){
			System.out.println("Unknown file type! This file is not a kind of product report.");
			//return;
		}
		if(prd==null){
			System.out.println("Empty!");
			return;
		}
		ui.igetdata=new GetByReport(prd);
		ui.page.calculate(prd.size());
		Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
		
	}
	
	////----------------------------------------------input search data-------------------------------------///
	public static void Search(UserInterface ui){
		String choice;
		do{
			System.out.println("--------------------------**************************-----------------------");
			System.out.println("--------------------------Student searching data------------------------");
			System.out.println("1.)Search by id");
			System.out.println("2.)Search by name");
			System.out.println("3.)Search by name and content");
			System.out.println("exit.) back to menu");
			choice=Input.inputString("Option >");
			switch(choice){
			case "1":
				
				int id=(int)Input.inputFloat("Input search id:");
				ui.igetdata=new GetById(id);
				int idFound=ui.proDao.searchCountProductById(id);
				
				if(idFound==0){
					System.out.println("Could not find data id:"+id);
					break;
				}
				//Viewer.displayData(Product.getFields(), idFound.toArray());
				ui.page.calculate(idFound);
				Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
				System.out.println("Total found:"+idFound+" products");
				choice="exit";
				break;
			case "2":
				String name=Input.inputString("Input search name:");
				ui.igetdata=new GetByName(name);
				int nameFound=ui.proDao.searchCountProductByName(name);
				if(nameFound==0){
					System.out.println("Could not match any name:"+name);
					break;
				}
				ui.page.calculate(nameFound);
				//Viewer.displayData(Product.getFields(), nameFound.toArray());
				//Viewer.displayProduct(nameFound, ui.page);
				Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
				System.out.println("Total found:"+nameFound+"products");
				choice="exit";
				break;
			case "3":
				String rand=Input.inputString("Input search word:");
				ui.igetdata=new GetByContent();
				int contentFound=ui.proDao.searchCountProductByContent(rand);
				if(contentFound==0){
					
					System.out.println("Could not match any word:"+rand);
					break;
				}
				ui.page.calculate(contentFound);
				//Viewer.displayData(Product.getFields(), randFound.toArray());
				//Viewer.displayProduct(randFound, ui.page);
				Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
				System.out.println("Total found:"+contentFound+" products");
				choice="exit";
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
	public static void shortcut(String c, UserInterface ui){
		String[] arr=c.split("#");
		switch(arr[0].toUpperCase()){
		case "W":
			try{
				Product p=new Product();
				//int id=ui.proDao.idGenerated();
				int id=0;
				p.setData(id+"-"+arr[1]);
				System.out.println("Writing data...");
				if(!ui.proDao.insertData(p)){
					System.out.println("Failed to insert data!");
					return;
				};
				System.out.println("Writing data success!");
			}catch(Exception e){
				System.out.println("Invalid format for shortcut writing data.");
				break;
			}
			break;
		case "R":
			try{
				int id=Integer.parseInt(arr[1]);
				//Viewer.displayProduct(ui.proDao.searchProductById(id), ui.page);
				Viewer.viewProduct(new GetById(id).getProduct());
			}catch(Exception e){
				System.out.println("Product id you entered is not available!");
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
				if(ui.proDao.searchCountProductById(id)==0){
					System.out.println("Cannot find id you entered.");
					break;
				}
				
				if(!Input.Confirmation("Are you sure to udpate")){					
					System.out.println("Update cancel!");
					break;
				}
				System.out.println("Updating...");
				Product temp=new Product();
				temp.setData(arr[1]);
				ui.proDao.updateData(temp);
				System.out.println("Data has been updated!");
			}catch(Exception e){
				System.out.println("Failed to update data.");
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
					if(!Input.Confirmation("Are you sure to delete all data")){
						break;
					}
					ui.proDao.deleteAll();
					break;
				}
				int id=Integer.parseInt(d[0]);
				if(ui.proDao.searchCountProductById(id)==0){
					System.out.println("Data not found!");
					break;
				}
				if(!Input.Confirmation("Are you sure to delete the product"));
				if(!ui.proDao.deleteDataById(id)){
					System.out.println("Deleting data failed.");
					break;
				}
				System.out.println("Data has been successfully deleted!");
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
				int result=ui.proDao.searchCountProductByContent(arr[1]);
				if(result==0){
					System.out.println("Search not found!");
					break;
				}
				ui.igetdata=new GetByName(arr[1]);
				ui.page.calculate(result);
				Viewer.displayProduct(ui.igetdata.getData(ui.page, ""), ui.page);
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
