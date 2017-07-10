package br.com.softexpert.library.user.book;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.BookDao;

public class CreateBook{
	private DateOperations dateOperations = new DateOperations();
	private Books books= new BookDao();

	public void create() throws RecordException{
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
		if(date == null ||  date.isEmpty() ){
			date = null;
			book.setAcquisition(null);	
		}else
			if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
				book.setAcquisition(dateOperations.getConvertedDate(date));

			}else{
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
				create();
				return;
			}
		book.setAuthorsList(books.addAuthor(books.quantityOfAuthors()));
		book.setCategory(books.addCategory());
		try {
			if(books.create(book))
				JOptionPane.showMessageDialog(null, "Livro cadastrado.");
		} catch (HeadlessException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}
	}

}

