package br.com.softexpert.library.operations.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.category.CreateCategory;

public class BookJPA implements Operations<Book>{

	private EntityManager manager;

	public BookJPA(EntityManager manager) { 
		this.manager = manager; 
	} 
	@Override
	public boolean create(Book book) throws Exception {
		try{
			manager.getTransaction().begin();
			manager.persist(book);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}   
		return true;
	}

	@Override
	public boolean delete(String book) {
		List<Book> list;
		try {
			list =searchByTitle(book);
		} catch (Exception e1) {
			return false;
		}
		try{
			manager.getTransaction().begin();
			manager.remove(list.get(0));
			manager.getTransaction().commit();
		}finally{
			manager.close();
		}
		return true;
	}

	@Override
	public void update(Book book) throws Exception {
		try{
			manager.getTransaction().begin();
			manager.merge(book);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}   
	}

	@Override
	public Book search(String name) throws Exception {
		return null;
	}

	public List<Book> searchByTitle(String title) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.title = :title"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("title", title);
		List<Book> list = query.getResultList();
		return list;
	}

	public Book searchByCode(int sequentialCode) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.sequentialCode = :sequentialCode"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("sequentialCode", sequentialCode);
		List<Book> b= query.getResultList();
		return b.get(0);
	}

	public List<Book> searchByCategory(String description) throws Exception {
		String jpql = 
				"SELECT x FROM Book x where x.category.description = :description"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("description", description);
		List<Book> list = query.getResultList();
		return list;
	}

	public List<Book> searchByAuthor(String name) throws Exception {
		String jpql = 
				"SELECT x FROM Book x inner join x.authorsList al where al.name = :name"; 

		TypedQuery <Book> query = 
				this.manager.createQuery(jpql, Book.class);

		query.setParameter("name", name);
		List<Book> list = query.getResultList();
		return list;
	}
	public String quantityOfAuthors() {
		CreateAuthor createAuthor = new CreateAuthor();
		String q = createAuthor.getQuantityOfAuthors();
		if(q.isEmpty()){
			do{
				q = createAuthor.getQuantityOfAuthors();
			}while(q.isEmpty());
		}
		return q;
	}


	public Category addCategory(EntityManager manager) {
		Category c = new Category();
		CreateCategory createCategory = new CreateCategory();
		CategoryJPA cdb = new CategoryJPA(manager);
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


	public List<Author> addAuthor(String qAuthors, EntityManager manager) {
		CreateAuthor createAuthor = new CreateAuthor();
		Author a = new Author();
		AuthorJPA adb = new AuthorJPA(manager);
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
}
