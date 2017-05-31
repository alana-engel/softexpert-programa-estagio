package br.com.softexpert.biblioteca.outrasoperacoes;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	public int dia;
	public int mes;
	public int ano;
	static Calendar calendario = Calendar.getInstance();

	void DataA() {  

		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.MINUTE,0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND,0);
		dia = calendario.get(Calendar.DAY_OF_MONTH);  
		mes = calendario.get(Calendar.MONTH);  
		ano = calendario.get(Calendar.YEAR);  

	}  
	public boolean dataTransformada(String data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			sdf.parse(data);
		} catch (ParseException e) {
			return false;
		}
		return true;
}
	public Date retornaDataTransformada(String data){
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d =sdf.parse(data);
		} catch (ParseException e) {
			//System.err.println("Erro");
		
		}
		return d;
		
}
	public boolean ComparaData(Date entrada) {  

		Calendar dataC = Calendar.getInstance(); 
		dataC.setTime(entrada);

		if(calendario.compareTo(dataC)>=0){
			return true;
		}else{
			return false;
		}
	}  
}
