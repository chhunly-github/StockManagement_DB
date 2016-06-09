package UI_Main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.ProductDAO;
import Operations.OperationsControl;
import Pagination.Pagination;
import Product.Product;
import Viewer.TableViewer;

public class UserInterface {
	Pagination page;
	ArrayList<Product> currentData;
/*------------------constructor------------------------*/
	
	public UserInterface(){
		
		long st=System.currentTimeMillis();
		long et=System.currentTimeMillis();
		System.out.println((et-st)/1000+" seconds");
		System.out.println("Checking recovery...!");
		page=new Pagination(ProductDAO.numberOfProduct());
		Menu();
			
	}
/*---------------end of constructor------------------------*/
	
	public void Menu(){
		
		String choice;
		OperationsControl.displayProduct(prdRecords, page);
		do{
			System.out.println();
			System.out.printf("%80s","+===================================================================================+\n");
			System.out.printf("%80s","|*)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast|\n");
			System.out.printf("%80s","|                                                                                   |\n");
			System.out.printf("%80s","|     S)earch | G)oto | Se)t row | Sa)ve | B)ackup | Re)store | H)elp | E)xit       |\n");
			System.out.printf("%80s","+===================================================================================+\n");
			System.out.println();
			//System.out.print("Option >");
			//choice="";
			choice=OperationsControl.inputString("Option >");	//cin.nextLine();
			if(choice.contains("#")){
				UI_Function.shortcut(choice,this);
				continue;
			}
			switch (choice.toUpperCase()) {
				case "*":/*------------------------------display all data---------------------*/
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
					UI_Function.first(this);
					break;
				case "P":/*------------------------------go to previous page---------------------*/
					UI_Function.previous(this);
					break;
				case "N":/*------------------------------go to next page---------------------*/
					UI_Function.next(this);
					break;
				case "L":/*------------------------------go to last page---------------------*/
					UI_Function.last(this);
					break;
				case "S":/*------------------------------search product---------------------*/
					//System.out.println("Input search >");
					UI_Function.search(this);
					break;
				case "G":/*------------------------------go to a specific page---------------------*/
					UI_Function.gotoPage(this);
					break;
				case "SE":/*------------------------------set number of rows in a page---------------------*/
					UI_Function.setRow(this);
					break;
				case "SA":/*------------------------------save any changed to using file---------------------*/
					UI_Function.save();
					break;
				case "B":/*------------------------------backup data to backup file---------------------*/
					System.out.println("Backup Data");
					UI_Function.backup();
					break;
				case "RE":/*------------------------------restore data from a backup file to using file---------------------*/
					System.out.println("Restore Data");
					UI_Function.restore();
					break;
				case "H":/*------------------------------information on using shortcut key---------------------*/
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
