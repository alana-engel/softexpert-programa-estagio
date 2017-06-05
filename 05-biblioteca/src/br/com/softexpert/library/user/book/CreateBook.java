package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class CreateBook{
	private DateOperations dateOperations = new DateOperations();
	private Operations<Book> books= new BooksInMemory();
	public void create(){
		String date;
		boolean dateT=false;
		Book book = new Book();
		book.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		book.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
		if(pag.isEmpty()){
			book.setPages(0);
		}else{
			book.setPages(Integer.parseInt(pag));
		}
		book.setLocation(JOptionPane.showInputDialog("Digite o Local:"));
		do{
			date = JOptionPane.showInputDialog("Digite a data de aquisição no formato dd/MM/yyyy:");
			if(date.isEmpty()){
				date = "01/01/01";
				dateT=true;
			}else{
				dateT=dateOperations.dateConverter(date);
			}
		}while(dateT==false);
		if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
			book.setAcquisition(dateOperations.getConvertedDate(date));
		}else{
			JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
		}
		if(books.create(book)){
			JOptionPane.showMessageDialog(null, "Livro cadastrado.");
		}else{
			JOptionPane.showMessageDialog(null, "Livro não cadastrado, os campos Titulo e Local devem ser preenchidos.");
		}
	}

}
