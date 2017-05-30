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
	
	public Date retornaDataTransformada(String data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d =sdf.parse(data);
		} catch (ParseException e) {
			System.err.println("Erro ao transformar a data");
		}
		System.out.println(sdf.format(d));
		
		return d;
		
}
	public boolean ComparaData(String entrada) {  

		String[] data = entrada.split("\\/");  
		Calendar dataC = Calendar.getInstance();  
		dataC.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[0]));  
		dataC.set(Calendar.MONTH, Integer.parseInt(data[1])-1);  
		dataC.set(Calendar.YEAR, Integer.parseInt(data[2]));  
		dataC.set(Calendar.HOUR, 0);
		dataC.set(Calendar.MINUTE,0);
		dataC.set(Calendar.SECOND, 0);
		dataC.set(Calendar.MILLISECOND,0);
		if(calendario.compareTo(dataC)>=0){
			return true;
		}else{
			return false;
		}
	}  
}
