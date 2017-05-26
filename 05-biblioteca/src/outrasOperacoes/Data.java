package outrasOperacoes;
import java.io.Serializable;
import java.util.Calendar;

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
	public boolean ComparaData() {  
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
	 public boolean verificaData(int dia, int mes, int ano){
		 boolean dataValida = false;
	      if(dia>31){
	    	  dataValida= false;
	        
	      }
	      else if(mes>12 || mes<1){
	    	  dataValida= false;
		         
		      }
		      else if(((mes==4)||(mes==6)||(mes==9)||(mes==11))&&(dia>30)) {
		    	  dataValida= false;
		         
		      } 
		      else if(mes==2){
		        if(dia>28){
		        	dataValida= false;

		        } 
		      }
		      else{
		    	  dataValida= true;
	  			}
	      return dataValida;
	  }
	
}
