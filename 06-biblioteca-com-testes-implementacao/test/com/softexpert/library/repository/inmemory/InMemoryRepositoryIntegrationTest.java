package com.softexpert.library.repository.inmemory;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.softexpert.library.entity.Author;
import com.softexpert.library.entity.Book;
import com.softexpert.library.entity.Category;
import com.softexpert.library.repository.BookRepository;
import com.softexpert.library.repository.Repository;
import com.softexpert.library.repository.RepositoryFactory;

public class InMemoryRepositoryIntegrationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private BookRepository bookRepository;
    private Repository<Author> authorRepository;
    private Repository<Category> categoryRepository;

    @Before
    public void setUp() {
        bookRepository = (BookRepository) RepositoryFactory.getRepositoryFrom(Book.class);
        authorRepository = RepositoryFactory.getRepositoryFrom(Author.class);
        categoryRepository = RepositoryFactory.getRepositoryFrom(Category.class);
    }

    @Test
    public void executeIntegrationTest() {

        Category category = addCategory();
        Author author = addAuthor();

        Book book = new Book();
        book.setTitle("Livro Teste 1");
        book.setAcquisition(yesterday());
        book.setCategory(category);
        book.addAuthor(author);

        book = bookRepository.save(book);
        assertThat(book.getId(), equalTo(1));
        assertThat(bookRepository.listAll().size(), equalTo(1));
        
        book.setTitle("Livro Teste Alterado");
        boolean updated = bookRepository.update(book);
        assertThat(updated, is(true));
        assertThat(bookRepository.listAll().size(), equalTo(1));
        
        boolean deleted = bookRepository.delete(book);
        assertThat(deleted, is(true));
        assertThat(bookRepository.listAll().size(), equalTo(0));
    }

    private Author addAuthor() {
        Author author = createFakeAuthor();
        return authorRepository.save(author);
    }

    private Category addCategory() {
        Category category = createFakeCategory();
        return categoryRepository.save(category);
    }

    private Category createFakeCategory() {
        Category category = new Category();
        category.setDescription("Fake Category");
        return category;
    }

    private Author createFakeAuthor() {
        Author author = new Author();
        author.setName("Fake Author");
        author.setBirthday(new Date());
        author.setNationality("Irish");
        return author;
    }

    private Date yesterday() {
        return new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }

}
