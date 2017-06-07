package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class CreateBook{
	private DateOperations dateOperations = new DateOperations();
	private BooksInMemory books= new BooksInMemory();
	public void create(){
		String date;
		boolean dateT=false;
		Book book = new Book();
		book.setTitle(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		book.setSummary(JOptionPane.showInputDialog("Digite o resumo do livro: "));
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
		book.setAuthorsList(books.addAuthor(books.qAuthors()));
		book.setCategory(books.addCategory());
		if(books.create(book)){
			JOptionPane.showMessageDialog(null, "Livro cadastrado.");
		}else{
			JOptionPane.showMessageDialog(null, "Livro não cadastrado, os campos Titulo e Local devem ser preenchidos.");
		}
	}

}
