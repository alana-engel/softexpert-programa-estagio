package br.com.softexpert.library.operations.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.Barcode;


public class BookDao implements Books{
	static final String SQL_CREATE = "insert into tbl_books (title,summary,barcode,pages,bookLocation,acquisition,categoryBook) values (?,?,?,?,?,?,?)";
	static final String SQL_SELECT_BOOKS = "select * from tbl_books";
	static final String SQL_CREATE_AUTHOR_BOOK = "insert into tbl_author_book (codeBook,codeAuthor) values (?,?)";
	static final String SQL_DELETE = "delete from tbl_books where sequentialCode=?";
	static final String SQL_UPDATE = "update tbl_books set title=?,summary=?,barcode=?,pages=?,bookLocation=?,acquisition=?,categoryBook=? where sequentialCode=?";
	static final String SQL_UPDATE_AUTHOR = "update tbl_author_book set codeAuthor=? where codeBook=?";
	static final String SQL_SEARCH_BY_CODE= "SELECT * FROM tbl_category INNER JOIN (tbl_books INNER JOIN (tbl_author_book INNER JOIN tbl_author ON tbl_author_book.codeAuthor = tbl_author.sequentialCode)"
			+ "  ON tbl_books.sequentialCode = tbl_author_book.codeBook) on tbl_category.sequentialCode = tbl_books.categoryBook where codeBook=?;";
	static final String SQL_SEARCH_BY_TITLE = "SELECT * FROM tbl_books INNER JOIN tbl_category ON tbl_books.categoryBook = tbl_category.sequentialCode where title like ?";
	static final String SQL_SEARCH_BY_CATEGORY = "SELECT * FROM tbl_books INNER JOIN tbl_category ON tbl_books.categoryBook = tbl_category.sequentialCode Where description =?";
	static final String SQL_SEARCH_BY_AUTHOR = "SELECT * FROM tbl_category INNER JOIN (tbl_books INNER JOIN (tbl_author_book INNER JOIN tbl_author ON tbl_author_book.codeAuthor = tbl_author.sequentialCode)"
			+ "  ON tbl_books.sequentialCode = tbl_author_book.codeBook) on tbl_category.sequentialCode = tbl_books.categoryBook where name = ?";
	static final String SQL_SELECT_CODE_AUTHOR = "select * from tbl_author_book where codeBook=?"; 
	
	public BookDao() {
	
	}

	@Override
	public boolean create(Book book) throws Exception {
		
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new RecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_CREATE);){
			stmt.setString(1,book.getTitle());
			stmt.setString(2,book.getSummary());
			stmt.setString(3,book.getBarcode());
			stmt.setInt(4,book.getPages());
			stmt.setString(5,book.getLocation());
			if(book.getAcquisition() == null){
				stmt.setDate(6,null);	
			}else{
				stmt.setDate(6,new Date(book.getAcquisition().getTime()));
			}
			stmt.setInt(7,book.getCategory().getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		getCode(book);
		insertAuthor(book);
		getBarcode(book);
		return true;
	}

	private void getCode(Book book) {
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SELECT_BOOKS);){
			ResultSet rs = stmt.executeQuery();
			Book b = new Book();
			while (rs.next()) {
				b.setTitle(rs.getString("title"));
				if(b.getTitle().equals(book.getTitle()))
					book.setSequentialCode(rs.getInt("sequentialCode"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void insertAuthor(Book book) {
		List<Author> authors = book.getAuthorsList();
		for (int i=0;i<authors.size();i++){
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_AUTHOR_BOOK);) {
				Author a= authors.get(i);
				stmt.setInt(1,book.getSequentialCode());
				stmt.setInt(2,a.getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		}
	}

	private void getBarcode(Book book) throws Exception {
		Barcode bcode=new Barcode();
		book.setTitle(book.getTitle());
		book.setSummary(book.getSummary());
		book.setPages(book.getPages());
		book.setLocation(book.getLocation());
		book.setAcquisition(book.getAcquisition());
		book.setAuthorsList(book.getAuthorsList());
		book.setCategory(book.getCategory());
		book.setBarcode(bcode.getBarcode(book.getSequentialCode()));
		update(book);
	}

	@Override
	public boolean delete(int book) {
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection
				.prepareStatement(SQL_DELETE);){
			stmt.setInt(1, book);
			stmt.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public void update(Book book) throws Exception {
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE);) {
			stmt.setString(1,book.getTitle());
			stmt.setString(2,book.getSummary());
			stmt.setString(3,book.getBarcode());
			stmt.setInt(4,book.getPages());
			stmt.setString(5,book.getLocation());
			if(book.getAcquisition() == null){
				stmt.setDate(6,null);	
			}else{
				stmt.setDate(6,new Date(book.getAcquisition().getTime()));
			}
			stmt.setInt(7,book.getCategory().getSequentialCode());
			stmt.setInt(8,book.getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		getCode(book);
		updateAuthor(book);
	}

	private void updateAuthor(Book nBook) {
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_AUTHOR);) {
			List<Author> authors = nBook.getAuthorsList();
			for (int i=0;i<authors.size();i++){
				Author a= authors.get(i);
				stmt.setInt(1,nBook.getSequentialCode());
				stmt.setInt(2,a.getSequentialCode());
			}
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book search(int id) throws Exception {
		return null;
	}

	@Override
	public Book searchByCode(int n) throws RecordException {
		boolean found = false;
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH_BY_CODE);) {
			stmt.setInt(1, n);
			List<Book> booksList = new ArrayList<Book>();
			ResultSet rs = stmt.executeQuery();
			Book b = new Book();
			while (rs.next()) {
				b.setSequentialCode(rs.getInt("codeBook"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				b.setBarcode(rs.getString("barcode"));
				Category c = new Category();
				c.setSequentialCode(rs.getInt("categoryBook"));
				c.setDescription(rs.getString("description"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("codeBook"))));
				booksList.add(b);
				found = true;
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return booksList.get(0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> searchByTitle(String title) throws Exception {
		boolean found = false;
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH_BY_TITLE);){
			stmt.setString(1, "%"+title+"%");
			List<Book> booksList = new ArrayList<Book>();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setSequentialCode(rs.getInt("sequentialCode"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				b.setBarcode(rs.getString("barcode"));
				Category c = new Category();
				c.setSequentialCode(rs.getInt("categoryBook"));
				c.setDescription(rs.getString("description"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("sequentialCode"))));
				booksList.add(b);
				found = true;
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return booksList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Book> searchByCategory(String category) throws Exception {
		boolean found = false;
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH_BY_CATEGORY);){
			stmt.setString(1, category);
			List<Book> booksList = new ArrayList<Book>();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setSequentialCode(rs.getInt("sequentialCode"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				b.setBarcode(rs.getString("barcode"));
				Category c = new Category();
				c.setSequentialCode(rs.getInt("categoryBook"));
				c.setDescription(rs.getString("description"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("sequentialCode"))));
				booksList.add(b);
				found = true;
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return booksList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Book> searchByAuthor(String n) throws Exception {
		boolean found = false;
		List<Book> listBook = new ArrayList<Book>();
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH_BY_AUTHOR);){
			stmt.setString(1, n);
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				Author a = new Author();
				Book b = new Book();
				a.setSequentialCode(rs.getInt("codeAuthor"));
				a.setName(rs.getString("name"));
				a.setBirthday(rs.getDate("birthday"));
				a.setNationality(rs.getString("nationality"));
				if(a.getName().equals(n)){
					b.setSequentialCode(rs.getInt("codeBook"));
					b.setTitle(rs.getString("title"));
					b.setSummary(rs.getString("summary"));
					b.setPages(rs.getInt("pages"));
					b.setLocation(rs.getString("bookLocation"));
					b.setAcquisition(rs.getDate("acquisition"));
					b.setBarcode(rs.getString("barcode"));
					Category c = new Category();
					c.setSequentialCode(rs.getInt("categoryBook"));
					c.setDescription(rs.getString("description"));
					b.setCategory(c);
					b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("codeBook"))));
					listBook.add(b);
					found=true;
				}
				else{
					a.setName(null);
				}
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return listBook;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	/*@Override
	public Category addCategory() {
		Category c = new Category();
		CreateCategory createCategory = new CreateCategory();
		CategoryDao cdb = new CategoryDao();
		String description=createCategory.getDescription();
		if(description.isEmpty()){
			do{
				description=createCategory.getDescription();
			}while(description.isEmpty());
		}
		try {
			c=cdb.search(description);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(c.getDescription() ==null || c.getDescription().isEmpty()){
			createCategory.returnMessage();
			createCategory.create();
			try {
				c=cdb.search(description);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public List<Author> addAuthor(String qAuthors) {
		CreateAuthor createAuthor = new CreateAuthor();
		Author a = new Author();
		AuthorDao adb = new AuthorDao();
		List<Author> authorsList = new ArrayList<Author>();
		int q=Integer.parseInt(qAuthors);
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
	}*/

	private List<Author> getAuthorsBook(List<Integer> code){
		List<Author> authorsList = new ArrayList<Author>();
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement("select * from tbl_author");){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				for (int i=0;i<code.size();i++){
					int codeA=code.get(i);
					Author a = new Author();
					a.setSequentialCode(rs.getInt("sequentialCode"));
					a.setName(rs.getString("name"));
					a.setBirthday(rs.getDate("birthday"));
					a.setNationality(rs.getString("nationality"));
					if(codeA==rs.getInt("sequentialCode"))
						authorsList.add(a);
				}
			}
			return authorsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Integer> getCodeAuthor(int code){
		List<Integer> codeAuthorsList = new ArrayList<Integer>();
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SELECT_CODE_AUTHOR);){
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int codeAuthor = rs.getInt("codeAuthor");
				codeAuthorsList.add(codeAuthor);
			}
			return codeAuthorsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
