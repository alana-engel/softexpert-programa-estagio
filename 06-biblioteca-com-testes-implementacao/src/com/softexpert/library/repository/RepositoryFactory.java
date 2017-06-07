package com.softexpert.library.repository;

import java.util.HashMap;
import java.util.Map;

import com.softexpert.library.entity.BasicEntity;

/**
 * Classe responsável por instanciar e gerenciar as instâncias dos repositórios
 * @author erico.lutzer
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RepositoryFactory {

    private static Map<Class, Repository> repositories = new HashMap<>();
    private static final String REPOSITORY_TYPE = "InMemory";
    
    public static <E extends BasicEntity> Repository<E> getRepositoryFrom(Class<E> entityClass) {
        try {
            Class<E> repositoryClass = (Class<E>) Class.forName(getRepositoryClassName(entityClass));
            Repository<E> repo = repositories.get(repositoryClass);
            if (repo == null) {
                repo = (Repository<E>) repositoryClass.newInstance();
                repositories.put(repositoryClass, repo);
            }
            return repo;
        } catch (Exception e) {
            throw new CannotLocateRepositoryException("Não foi possível instanciar repositório para " + entityClass.getSimpleName(), e);
        }
    }
    
    private static String getRepositoryClassName(Class entityClass) {
        return getRepositoryPackage() + "." + entityClass.getSimpleName() + REPOSITORY_TYPE + "Repository";
    }
    
    private static String getRepositoryPackage() {
        return RepositoryFactory.class.getPackage().getName() + "." + REPOSITORY_TYPE.toLowerCase();
    }

}
