package br.com.softexpert.library.operations.db.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Operations;

public class CategoryJPA implements Operations<Category>{

	private EntityManager manager; 

	public CategoryJPA(EntityManager manager) { 
		this.manager = manager; 
	} 

	@Override
	public boolean create(Category category) throws Exception {
		if (category.getDescription().isEmpty()) {
			return false;
		}
		try{
			manager.getTransaction().begin();
			manager.persist(category);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}

		return true;
	}

	@Override
	public boolean delete(int id) {
		Category c = null;
		try {
			c = search(id);
		} catch (Exception e1) {
			return false;
		}
		try{
			manager.getTransaction().begin();
			manager.remove(c);
			manager.getTransaction().commit();
		}finally{
			manager.close();
		}
		return true;
	}

	@Override
	public void update(Category category) throws Exception {
		if(category.getDescription().isEmpty())
			throw new RecordException("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		try{
			manager.getTransaction().begin();
			manager.merge(category);
			manager.getTransaction().commit();  

		}finally{
			manager.close();
		}
	}
	@Override
	public Category search(int id) throws Exception {

		String jpql = 
				"SELECT x FROM Category x where x.sequentialCode = :id"; 

		TypedQuery <Category> query = 
				this.manager.createQuery(jpql, Category.class);

		query.setParameter("id", id);
		List<Category> list = query.getResultList();
		if(list.get(0) == null){
			throw new RecordException("Não foi possível encontrar o autor.");
		}else{
		return list.get(0);
		}
	}
	public Category searchByDescription(String name) throws Exception {

		String jpql = 
				"SELECT x FROM Category x where x.description = :name"; 

		TypedQuery <Category> query = 
				this.manager.createQuery(jpql, Category.class);

		query.setParameter("name", name);
		List<Category> list = query.getResultList();
		return list.get(0);
	}
}
