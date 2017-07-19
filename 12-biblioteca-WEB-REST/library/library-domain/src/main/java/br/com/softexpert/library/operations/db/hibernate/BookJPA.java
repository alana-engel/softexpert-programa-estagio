package br.com.softexpert.library.operations.db.hibernate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.library.Barcode;


public class BookJPA implements Operations<Book>{

	private EntityManager manager;

	public BookJPA(EntityManager manager) { 
		this.manager = manager; 
	} 
	@Override
	public boolean create(Book book) throws Exception {
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new RecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}else{
		try{
			manager.getTransaction().begin();
			manager.persist(book);
			manager.getTransaction().commit();  
			getBarcode(book);
			return true;
		}finally{
			manager.close();
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
	public boolean delete(int id) {
		Book b;
		try {
			b =searchByCode(id);
		} catch (Exception e1) {
			return false;
		}
		try{
			manager.getTransaction().begin();
			manager.remove(b);
			manager.getTransaction().commit();
		}finally{
			manager.close();
		}
		return true;
	}

	@Override
	public void update(Book book) throws Exception {
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory()==null){
			throw new RecordException("Não foi possível alterar o Livro. Verifique os campos preenchidos.");
		}else{
		try{
			manager.getTransaction().begin();
			manager.merge(book);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}  
		}
	}

	@Override
	public Book search(int id) throws Exception {
		return null;
	}

	public List<Book> searchByTitle(String title) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.title = :title"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("title", title);
		List<Book> list = query.getResultList();
		if(list.get(0) == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return list;
		}
	}

	public Book searchByCode(int sequentialCode) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.sequentialCode = :sequentialCode"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("sequentialCode", sequentialCode);
		List<Book> b= query.getResultList();
		if(b == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return b.get(0);
		}
	}

	public List<Book> searchByCategory(String description) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.category.description = :description"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("description", description);
		List<Book> list = query.getResultList();
		if(list.get(0) == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return list;
		}
	}

	public List<Book> searchByAuthor(String name) throws Exception {
		String jpql = 
				"SELECT x FROM Book x inner join x.authorsList al where al.name = :name"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("name", name);
		List<Book> list = query.getResultList();
		if(list.get(0) == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return list;
		}
	}

}
