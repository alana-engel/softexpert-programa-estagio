package br.com.softexpert.library.library;

public class Barcode {
	private String countryCode;
	private String companyCode;
	private String productCode;
	
	private String getCountryCode(){
		countryCode = "789";
		return countryCode;
	}
	
	private String getCompanyCode(){
		companyCode = "12345";
		return companyCode;
	}

	private String getProductCode(int n){
		String cod ="0"+n;
		int len = cod.length();  
		while(len<4){
		       cod="0"+cod;
		       len++;
		   }
		productCode = cod;
		return productCode;
	}
	
	public String getBarcode(int n){
		String all =getCountryCode()+getCompanyCode()+getProductCode(n);
		char nine = all.charAt(8);
		char ten = all.charAt(9);
		char eleven = all.charAt(10);
		char twelve = all.charAt(11);
		int sumP= 17+Character.getNumericValue(ten)+Character.getNumericValue(twelve);
		int sumO= 22+Character.getNumericValue(nine)+Character.getNumericValue(eleven);
		int x3=sumP*3;
		int results=x3+sumO;
		int c=0;
		while(results%10!=0){
			c++;
			results++;
		}
		String checker =""+c;
		String barcode=getCountryCode()+" "+getCompanyCode()+" "+getProductCode(n)+" "+checker;
		return barcode;
	
	}
}
