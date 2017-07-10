package br.com.softexpert.library.operations.db.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;

public class AuthorJPA implements Operations<Author>{

	private EntityManager manager;

	public AuthorJPA(EntityManager manager) { 
		this.manager = manager; 
	} 
	@Override
	public boolean create(Author Author) throws Exception {
		try{
			manager.getTransaction().begin();
			manager.persist(Author);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}   
		return true;
	}

	@Override
	public boolean delete(String name) {
		Author a = null;
		try {
			a = search(name);
		} catch (Exception e1) {
			return false;
		}
		try{
			manager.getTransaction().begin();
			manager.remove(a);
			manager.getTransaction().commit();
		}finally{
			manager.close();
		}
		return true;
	}

	@Override
	public void update(Author author) throws Exception {
		try{
			manager.getTransaction().begin();
			manager.merge(author);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}

	}

	@Override
	public Author search(String name) throws Exception {
		String jpql = 
				"SELECT x FROM Author x where x.name = :name"; 

		TypedQuery <Author> query = 
				this.manager.createQuery(jpql, Author.class);

		query.setParameter("name", name);
		List<Author> list = query.getResultList();
		return list.get(0);
	}


}
