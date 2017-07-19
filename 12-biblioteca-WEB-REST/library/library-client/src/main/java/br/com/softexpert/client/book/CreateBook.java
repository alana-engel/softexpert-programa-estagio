package br.com.softexpert.client.book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.softexpert.client.author.CreateAuthor;
import br.com.softexpert.client.category.CreateCategory;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

public class CreateBook{
	private BookJPA books= new BookJPA(Manager.getEntityManager());
	private DateOperations dateOperations = new DateOperations();
	public void create() throws RecordException{
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
		String date = JOptionPane.showInputDialog("Digite a data de aquisição no formato dd/MM/yyyy:");
		if(date == null ||  date.isEmpty() ){
			book.setAcquisition(null);	
		}else
			if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
				book.setAcquisition(dateOperations.getConvertedDate(date));
			}else{
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
				create();
				return;
			}
		book.setAuthorsList(addAuthors());
		book.setCategory(addCategory());
		try {
			books.create(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<Author> addAuthors() {
		CreateAuthor createAuthor = new CreateAuthor();
		Author a = new Author();
		AuthorJPA adb = new AuthorJPA(Manager.getEntityManager());
		List<Author> authorsList = new ArrayList<Author>();
		int q=Integer.parseInt(createAuthor.quantityOfAuthors());
		for(int i=0;i<q;i++){
			String name = createAuthor.getName();
			try {
				a=adb.searchByName(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(a.getName() ==null || a.getName().isEmpty()){
				createAuthor.returnMessage();
				createAuthor.create();
				try {
					a=adb.searchByName(name);
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
	private Category addCategory() {
		CategoryJPA c= new CategoryJPA(Manager.getEntityManager());
		CreateCategory createCategory = new CreateCategory();
		Category category = new Category();
		String description=createCategory.getDescription();
		try {
			category=c.searchByDescription(description);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(category.getDescription() ==null || category.getDescription().isEmpty()){
			createCategory.returnMessage();
			createCategory.create();
			try {
				category=c.searchByDescription(description);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return category;
	}

}

