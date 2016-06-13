package UI_Main;

import DAO.DbConnection;
import DAO.ProductDAO;
import DAO.Implements.GetAllByPage;
import DAO.Interfaces.IGetData;
import Pagination.Pagination;

public class UserInterface {
	Pagination page;
	ProductDAO proDao;
	IGetData igetdata;
/*------------------constructor------------------------*/
	
	public UserInterface(){
		
		proDao=new ProductDAO();
		
		DbConnection.createDatabase(ProductDAO.databaseName());
		DbConnection.createTable(ProductDAO.tableName(),ProductDAO.databaseName());
		//proDao.insertTestData();
		page=new Pagination(proDao.numberOfProduct());
		Menu();
		
			
	}
/*---------------end of constructor------------------------*/
	
	public void Menu(){
		
		String choice;
		//display data here
		do{
			System.out.println();
			System.out.printf("+================================================================================================+\n");
			System.out.printf("| *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast | S)earch  |\n");
			System.out.printf("|                                                                                                |\n");
			System.out.printf("|  G)oto | Se)t row |  Sh)otScreen | V)iew Shots | Re)ports | Exp)ort to report | H)elp | E)xit  |\n");
			System.out.printf("+================================================================================================+\n");
			System.out.println();
			choice=Input.inputString("Option >");	//cin.nextLine();
			if(choice.contains("#")){
				UI_Function.shortcut(choice,this);
				continue;
			}
			switch (choice.toUpperCase()) {
				case "*":/*------------------------------display all data---------------------*/
					new ClearScreen();
					page.calculate(proDao.numberOfProduct());
					igetdata=new GetAllByPage();
					UI_Function.display(this);
					break;
				case "W":/*------------------------------write new data---------------------*/
					UI_Function.write(this);
					break;
				case "R":/*------------------------------read detail of a data---------------------*/
					UI_Function.read(this);
					break;
				case "U":/*------------------------------update---------------------*/
					UI_Function.update(this);
					break;
				case "D":/*------------------------------delete data---------------------*/
					UI_Function.delete(this);
					break;
				case "F":/*------------------------------go to first page---------------------*/
					new ClearScreen();
					UI_Function.first(this);
					break;
				case "P":/*------------------------------go to previous page---------------------*/
					new ClearScreen();
					UI_Function.previous(this);
					break;
				case "N":/*------------------------------go to next page---------------------*/
					new ClearScreen();
					UI_Function.next(this);
					break;
				case "L":/*------------------------------go to last page---------------------*/
					new ClearScreen();
					UI_Function.last(this);
					break;
				case "S":/*------------------------------search product---------------------*/
					//System.out.println("Input search >");
					UI_Function.Search(this);
					break;
				case "G":/*------------------------------go to a specific page---------------------*/
					new ClearScreen();
					UI_Function.gotoPage(this);
					break;
				case "SE":/*------------------------------set number of rows in a page---------------------*/
					new ClearScreen();
					UI_Function.setRow(this);
					break;
/*				case "SA":------------------------------save any changed to using file---------------------
					UI_Function.save();
					break;*/
				case "SH":/*------------------------------backup data to backup file---------------------*/
					System.out.println("shot screen");
					UI_Function.shotScreen();
					break;
				case "V":/*------------------------------restore data from a backup file to using file---------------------*/
					System.out.println("view image");
					UI_Function.viewScreenShot(this);
					break;
				case "RE":/*------------------------------restore data from a backup file to using file---------------------*/
					
					System.out.println("report");
					UI_Function.getReport(this);
					break;
				case "EXP":/*------------------------------restore data from a backup file to using file---------------------*/
					System.out.println("export report");
					UI_Function.exportReport(this);
					break;
				
				case "H":/*------------------------------information on using shortcut key---------------------*/
					new ClearScreen();
					UI_Function.showHelp();
					break;
				case "E":/*------------------------------exit the program---------------------*/
					if(!UI_Function.exit())
						choice="";
					break;
			default:
				break;
			}
			//System.out.print("Option >");
		}while(!choice.equals("e"));
	}
	
	
	public static void main(String[] args){
		new UserInterface();
		
		
	}
}
