package br.com.softexpert.library.user.book;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class UpdateBook{

	private BooksInMemory books= new BooksInMemory();
	private DateOperations dateOperations = new DateOperations();

	public void update() {
		Book book =new Book();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do livro: "));
		int exist = books.checkIfBookExists(cod);
		if (exist==-1){
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o Livro.");
		}
		else{
			book.setTitle(JOptionPane.showInputDialog("Digite o titulo do livro: "));
			book.setSummary(JOptionPane.showInputDialog("Digite o resumo do livro: "));
			String pag=JOptionPane.showInputDialog("Digite a quantidade de p�ginas: ");
			if(pag.isEmpty()){
				book.setPages(0);
			}else{
				book.setPages(Integer.parseInt(pag));
			}
			book.setLocation(JOptionPane.showInputDialog("Digite o Local:"));
			getAcquisition(book);
			book.setAuthorsList(books.addAuthor(books.qAuthors()));
			book.setCategory(books.addCategory());
			try {
				books.update(book, cod);
				JOptionPane.showMessageDialog(null, "Livro alterado.");

			} catch (RecordException e) {
				JOptionPane.showMessageDialog(null, "Livro n�o alterado, os campos Titulo e Local devem ser preenchidos.");
				e.printStackTrace();
			}
		}
	}

	private void getAcquisition(Book livro) {
		String data;
		boolean dataT;
		do{
			data = JOptionPane.showInputDialog("Digite a data de aquisi��o no formato dd/MM/yyyy:");
			if(data.isEmpty()){
				data = "01/01/01";
				dataT=true;
			}else{
				dataT=dateOperations.dateConverter(data);
			}
		}while(dataT==false);
		if(dateOperations.CompareDate(dateOperations.getConvertedDate(data))){
			livro.setAcquisition(dateOperations.getConvertedDate(data));
		}else{
			JOptionPane.showMessageDialog(null,"A data de aquisi��o n�o pode ser superior a data atual.");
		}
	}
}