package br.com.softexpert.library.operations.db.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Operations;

public class AuthorJPA implements Operations<Author>{

	private EntityManager manager;

	public AuthorJPA(EntityManager manager) { 
		this.manager = manager; 
	} 
	@Override
	public boolean create(Author author) throws Exception {
		if (author.getName().isEmpty()) {
			throw new RecordException("Não foi possível cadastrar o autor. Preencha o campo Nome.");
		} 
		try{
			manager.getTransaction().begin();
			manager.persist(author);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}   
		return true;
	}

	@Override
	public boolean delete(int id) {
		Author a = null;
		try {
			a = search(id);
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
		if(author.getName().isEmpty()){
			throw new RecordException("Não foi possível alterar o autor. Preencha o campo Nome.");
		}else{
		try{
			manager.getTransaction().begin();
			manager.merge(author);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}
		}
	}

	@Override
	public Author search(int id) throws Exception {
		String jpql = 
				"SELECT x FROM Author x where x.sequentialCode = :id"; 
		TypedQuery <Author> query = 
				this.manager.createQuery(jpql, Author.class);

		query.setParameter("id", id);
		List<Author> list = query.getResultList();
		if(list.get(0) == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return list.get(0);
		}
	}
	
	public Author searchByName(String name) throws Exception {
		String jpql = 
				"SELECT x FROM Author x where x.name = :name"; 

		TypedQuery <Author> query = 
				this.manager.createQuery(jpql, Author.class);

		query.setParameter("name", name);
		List<Author> list = query.getResultList();
		return list.get(0);
	}


}
