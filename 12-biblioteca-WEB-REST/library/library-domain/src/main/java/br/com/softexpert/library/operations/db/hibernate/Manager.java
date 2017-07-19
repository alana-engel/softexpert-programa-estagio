package br.com.softexpert.library.operations.db.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Manager {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");;
	
	public static EntityManager getEntityManager() { 
		return Manager.factory.createEntityManager(); 
	} 
	
	
}
