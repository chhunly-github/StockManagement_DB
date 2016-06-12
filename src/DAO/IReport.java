package DAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface IReport {
	boolean create(Object obj);
	
	default String setName(){
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hhmmss a");
		return "report-"+formatter.format(now.getTime())+".rpt";
	}
	Object get(String reportFileName);
}
