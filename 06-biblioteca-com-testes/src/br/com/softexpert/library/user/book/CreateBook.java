package br.com.softexpert.library.user.book;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class CreateBook{
	private DateOperations dateOperations = new DateOperations();
	private BooksInMemory books= new BooksInMemory();
	public void create(){
		String date;
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
		date = JOptionPane.showInputDialog("Digite a data de aquisição no formato dd/MM/yyyy:");
		if(date.isEmpty())
			date = "01/01/01";
		boolean dateT;
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
			book.setAuthorsList(books.addAuthor(books.qAuthors()));
			book.setCategory(books.addCategory());
			try {
				if(books.create(book))
					JOptionPane.showMessageDialog(null, "Livro cadastrado.");
			} catch (RecordException e) {
				JOptionPane.showMessageDialog(null, "Livro não cadastrado, os campos Titulo e Local devem ser preenchidos.");
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
		}

	}

}