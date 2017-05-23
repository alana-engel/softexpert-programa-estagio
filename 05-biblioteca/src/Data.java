import java.io.Serializable;
import java.util.Calendar;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	int dia;
	int mes;
	int ano;
	Calendar calendario = Calendar.getInstance();

	void DataA() {  

		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.MINUTE,0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND,0);
		dia = calendario.get(Calendar.DAY_OF_MONTH);  
		mes = calendario.get(Calendar.MONTH);  
		ano = calendario.get(Calendar.YEAR);  

	}  
	boolean ComparaData() {  
		String entrada = ""+dia+"/"+mes+"/"+ano;  

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
	public String toString() {
		String data="";
		data=data+dia+"/"+mes+"/"+ano;
		return data;
	}
}
