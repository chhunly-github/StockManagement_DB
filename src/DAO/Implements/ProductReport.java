package DAO.Implements;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import DAO.Interfaces.IReport;


public class ProductReport implements IReport{
	private String reportName;
	public String toString(){
		return reportName;
	}
	
	
	@Override
	public boolean create(Object obj) {
		if(obj==null){
			return false;
		}
		/*ArrayList<Product> prds;
		try{
			prds=(ArrayList<Product>) obj;
		}catch(Exception e){
			return false;
		}
		for(Product p:prds){
			System.out.println(p.toString());
		}*/
		ObjectOutputStream oos=null;
		try{
			this.reportName=setName();
			FileOutputStream fos=new FileOutputStream("Reports/"+this.reportName);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			oos=new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return true;
		}catch(IOException e){
			
		}finally{
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public Object get(String reportFileName) {
		ObjectInputStream ois=null;
		try{
			FileInputStream fis=new FileInputStream(reportFileName);
			BufferedInputStream bis=new BufferedInputStream(fis);
			ois=new ObjectInputStream(bis);
			Object obj=ois.readObject();
			return obj;
		}catch(IOException e){
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
