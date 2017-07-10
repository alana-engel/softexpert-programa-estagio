package br.com.softexpert.library.user.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;


public class SearchBook{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private BookJPA books= new BookJPA(manager);

	public void searchByCode(){
		Book book =new Book();
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do livro: "));
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
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o livro.");
			e.printStackTrace();
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
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o livro.");
			e.printStackTrace();
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
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o livro.");
			e.printStackTrace();
		}
	}
}