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
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.book.QuantityOfAuthors;
import br.com.softexpert.library.user.category.CreateCategory;

public class BookDao implements Books{
	static final String sqlCreate = "insert into tbl_books (title,summary,barcode,pages,bookLocation,acquisition,categoryBook) values (?,?,?,?,?,?,?)";
	static final String sqlSelectBooks = "select * from tbl_books";
	static final String sqlCreateAuthorBook = "insert into tbl_author_book (codeBook,codeAuthor) values (?,?)";
	static final String sqlDelete = "delete from tbl_books where title=?";
	static final String sqlUpdate = "update tbl_books set title=?,summary=?,barcode=?,pages=?,bookLocation=?,acquisition=?,categoryBook=? where sequentialCode=?";
	static final String sqlUpdateAuthor = "update tbl_author_book set codeAuthor=? where codeBook=?";
	static final String sqlSearchByCode = "select * from tbl_books where sequentialCode=?";
	static final String sqlSearchByTitle = "select * from tbl_books where title like ?";
	static final String sqlSearchByCategory = "select * from tbl_category where description= ?";
	static final String sqlSearchByAuthor = "select * from tbl_author where name=?";
	static final String sqlSelectCodeBooks = "select * from tbl_author_book where codeAuthor=?";
	static final String sqlSelectListBook = "select * from tbl_books where categoryBook=?";
	static final String sqlSelectCategoryBook = "select * from tbl_category where sequentialCode=?";
	static final String sqlSelectCodeAuthor = "select * from tbl_author_book where codeBook=?"; 
	
	public BookDao() {
	
	}

	@Override
	public boolean create(Book book) throws Exception {
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new RecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlCreate);){
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
				prepareStatement(sqlSelectBooks);){
			try(ResultSet rs = stmt.executeQuery();){
			Book b = new Book();
			while (rs.next()) {
				b.setTitle(rs.getString("title"));
				if(b.getTitle().equals(book.getTitle()))
					book.setSequencialCode(rs.getInt("sequentialCode"));
			}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void insertAuthor(Book book) {
		List<Author> authors = book.getAuthorsList();
		for (int i=0;i<authors.size();i++){
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlCreateAuthorBook);) {
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
	public boolean delete(String book) {
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection
				.prepareStatement(sqlDelete);){
			stmt.setString(1, book);
			stmt.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public void update(Book book) throws Exception {
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlUpdate);) {
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
				PreparedStatement stmt = connection.prepareStatement(sqlUpdateAuthor);) {
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
	public Book search(String name) throws Exception {
		return null;
	}

	@Override
	public Book searchByCode(int n) throws RecordException {
		boolean found = false;
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(sqlSearchByCode);) {
			stmt.setInt(1, n);
			List<Book> booksList = new ArrayList<Book>();
			try(ResultSet rs = stmt.executeQuery();){
			Book b = new Book();
			while (rs.next()) {
				b.setSequencialCode(rs.getInt("sequentialCode"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				Category c = new Category();
				c=getCategoryBook(rs.getInt("categoryBook"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("sequentialCode"))));
				booksList.add(b);
				found = true;
			}
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
				prepareStatement(sqlSearchByTitle);){
			stmt.setString(1, "%"+title+"%");
			List<Book> booksList = new ArrayList<Book>();
			try(ResultSet rs = stmt.executeQuery();){
			while (rs.next()) {
				Book b = new Book();
				b.setSequencialCode(rs.getInt("sequentialCode"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				Category c = new Category();
				c=getCategoryBook(rs.getInt("categoryBook"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("sequentialCode"))));
				booksList.add(b);
				found = true;
			}
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
				prepareStatement(sqlSearchByCategory);){
			stmt.setString(1, category);
			List<Book> booksList = new ArrayList<Book>();
			try(ResultSet rs = stmt.executeQuery();){
			Category c = new Category();
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
				int code = c.getSequentialCode();
				booksList=getListBook(code);
				found = true;
			}
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return booksList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Book> getListBook(int code){
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(sqlSelectListBook);){
			stmt.setInt(1, code);
			List<Book> booksList = new ArrayList<Book>();
			try(ResultSet rs = stmt.executeQuery();){
			while (rs.next()) {
				Book b = new Book();
				b.setSequencialCode(rs.getInt("sequentialCode"));
				b.setTitle(rs.getString("title"));
				b.setSummary(rs.getString("summary"));
				b.setPages(rs.getInt("pages"));
				b.setLocation(rs.getString("bookLocation"));
				b.setAcquisition(rs.getDate("acquisition"));
				Category c = new Category();
				c=getCategoryBook(rs.getInt("categoryBook"));
				b.setCategory(c);
				b.setAuthorsList(getAuthorsBook(getCodeAuthor(rs.getInt("sequentialCode"))));
				booksList.add(b);
			}
			}
			return booksList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Book> searchByAuthor(String n) throws Exception {
		boolean found = false;
		List<Integer> list = new ArrayList<Integer>();
		List<Book> listBook = new ArrayList<Book>();
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(sqlSearchByAuthor);){
			stmt.setString(1, n);
			try(ResultSet rs = stmt.executeQuery();){
			Author a = new Author();
			while (rs.next()) {
				a.setSequentialCode(rs.getInt("sequentialCode"));
				a.setName(rs.getString("name"));
				a.setBirthday(rs.getDate("birthday"));
				a.setNationality(rs.getString("nationality"));
				if(a.getName().equals(n)){
					int codeA= rs.getInt("sequentialCode");
					list=getCodeBooks(codeA);
					listBook = getBooks(list);
					found = true;
				}
				else{
					a.setName(null);
				}
			}
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return listBook;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Integer> getCodeBooks(int codeA){
		List<Integer> list = new ArrayList<Integer>();
		try(Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(sqlSelectCodeBooks);) {
			stmt.setInt(1, codeA);
			try(ResultSet rs = stmt.executeQuery();){
			while (rs.next()) {
				int codeAuthor=rs.getInt("codeAuthor");
				int codeBook=rs.getInt("codeBook");
				if(codeA==codeAuthor){
					list.add(codeBook);
				}
			}
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Book> getBooks(List<Integer> code){
		List<Book> listBook = new ArrayList<Book>();
		for (int i=0;i<code.size();i++){
			try {
				listBook.add(searchByCode(code.get(i)));
			} catch (RecordException e) {
				e.printStackTrace();
			}
		}
		return listBook;
	}
	@Override
	public String quantityOfAuthors() {
		QuantityOfAuthors qAuthors = new QuantityOfAuthors();
		String q = qAuthors.getQuantityOfAuthors();
		if(q.isEmpty()){
			do{
				q = qAuthors.getQuantityOfAuthors();
			}while(q.isEmpty());
		}
		return q;
	}

	@Override
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
	}
	private Category getCategoryBook(int code){
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(sqlSelectCategoryBook);){
			stmt.setInt(1, code);
			Category c = new Category();
			try(ResultSet rs = stmt.executeQuery();){
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
			}
			}
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Author> getAuthorsBook(List<Integer> code){
		List<Author> authorsList = new ArrayList<Author>();
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement("select * from tbl_author");){
			try(ResultSet rs = stmt.executeQuery();){
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
				prepareStatement(sqlSelectCodeAuthor);){
			stmt.setInt(1, code);
			try(ResultSet rs = stmt.executeQuery();){
			while (rs.next()) {
				int codeAuthor = rs.getInt("codeAuthor");
				codeAuthorsList.add(codeAuthor);
			}
			}
			return codeAuthorsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
