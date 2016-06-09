package Product;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

///starting class product
public class Product implements Serializable{
/*--------------------------------fields-------------------------*/
	private int id;
	private String name;
	private float unitPrice;
	private float stockQty;
	private Date impDate;
	private String content;
/*-----------------------------end of fields-------------------------*/
	
/*--------------------------------constructors-------------------------*/
	public Product(int id, String name, float up, float stockQty,Date impDate,String content){
		this.id=id;
		this.name=name;
		this.unitPrice=up;
		this.stockQty=stockQty;
		this.impDate=impDate;
		this.content=content;
	}
	public Product(){
		this.impDate=new Date();
	}
	

/*--------------------------------end of constructors-------------------------*/
	
/*------------------------------static methods-------------------------*/
	public static String[] getFields(){
		String[] str={"ID","NAME","UNIT PRICE","STOCK QUANTITY","IMPORTED DATE"};
		return str;
		
	}
	
/*---------------------------end of static methods-------------------------*/
	
	
/*------------------------------instance methods-------------------------*/
	public Object[] getData(){	///return array of data
		Object[] obj={this.id,this.name,this.unitPrice,this.stockQty,(this.impDate.getYear()+1900+"/"+(this.impDate.getMonth()+1)+"/"+ this.impDate.getDate())};
		return obj;
	}
	public void setData(String data) throws Exception{
		
			String[] arr=data.split("-");
			this.id=Integer.parseInt(arr[0].trim());
			this.name=arr[1].trim();
			this.unitPrice=Float.parseFloat(arr[2].trim());
			this.stockQty=Float.parseFloat(arr[3].trim());
			this.content=arr[4].trim();
		
	}
    
    @SuppressWarnings("deprecation")
	@Override
	public String toString(){
		return this.id+"/"+this.name+"/"+this.unitPrice+"/"+this.stockQty+"/"+(this.impDate.getYear()+1900+"/"+(this.impDate.getMonth()+1)+"/"+ this.impDate.getDate());
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getStockQty() {
		return stockQty;
	}

	public void setStockQty(float stockQty) {
		this.stockQty = stockQty;
	}

	public Date getImpDate() {
		return impDate;
	}

	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
/*---------------------------end of instance methods-------------------------*/
}
//end of class product

