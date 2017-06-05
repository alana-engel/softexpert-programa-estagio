package br.com.softexpert.library.operations.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.book.Pages;
import br.com.softexpert.library.user.category.CreateCategory;

public class BooksInMemory implements Books {
	private static List<Book> books = new ArrayList<Book>();
	private CreateAuthor createAuthor = new CreateAuthor();
	private boolean found = true;
	private Pages pages = new Pages();

	@Override
	public boolean create(Book book) {
		SequentialCode cod = new SequentialCode();
		book.setCodLivro(cod.bookCode());
		book.setBarcode(cod.barcode());
		book.setAuthorsList(addAuthor(qPages()));
		book.setCategory(addCategory());
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null){
			try {
				throw new CreateRecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
			} catch (CreateRecordException e) {
				e.printStackTrace();
			}
			return false;
		}else{
			books.add(book);
		}
		return true;
	}

	private String qPages() {
		String q = pages.getQuantityOfAuthors();
		if(q.isEmpty()){
			do{
				q = pages.getQuantityOfAuthors();
			}while(q.isEmpty());
		}
		return q;
	}

	private List<Author> addAuthor(String qnt){
		AuthorsInMemory authorOperations= new AuthorsInMemory();
		Author a = new Author();
		List<Author> authorsList = new ArrayList<>();
		int q=Integer.parseInt(qnt);
		for(int i=0;i<q;i++){
			String nome = createAuthor.getName();
			int p=authorOperations.checkIfAuthorExists(nome);
			if(p!=-1){
				a = authorOperations.getAuthors().get(p);
			}
			else{
				a = createAuthor(a,p, nome);
			}
			authorsList.add(a);
		}
		return authorsList;
	}
	private Author createAuthor(Author a, int p, String name) {
		AuthorsInMemory authorOperations= new AuthorsInMemory();
		createAuthor.returnMessage();
		createAuthor.create();
		for (int j=0;j<authorOperations.getAuthors().size();j++){
			if (authorOperations.getAuthors().get(j).getName().equalsIgnoreCase(name)) {
				a = authorOperations.getAuthors().get(j);
				found = true;
			}
		}
		return a;
	}

	private Category addCategory() {
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
			c1 = categoryOperations.getCategories().get(p);
		}
		else{
			c1 = createCategory(p, description);
		}
		return c1;
	}
	private Category createCategory(int p, String description) {
		CreateCategory createCategory = new CreateCategory();
		CategoriesInMemory categoryOperations= new CategoriesInMemory();
		Category c1 = new Category();
		createCategory.returnMessage();

		createCategory.create();
		for (int i=0;i<categoryOperations.getCategories().size();i++){
			if (categoryOperations.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {

				c1= categoryOperations.getCategories().get(i);
				found = true;
			}
		}
		return c1;
	}


	public boolean delete(String title){
		for (int i=0;i<getBooks().size();i++){
			if (getBooks().get(i).getTitle().equalsIgnoreCase(title)) {
				getBooks().remove(i);
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean update(Book book, int position) {
		if(book.getTitle().isEmpty()){
			return false;
		}else{
			books.get(position).setTitulo(book.getTitle());
			books.get(position).setResumo(book.getSummary());
			books.get(position).setPages(book.getPages());
			books.get(position).setLocation(book.getLocation());
			books.get(position).setAcquisition(book.getAcquisition());
			books.get(position).setAuthorsList(addAuthor(qPages()));
			books.get(position).setCategory(addCategory());
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
		for (int i=0;i<books.size();i++){
			if (books.get(i).getSequentialCode()==code) {
				book = books.get(i);
				found = true;
			}
		}
		return book;
	}
	public List<Book> searchByTitle(String title){
		found=false;
		List<Book> listByTitle = new ArrayList<>();
		for (int i=0;i<books.size();i++){
			if (books.get(i).getTitle().contains(title)) {
				listByTitle.add(books.get(i));
				found = true;
			}
		}
		return listByTitle;
	} 
	public List<Book> searchByCategory(String category){
		found=false;
		List<Book> listByCategory = new ArrayList<>();
		for (int i=0;i<books.size();i++){
			if (books.get(i).getCategory().getDescription().equalsIgnoreCase(category)) {
				listByCategory.add(books.get(i));
				found = true;
			}
		}
		return listByCategory;
	}
	public List<Book> searchByAuthor(String name){
		List<Book> listByAuthor = new ArrayList<>();
		List<Author> list = new ArrayList<>();
		found = false;
		for (int i=0;i<books.size();i++){
			list=books.get(i).getAuthorsList();
			buscaNomeAutor(name, listByAuthor, list, i);
		}
		return listByAuthor;
	}
	private void buscaNomeAutor(String name, List<Book> listByAuthor, List<Author> list, int i) {
		Author a;
		for (int j=0;j<list.size();j++){
			a=list.get(j);
			if (a.getName().equalsIgnoreCase(name)) {
				listByAuthor.add(books.get(i));
				found = true;
			}
		}
	}
	public int checkIfBookExists(int n){
		for (int i=0;i<books.size();i++){
			if (books.get(i).getSequentialCode()==n) {
				books.get(i);
				return i;
			}
		}
		return -1;
	}

	public boolean found(){
		return found;
	}


	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		BooksInMemory.books = books;
	}	
}
