package br.com.softexpert.library.operations.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.repository.Repository;

public class CategoriesInFile {

	Repository repository = new Repository();
	void saveCategoryInFile() {
		
		try{
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("categories.dat"));
			s.writeObject(repository.getCategories());
			s.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	
	}
	
	@SuppressWarnings("unchecked")
	void getCategories() {
		try{
			FileInputStream category= new FileInputStream("categories.dat");
			ObjectInputStream categories = new ObjectInputStream(category);
			repository.setCategories((ArrayList<Category>) categories.readObject());
			categories.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
}