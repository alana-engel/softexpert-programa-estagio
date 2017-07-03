package br.com.softexpert.library.user.book;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.BookDao;


public class UpdateBook{

	private Books books= new BookDao();
	private DateOperations dateOperations = new DateOperations();
	public void update() {
		Book book =new Book();
		Book nBook =new Book();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		try {
			book=books.searchByCode(cod);
		} catch (RecordException e1) {
			e1.printStackTrace();
		}
		nBook.setTitle(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		nBook.setSummary(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
		if(pag.isEmpty()){
			nBook.setPages(0);
		}else{
			nBook.setPages(Integer.parseInt(pag));
		}
		nBook.setLocation(JOptionPane.showInputDialog("Digite o Local:"));
		getAcquisition(nBook);
		nBook.setAuthorsList(books.addAuthor(books.qAuthors()));
		nBook.setCategory(books.addCategory());
		try {
			books.update(book, nBook);
			JOptionPane.showMessageDialog(null, "Livro alterado.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o Livro.");
			e.printStackTrace();
		}
	}

	private void getAcquisition(Book book) {
		String date;

		date = JOptionPane.showInputDialog("Digite a data de aquisição no formato dd/MM/yyyy:");
		if(date == null ||  date.isEmpty() ){
			date = null;
			book.setAcquisition(null);

		}else{
			if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
				book.setAcquisition(dateOperations.getConvertedDate(date));
			}else{
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
			}
		}
	}
}