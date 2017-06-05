package br.com.softexpert.library.user.book;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class SearchBook{
	private Books books= new BooksInMemory();

	public void searchByCode(){
		Book book =new Book();
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		book=books.searchByCode(n);
		boolean found=books.found();
		if (!found){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			JOptionPane.showMessageDialog(null, book);
		}
	}
	public void searchByTitle(){
		String title=(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		List<Book> list = new ArrayList<Book>();
		list=books.searchByTitle(title);
		boolean found=books.found();
		if (!found){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			String print="";
			for (int i=0;i<list.size();i++){
				Book book = new Book();
				book=list.get(i);
				print=print+book;
			}
			JOptionPane.showMessageDialog(null, print);
		}
	}
	public void searchByCategory(){
		String description=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		List<Book> list = new ArrayList<Book>();
		list=books.searchByCategory(description);
		boolean found=books.found();
		if (!found){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			String print="";
			for (int i=0;i<list.size();i++){
				Book book = new Book();
				book=list.get(i);
				print=print+book;
			}
			JOptionPane.showMessageDialog(null, print);
		}
	}
	public void searchByAuthor(){
		String n=(JOptionPane.showInputDialog("Digite o autor do livro: "));
		List<Book> list = new ArrayList<>();
		list=books.searchByAuthor(n);
		String print=new String();
		boolean found=books.found();
		if (!found){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}else{
			for (int cont=0;cont<list.size();cont++){
				Book book = new Book();
				book=list.get(cont);
				print=print+book;

			}
			JOptionPane.showMessageDialog(null, print);
		}
	}
}
