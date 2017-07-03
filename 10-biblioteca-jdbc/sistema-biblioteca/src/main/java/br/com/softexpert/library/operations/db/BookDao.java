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
import br.com.softexpert.library.user.book.QAuthors;
import br.com.softexpert.library.user.category.CreateCategory;

public class BookDao implements Books{
	private Connection connection;

	public BookDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public boolean create(Book book) throws Exception {
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new RecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}
		String sql = "insert into tbl_books " +
				"(title,summary,barcode,pages,bookLocation,acquisition,categoryBook)" +
				" values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
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
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		getCode(book);
		insertAuthor(book);
		getBarcode(book);
		return true;
	}

	private void getCode(Book book) {
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_books");
			ResultSet rs = stmt.executeQuery();
			Book b = new Book();
			while (rs.next()) {
				b.setTitle(rs.getString("title"));
				if(b.getTitle().equals(book.getTitle()))
					book.setSequencialCode(rs.getInt("sequentialCode"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void insertAuthor(Book book) {
		List<Author> authors = book.getAuthorsList();
		for (int i=0;i<authors.size();i++){
		String sql2 = "insert into tbl_author_book " +
				"(codeBook,codeAuthor)" +
				" values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql2);
				Author a= authors.get(i);
				stmt.setInt(1,book.getSequentialCode());
				stmt.setInt(2,a.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		}
	}

	private void getBarcode(Book book) throws Exception {
		Barcode bcode=new Barcode();
		Book nbook = new Book();
		nbook.setTitle(book.getTitle());
		nbook.setSummary(book.getSummary());
		nbook.setPages(book.getPages());
		nbook.setLocation(book.getLocation());
		nbook.setAcquisition(book.getAcquisition());
		nbook.setAuthorsList(book.getAuthorsList());
		nbook.setCategory(book.getCategory());
		nbook.setBarcode(bcode.getBarcode(book.getSequentialCode()));
		update(book, nbook);
	}

	@Override
	public boolean delete(String book) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("delete from tbl_books where title=?");
			stmt.setString(1, book);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public void update(Book book, Book nBook) throws Exception {
		String sql = "update tbl_books " +
				"set title=?,summary=?,barcode=?,pages=?,bookLocation=?,acquisition=?,categoryBook=?" +
				"where sequentialCode=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,nBook.getTitle());
			stmt.setString(2,nBook.getSummary());
			stmt.setString(3,nBook.getBarcode());
			stmt.setInt(4,nBook.getPages());
			stmt.setString(5,nBook.getLocation());
			if(nBook.getAcquisition() == null){
				stmt.setDate(6,null);	
			}else{
				stmt.setDate(6,new Date(nBook.getAcquisition().getTime()));
			}
			stmt.setInt(7,nBook.getCategory().getSequentialCode());
			stmt.setInt(8,book.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		getCode(nBook);
		updateAuthor(nBook);
	}

	private void updateAuthor(Book nBook) {
		String sql2 = "update tbl_author_book " +
				"set codeAuthor=?" +
				" where codeBook=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql2);
			List<Author> authors = nBook.getAuthorsList();
			for (int i=0;i<authors.size();i++){
				Author a= authors.get(i);
				stmt.setInt(1,nBook.getSequentialCode());
				stmt.setInt(2,a.getSequentialCode());
			}
			stmt.execute();
			stmt.close();
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
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_books where sequentialCode=?");
			stmt.setInt(1, n);
			ResultSet rs = stmt.executeQuery();
			Book b = new Book();
			List<Book> booksList = new ArrayList<Book>();
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
			rs.close();
			stmt.close();
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
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_books where title like ?");
			stmt.setString(1, "%"+title+"%");
			ResultSet rs = stmt.executeQuery();
			List<Book> booksList = new ArrayList<Book>();
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
			rs.close();
			stmt.close();
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
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_category where description= ?");
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();
			Category c = new Category();
			List<Book> booksList = new ArrayList<Book>();
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
				int code = c.getSequentialCode();
				booksList=getListBook(code);
				found = true;
			}
			rs.close();
			stmt.close();
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return booksList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Book> getListBook(int code){
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_books where categoryBook=?");
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();
			List<Book> booksList = new ArrayList<Book>();
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
			rs.close();
			stmt.close();
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
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_author where name=?");
			stmt.setString(1, n);
			ResultSet rs = stmt.executeQuery();
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
			rs.close();
			stmt.close();
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o livro.");
			return listBook;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Integer> getCodeBooks(int codeA){
		List<Integer> list = new ArrayList<Integer>();
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_author_book where codeAuthor=?");
			stmt.setInt(1, codeA);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int codeAuthor=rs.getInt("codeAuthor");
				int codeBook=rs.getInt("codeBook");
				if(codeA==codeAuthor){
					list.add(codeBook);
				}
			}
			rs.close();
			stmt.close();
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
	public String qAuthors() {
		QAuthors qAuthors = new QAuthors();
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
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_category where sequentialCode=?");
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();
			Category c = new Category();
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
			}
			rs.close();
			stmt.close();
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Author> getAuthorsBook(List<Integer> code){
		List<Author> authorsList = new ArrayList<Author>();
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_author");
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
			rs.close();
			stmt.close();
			return authorsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private List<Integer> getCodeAuthor(int code){
		List<Integer> codeAuthorsList = new ArrayList<Integer>();
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_author_book where codeBook=?");
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int codeAuthor = rs.getInt("codeAuthor");
				codeAuthorsList.add(codeAuthor);
			}
			rs.close();
			stmt.close();
			return codeAuthorsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
