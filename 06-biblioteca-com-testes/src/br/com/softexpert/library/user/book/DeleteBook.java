package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class DeleteBook{
	private Operations<Book> books= new BooksInMemory();
	public void delete(){
		String title=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido.")); 
		if(books.delete(title)){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
