package br.com.softexpert.library.operations.memory;

public class SequentialCode {
	static int codL=0;
	static int codB=0;
	static int codC=0;
	static int codA=0;
	
	public int bookCode(){
		codL=codL+1;
		return codL;
	}
	public int barcode(){
		codB=codB+1;
		return codB;
	}
	public  int categoryCode(){
		codC=codC+1;
		return codC;
	}
	public int authorCode(){
		codA=codA+1;
		return codA;
	}
}
