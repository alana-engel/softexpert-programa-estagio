package br.com.softexpert.library.library;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateOperations implements Serializable{
	private static final long serialVersionUID = 1L;
	public int d;
	public int m;
	public int y;
	static Calendar calendar = Calendar.getInstance();

	void DataA() {  

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		d = calendar.get(Calendar.DAY_OF_MONTH);  
		m = calendar.get(Calendar.MONTH);  
		y = calendar.get(Calendar.YEAR);  

	}  
	public boolean dateConverter(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
}
	public Date getConvertedDate(String data){
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d =sdf.parse(data);
		} catch (ParseException e) {
		
		}
		return d;
		
}
	public boolean CompareDate(Date date) {  

		Calendar dateC = Calendar.getInstance(); 
		dateC.setTime(date);

		if(calendar.compareTo(dateC)>=0){
			return true;
		}else{
			return false;
		}
	}  
}

