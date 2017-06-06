package br.com.softexpert.library.operations.file;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import br.com.softexpert.library.operations.memory.*;
public class Code {
	SequentialCode code = new SequentialCode();
	
	void saveInFileCodeAuthor() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodeA.dat"));
			grava.writeObject(code.authorCode());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	}
	void saveInFileBarcodeAuthor() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("BCode.dat"));
			grava.writeObject(code.barcode());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	}
	void saveInFileCodeBook() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodeB.dat"));
			grava.writeObject(code.bookCode());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	}
	void saveInFileCodeCategory() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodeC.dat"));
			grava.writeObject(code.categoryCode());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	}
	
	
	void getBarcode() {
		try{
			FileInputStream cod= new FileInputStream("BCode.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			code.barcode();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
	void getBookCode() {
		try{
			FileInputStream cod= new FileInputStream("CodeB.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			code.bookCode();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
	void getAuthorCode() {
		try{
			FileInputStream cod= new FileInputStream("CodeA.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			code.authorCode();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
	void getCategoryCode() {
		try{
			FileInputStream cod= new FileInputStream("CodeC.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			code.categoryCode();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
	
}
