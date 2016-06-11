package Pagination;

public class Pagination {
	////------------------------------fields---------------------------///
	int totalRecord;
	int totalPage;
	int currentPage = 1;
	int recordPerPage = 5;
			
	//--------------encapsulation------------/
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int offSet(){
		int of=(currentPage-1)*recordPerPage;
		return of;
	}
	public int getLastData(){
		if(currentPage==totalPage){
			return totalRecord;
		}
		return currentPage*recordPerPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage>totalPage||currentPage<=0)
			return;
		this.currentPage = currentPage;
	}


	public int getRecordPerPage() {
		return recordPerPage;
	}


	public void setRecordPerPage(int recordPerPage) {
		if (recordPerPage <= 0){
			return;
		}
		this.recordPerPage = recordPerPage;
		calculate();
	}


	
	public int getTotalRecord() {
		return totalRecord;
	}


	public void setTotalRecord(int totalRecord) {
		if(totalRecord<0){
			return;
		}
		this.totalRecord = totalRecord;
		calculate();
	}


	public int getTotalPage() {
		return totalPage;
	}


	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/

	//--------------------------------------//

	
	


	///-----------------------constructors-------------------------/////
	
	public Pagination(int records){
		totalRecord = records;
		calculate();
	}
	
	public Pagination(int records, int recordPerPage){
		calculate(records, recordPerPage);
	}
	
	
	///-----------------------------------------------------------/////
	
	//-------------calculate page----------//
	public void calculate(){
		if(totalRecord==0){
			this.currentPage=0;
			this.totalPage=0;
			return;
		}
		this.currentPage=1;
		if ((totalRecord%recordPerPage) > 0) {
			this.totalPage = (totalRecord / recordPerPage)+ 1;
			return;
		}
		this.totalPage = (int)(totalRecord / recordPerPage);
	}
	
	public void calculate(int records){
		totalRecord = records;
		if(totalRecord==0){
			this.currentPage=0;
			this.totalPage=0;
			return;
		}
		this.currentPage=1;
		if ((totalRecord % recordPerPage) != 0) {
			this.totalPage = (int)(totalRecord / recordPerPage)+ 1;
			//System.out.println(this.totalPage);
			return;
		}
		
		this.totalPage = (int)(totalRecord / recordPerPage);
		
		//System.out.println(this.totalPage);
	}
	
	public void calculate(int records, int recordPerPage){
		totalRecord = records;
		if(totalRecord==0){
			this.currentPage=0;
			this.totalPage=0;
			return;
		}
		this.currentPage=1;
		this.recordPerPage = recordPerPage;
		if ((totalRecord % this.recordPerPage) > 0) {
			this.totalPage = (int)(totalRecord / this.recordPerPage)+ 1;
			return;
		}
		this.totalPage = (int)(totalRecord / this.recordPerPage);
	}
		
	
	//-----------------------------------//	
	
	
	
	
	///---------paging operation---------//
	public int nextPage(){
		if (currentPage < totalPage){ 
			currentPage++;
		}
		return currentPage;	
	}
	
	public int previousPage(){
		if (currentPage > 1) {
			currentPage--;
		}
		return currentPage;
	}
	
	public int firstPage(){
		currentPage = 1;
		return currentPage;
	}
	
	public int lastPage(){
		currentPage = totalPage;
		return currentPage;
	}
	
	public int gotoPage(int page){
		if (page <= totalPage && page!=1) {
			currentPage = page;
		}
		return currentPage;
		
	}
	
	///---------------------------------//
}
