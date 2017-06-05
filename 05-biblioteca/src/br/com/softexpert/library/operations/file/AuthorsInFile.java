package br.com.softexpert.library.operations.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.repository.Repository;

public class AuthorsInFile {
	Repository repository = new Repository();

	void saveAuthorsInFile() {
		try{
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("authors.dat"));
			s.writeObject(repository.getAuthors());
			s.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	
		
	}
	
	@SuppressWarnings("unchecked")
	void getAuthors() {
		try{
			FileInputStream author= new FileInputStream("authors.dat");
			ObjectInputStream authors = new ObjectInputStream(author);
			repository.setAuthors((ArrayList<Author>) authors.readObject());
			authors.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	
	}

}
