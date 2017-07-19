package br.com.softexpert.client.book;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;


public class SearchBook{
	private BookJPA books= new BookJPA(Manager.getEntityManager());

	public void searchByCode(){
		Book book =new Book();
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		try {
			book=books.searchByCode(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, book);
	}
	
	public void searchByTitle(){
		String title=(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		List<Book> list = new ArrayList<Book>();
		try {
			list=books.searchByTitle(title);
			String print="";
			for (int i=0;i<list.size();i++){
				Book book = new Book();
				book=list.get(i);
				print=print+book;
			}
			JOptionPane.showMessageDialog(null, print); 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
	}
	public void searchByCategory(){
		String description=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		List<Book> list = new ArrayList<Book>();
		try {
			list=books.searchByCategory(description);
			String print="";
			for (int i=0;i<list.size();i++){
				Book book = new Book();
				book=list.get(i);
				print=print+book;
			}
			JOptionPane.showMessageDialog(null, print);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
	}
	public void searchByAuthor(){
		String n=(JOptionPane.showInputDialog("Digite o autor do livro: "));
		List<Book> list = new ArrayList<Book>();
		try {
			list=books.searchByAuthor(n);
			String print=new String();
			for (int cont=0;cont<list.size();cont++){
				Book book = new Book();
				book=list.get(cont);
				print=print+book;
			}
			JOptionPane.showMessageDialog(null, print);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
	}
}