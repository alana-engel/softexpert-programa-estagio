package br.com.softexpert.client.main;

import javax.swing.JOptionPane;

import br.com.softexpert.client.book.SearchBook;
import br.com.softexpert.library.library.Order;

public class Menu2 {
	private SearchBook searchBook = new SearchBook();
	private Order order = new Order();
	public void menuSearchBook(){
		order.ordena();
		int option=0;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar pelo Código\n"
					+ "2-Buscar pelo Título\n"
					+ "3-Buscar pela Categoria\n"
					+ "4-Buscar pelo Autor\n"
					+ "5-Voltar"));
			switch(option){
			case 1:searchBook.searchByCode();;break;
			case 2:searchBook.searchByTitle();break;
			case 3:searchBook.searchByCategory();break;
			case 4:searchBook.searchByAuthor();break;
			}
		}while(option!=5);
	}
}
