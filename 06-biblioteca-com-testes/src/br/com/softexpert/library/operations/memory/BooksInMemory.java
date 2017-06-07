package br.com.softexpert.library.operations.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.Barcode;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.repository.Repository;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.book.QAuthors;
import br.com.softexpert.library.user.category.CreateCategory;

public class BooksInMemory implements Books {
	
	private CreateAuthor createAuthor = new CreateAuthor();
	private QAuthors qAuthors = new QAuthors();
	Repository repository = new Repository();
	Barcode bcode=new Barcode();
	
	@Override
	public boolean create(Book book) {
		SequentialCode cod = new SequentialCode();
		book.setSequencialCode(cod.bookCode());
		int code =book.getSequentialCode();
		barcode(book, code);
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
				throw new CreateRecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}else{
			repository.getBooks().add(book);
		}
		return true;
	}
	private void barcode(Book book, int code){
		book.setBarcode(bcode.getBarcode(code));
	}
	public String qAuthors() {
		String q = qAuthors.getQuantityOfAuthors();
		if(q.isEmpty()){
			do{
				q = qAuthors.getQuantityOfAuthors();
			}while(q.isEmpty());
		}
		return q;
	}

	public List<Author> addAuthor(String qnt){
	
		Author a = new Author();
		List<Author> authorsList = new ArrayList<>();
		int q=Integer.parseInt(qnt);
		for(int i=0;i<q;i++){
			a = checkAuthors(a);
			authorsList.add(a);
		}
		return authorsList;
	}

	private Author checkAuthors( Author a) {
		AuthorsInMemory authorOperations= new AuthorsInMemory();
		String nome = createAuthor.getName();
		int p=authorOperations.checkIfAuthorExists(nome);
		if(p!=-1){
			a = repository.getAuthors().get(p);
		}
		else{
			a = createAuthor(a,p, nome);
		}
		return a;
	}
	private Author createAuthor(Author a, int p, String name) {
		createAuthor.returnMessage();
		createAuthor.create();
		for (int j=0;j<repository.getAuthors().size();j++){
			if (repository.getAuthors().get(j).getName().equalsIgnoreCase(name)) {
				a = repository.getAuthors().get(j);
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
	public boolean update(Book book, int cod){
		int exist = checkIfBookExists(cod);
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new CreateRecordException("Não foi possível auterar o Livro. Verifique os campos preenchidos.");
		}else{
			repository.getBooks().get(exist).setTitle(book.getTitle());
			repository.getBooks().get(exist).setSummary(book.getSummary());
			repository.getBooks().get(exist).setPages(book.getPages());
			repository.getBooks().get(exist).setLocation(book.getLocation());
			repository.getBooks().get(exist).setAcquisition(book.getAcquisition());
			repository.getBooks().get(exist).setAuthorsList(book.getAuthorsList());
			repository.getBooks().get(exist).setCategory(book.getCategory());
			return true;
		}
	}

	@Override
	public Book search(String title) {
		return null;
	}

	public Book searchByCode(int code){
		boolean found = false;
		Book book = new Book();
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getSequentialCode()==code) {
				book = repository.getBooks().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar o livro.");
		return book;
	}
	public List<Book> searchByTitle(String title){
		boolean found = false;
		List<Book> listByTitle = new ArrayList<>();
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getTitle().contains(title)) {
				listByTitle.add(repository.getBooks().get(i));
				found = true;
			}
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar o livro.");
		return listByTitle;
	} 
	public List<Book> searchByCategory(String category){
		boolean found = false;
		List<Book> listByCategory = new ArrayList<>();
		for (int i=0;i<repository.getBooks().size();i++){
			if (repository.getBooks().get(i).getCategory().getDescription().equalsIgnoreCase(category)) {
				listByCategory.add(repository.getBooks().get(i));
				found = true;
			}
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar o livro.");
		return listByCategory;
	}
	public List<Book> searchByAuthor(String name){
		boolean found = false;
		List<Book> listByAuthor = new ArrayList<>();
		List<Author> list = new ArrayList<>();
		for (int i=0;i<repository.getBooks().size();i++){
			list=repository.getBooks().get(i).getAuthorsList();
			Author a;
			for (int j=0;j<list.size();j++){
				a=list.get(j);
				if (a.getName().equalsIgnoreCase(name)) {
					listByAuthor.add(repository.getBooks().get(i));
					found = true;
				}
			}
			
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar o livro.");
		return listByAuthor;
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
}
