package br.com.softexpert.library.user.book;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.category.CreateCategory;

public class CreateBook{
	private CreateAuthor createAuthor = new CreateAuthor();
	private Author a = new Author();
	private AuthorJPA adb = new AuthorJPA(Manager.getEntityManager());
	private BookJPA books= new BookJPA(Manager.getEntityManager());
	private CategoryJPA c= new CategoryJPA(Manager.getEntityManager());
	private DateOperations dateOperations = new DateOperations();
	private CreateCategory createCategory = new CreateCategory();
	public void create() throws RecordException{
		String date;
		Book book = new Book();
		Category category = new Category();
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
		category = addCategory(category);
		List<Author> authorsList = addAuthors();
		book.setAuthorsList(authorsList);
		book.setCategory(category);
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

}

