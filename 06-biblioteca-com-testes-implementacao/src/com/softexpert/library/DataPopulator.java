package com.softexpert.library;

import java.util.HashSet;

import com.softexpert.library.entity.Author;
import com.softexpert.library.entity.Book;
import com.softexpert.library.entity.Category;
import com.softexpert.library.repository.AuthorRepository;
import com.softexpert.library.repository.BookRepository;
import com.softexpert.library.repository.Repository;
import com.softexpert.library.repository.RepositoryFactory;
import com.softexpert.library.utils.DateConverter;

public class DataPopulator {

    private BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFrom(Book.class);
    private AuthorRepository authorRepository = (AuthorRepository) RepositoryFactory.getRepositoryFrom(Author.class);
    private Repository<Category> categoryRepository = RepositoryFactory.getRepositoryFrom(Category.class);
    
    public void initializeData() {
        try {
            createCategories();
            createAuthors();
            createBooks();
        }catch (Exception e) {
            throw new RuntimeException("Não foi possível inicializar os dados!", e);
        }
    }
    
    private void createCategories() {
        Category ficcao = new Category();
        ficcao.setDescription("Ficção");
        
        Category romance = new Category();
        romance.setDescription("Romance");
        
        Category terror = new Category();
        terror.setDescription("Terror");
        
        categoryRepository.save(ficcao);
        categoryRepository.save(romance);
        categoryRepository.save(terror);
    }
    
    private void createAuthors() throws Exception {
        Author autor1 = new Author();
        autor1.setBirthday(DateConverter.asDate("11/02/1917"));
        autor1.setName("Sidney Sheldon");
        autor1.setNationality("Americano");
        
        Author autor2 = new Author();
        autor2.setBirthday(DateConverter.asDate("10/09/1959"));
        autor2.setName("Paulo Coelho");
        autor2.setNationality("Brasileiro");
        
        Author autor3 = new Author();
        autor3.setBirthday(DateConverter.asDate("21/12/1947"));
        autor3.setName("Stephen King");
        autor3.setNationality("Americano");
        
        authorRepository.save(autor1);
        authorRepository.save(autor2);
        authorRepository.save(autor3);
    }
    
    private void createBooks() throws Exception {
        Book book1 = new Book();
        book1.setTitle("Corrida Pela Herança");
        book1.setSummary("Livro onde pessoas correm pela herança de alguém");
        book1.setCategory(categoryRepository.loadById(1));
        book1.setAcquisition(DateConverter.asDate("10/10/2010"));
        book1.setLocation("Prateleira A4");
        book1.setPages(199);
        book1.setAuthors(new HashSet<>(authorRepository.listByName("sidney")));
        
        Book book2 = new Book();
        book2.setTitle("Loucura de Sara");
        book2.setSummary("Sara tá locona");
        book2.setCategory(categoryRepository.loadById(2));
        book2.setAcquisition(DateConverter.asDate("22/04/2017"));
        book2.setLocation("Balcão B2");
        book2.setPages(148);
        book2.setAuthors(new HashSet<>(authorRepository.listByName("paulo")));
        
        Book book3 = new Book();
        book3.setTitle("IT");
        book3.setSummary("Mas que coisa");
        book3.setCategory(categoryRepository.loadById(2));
        book3.setAcquisition(DateConverter.asDate("12/07/2016"));
        book3.setLocation("Prateleira C5");
        book3.setPages(333);
        book3.setAuthors(new HashSet<>(authorRepository.listByName("s")));
        
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
    
}
