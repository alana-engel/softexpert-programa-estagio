package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class UpdateBook{

	private Books books= new BooksInMemory();
	private DateOperations dateOperations = new DateOperations();

	public void update() {
		Book book =new Book();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		int exist = books.checkIfBookExists(cod);
		if (exist==-1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o Livro.");
		}
		else{
			book.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
			book.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
			String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
			if(pag.isEmpty()){
				book.setPages(0);
			}else{
				book.setPages(Integer.parseInt(pag));
			}
			book.setLocation(JOptionPane.showInputDialog("Digite o Local:"));
			getAcquisition(book);
			if(books.update(book, exist)==true){
				JOptionPane.showMessageDialog(null, "Livro alterado.");
			}else{
				JOptionPane.showMessageDialog(null, "Livro não alterado, os campos Titulo e Local devem ser preenchidos.");
			}
		}
	}

	private void getAcquisition(Book livro) {
		String data;
		boolean dataT;
		do{
			data = JOptionPane.showInputDialog("Digite a data de aquisição no formato dd/MM/yyyy:");
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
			JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
		}
	}
}
