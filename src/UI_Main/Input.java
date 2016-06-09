package UI_Main;

import java.util.Scanner;

public class Input {

	//------------with this function, you can input float without errors-----------------///
		public static float inputFloat(String str){
			float num;
			Scanner sc=new  Scanner(System.in);
			while(true){
				try{
					System.out.print(str+": ");
					num=sc.nextFloat();
					if(num<0){
						System.out.println("Negative value! Try positive value!");
						continue;
					}
					return num;
				}catch(Exception ex){
					System.out.println("input "+str+" invalid");
					sc.nextLine();
				}
			}
		}
		
		//-----------------------input any  string here----------------------///
		public static String inputString(String str){
			String st;
			Scanner sc=new  Scanner(System.in);
			while(true){
				try{
					System.out.print(str+": ");
					st=sc.nextLine();
					if(st.trim()==null)
						continue;
					return st;
				}catch(Exception ex){
					System.out.print("input "+str+" invalid");
				}
			}
		}
		
		//--------------------here you can input content break with ; sign-----------------//
		public static String inputContent(){
			String content="";
			Scanner sc=new  Scanner(System.in);
			do {
				content+=sc.nextLine();
				content+="\n";
			} while (!content.contains(";"));
			
			return content.split(";")[0].trim();
			
		}
}

