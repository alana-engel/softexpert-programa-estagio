package br.com.softexpert.library.user.book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;


public class UpdateBook{

	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private BookJPA books= new BookJPA(manager);
	private DateOperations dateOperations = new DateOperations();
	public void update() {
		Book book =new Book();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		try {
			book=books.searchByCode(cod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		book.setTitle(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		book.setSummary(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
		if(pag.isEmpty()){
			book.setPages(0);
		}else{
			book.setPages(Integer.parseInt(pag));
		}
		book.setLocation(JOptionPane.showInputDialog("Digite o Local:"));
		getAcquisition(book);
		book.setAuthorsList(books.addAuthor(books.quantityOfAuthors(), manager));
		book.setCategory(books.addCategory(manager));
		try {
			books.update(book);
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