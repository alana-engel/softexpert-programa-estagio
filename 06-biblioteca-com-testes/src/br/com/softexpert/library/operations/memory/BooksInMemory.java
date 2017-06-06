package br.com.softexpert.library.operations.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.repository.Repository;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.book.QAuthors;
import br.com.softexpert.library.user.category.CreateCategory;

public class BooksInMemory implements Books {
	
	private CreateAuthor createAuthor = new CreateAuthor();
	private boolean found = true;
	private QAuthors pages = new QAuthors();
	Repository repository = new Repository();
	
	@Override
	public boolean create(Book book) {
		SequentialCode cod = new SequentialCode();
		book.setCodLivro(cod.bookCode());
		book.setBarcode(cod.barcode());
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
				throw new CreateRecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		
		}else{
			repository.getBooks().add(book);
		}
		return true;
	}

	public String qPages() {
		String q = pages.getQuantityOfAuthors();
		if(q.isEmpty()){
			do{
				q = pages.getQuantityOfAuthors();
			}while(q.isEmpty());
		}
		return q;
	}

	public List<Author> addAuthor(String qnt){
		AuthorsInMemory authorOperations= new AuthorsInMemory();
		Author a = new Author();
		List<Author> authorsList = new ArrayList<>();
		int q=Integer.parseInt(qnt);
		for(int i=0;i<q;i++){
			String nome = createAuthor.getName();
			int p=authorOperations.checkIfAuthorExists(nome);
			if(p!=-1){
				a = repository.getAuthors().get(p);
			}
			else{
				a = createAuthor(a,p, nome);
			}
			authorsList.add(a);
		}
		return authorsList;
	}
	private Author createAuthor(Author a, int p, String name) {
		createAuthor.returnMessage();
		createAuthor.create();
		for (int j=0;j<repository.getAuthors().size();j++){
			if (repository.getAuthors().get(j).getName().equalsIgnoreCase(name)) {
				a = repository.getAuthors().get(j);
				found = true;
			}
		}
		return a;
	}

	public Category addCategory() {
		CategoriesInMemory categoryOperations= new CategoriesInMemory();
		Category c1 = new Category();
		CreateCategory createCategory = new CreateCategory();
		String description=createCategory.getDescription();
		if(description.isEmpty()){
			do{
				description=createCategory.getDescription();
			}while(description.isEmpty());
		}
		int p=categoryOperations.checkIfCategoryExists(description);
		if(p!=-1){
			c1 = repository.getCategories().get(p);
		}
		else{
			c1 = createCategory(p, description);
		}
		return c1;
	}
	private Category createCategory(int p, String description) {
		CreateCategory createCategory = new CreateCategory();
		Category c1 = new Category();
		createCategory.returnMessage();

		createCategory.create();
		for (int i=0;i<repository.getCategories().size();i++){
			if (repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				c1= repository.getCategories().get(i);
				found = true;
			}
		}
		return c1;
	}


	public boolean delete(String title){
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getTitle().equalsIgnoreCase(title)) {
				repository.getBooks().remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean update(Book book, int position){
		if(book.getTitle().isEmpty()){
			return false;
		}else{
			repository.getBooks().get(position).setTitle(book.getTitle());
			repository.getBooks().get(position).setSummary(book.getSummary());
			repository.getBooks().get(position).setPages(book.getPages());
			repository.getBooks().get(position).setLocation(book.getLocation());
			repository.getBooks().get(position).setAcquisition(book.getAcquisition());
			repository.getBooks().get(position).setAuthorsList(addAuthor(qPages()));
			repository.getBooks().get(position).setCategory(addCategory());
			return true;
		}
	}


	@Override
	public Book search(String title) {
		return null;
	}

	public Book searchByCode(int code){
		Book book = new Book();
		found=false;
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getSequentialCode()==code) {
				book = repository.getBooks().get(i);
				found = true;
			}
		}
		return book;
	}
	public List<Book> searchByTitle(String title){
		found=false;
		List<Book> listByTitle = new ArrayList<>();
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getTitle().contains(title)) {
				listByTitle.add(repository.getBooks().get(i));
				found = true;
			}
		}
		return listByTitle;
	} 
	public List<Book> searchByCategory(String category){
		found=false;
		List<Book> listByCategory = new ArrayList<>();
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getCategory().getDescription().equalsIgnoreCase(category)) {
				listByCategory.add(repository.getBooks().get(i));
				found = true;
			}
		}
		return listByCategory;
	}
	public List<Book> searchByAuthor(String name){
		List<Book> listByAuthor = new ArrayList<>();
		List<Author> list = new ArrayList<>();
		found = false;
		for (int i=0;i<repository.getBooks().size();i++){
			list=repository.getBooks().get(i).getAuthorsList();
			buscaNomeAutor(name, listByAuthor, list, i);
		}
		return listByAuthor;
	}
	private void buscaNomeAutor(String name, List<Book> listByAuthor, List<Author> list, int i) {
		Author a;
		for (int j=0;j<list.size();j++){
			a=list.get(j);
			if (a.getName().equalsIgnoreCase(name)) {
				listByAuthor.add(repository.getBooks().get(i));
				found = true;
			}
		}
	}
	public int checkIfBookExists(int n){
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getSequentialCode()==n) {
				repository.getBooks().get(i);
				return i;
			}
		}
		return -1;
	}

	public boolean found(){
		return found;
	}

}
