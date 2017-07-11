package br.com.softexpert.library.user.book;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.category.CreateCategory;


public class UpdateBook{
	private CreateCategory createCategory = new CreateCategory();
	CreateAuthor createAuthor = new CreateAuthor();
	Author a = new Author();
	AuthorJPA adb = new AuthorJPA(Manager.getEntityManager());
	private CategoryJPA c= new CategoryJPA(Manager.getEntityManager());
	private BookJPA books= new BookJPA(Manager.getEntityManager());
	private DateOperations dateOperations = new DateOperations();
	public void update() {
		Book book =new Book();
		Category category = new Category();
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
		category = addCategory(category);
		List<Author> authorsList = addAuthors();
		book.setAuthorsList(authorsList);
		book.setCategory(category);
		try {
			books.update(book);
			JOptionPane.showMessageDialog(null, "Livro alterado.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o Livro.");
			e.printStackTrace();
		}
	}

	private List<Author> addAuthors() {
		List<Author> authorsList = new ArrayList<Author>();
		int q=Integer.parseInt(books.quantityOfAuthors());
		for(int i=0;i<q;i++){
			String name = createAuthor.getName();
			try {
				a=adb.search(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(a.getName() ==null || a.getName().isEmpty()){
				createAuthor.returnMessage();
				createAuthor.create();
				try {
					a=adb.search(name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				authorsList.add(a);
			}else{
				authorsList.add(a);
			}
		}
		return authorsList;
	}

	private Category addCategory(Category category) {
		String description=createCategory.getDescription();
		try {
			category=c.search(description);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(category.getDescription() ==null || category.getDescription().isEmpty()){
			createCategory.returnMessage();
			createCategory.create();
			try {
				category=c.search(description);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return category;
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