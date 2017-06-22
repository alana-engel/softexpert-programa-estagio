package br.com.softexpert.library.library;

import java.io.Serializable;
import java.util.Date;

import br.com.softexpert.library.exception.DateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateOperations implements Serializable{
	private static final long serialVersionUID = 1L;
	public int d;
	public int m;
	public int y;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Calendar calendar = Calendar.getInstance();

	public Date getConvertedDate(String data) throws DateException{
		Date d = null;
		try {
			d =sdf.parse(data);
		} catch (ParseException e) {
			throw new DateException("Não foi possível converter data. Verifique o formato dd/mm/yyyy.");
		}
		return d;

	}
	public boolean CompareDate(Date date){  

		Calendar dateC = Calendar.getInstance(); 
		dateC.setTime(date);

		if(calendar.compareTo(dateC)>=0){
			return true;
		}
		return false;
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
	public String convertDateToString(Date d){
		String date=sdf.format(d);
		return date;
	}
}

