package br.com.softexpert.library.operations.file;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.softexpert.library.entity.Book;

import br.com.softexpert.library.repository.Repository;

public class BooksInFile {
	Repository repository = new Repository();

	
	void saveBooksInFile() {
		try{
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("books.dat"));
			s.writeObject(repository.getBooks());
			s.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
	}
	@SuppressWarnings("unchecked")
	void getBooks() {
		try{
			FileInputStream book= new FileInputStream("books.dat");
			ObjectInputStream books = new ObjectInputStream(book);
			repository.setBooks((ArrayList<Book>) books.readObject());
			books.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
	}
}
