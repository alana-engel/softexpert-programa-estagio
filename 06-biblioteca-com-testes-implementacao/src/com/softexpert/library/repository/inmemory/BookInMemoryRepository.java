package com.softexpert.library.repository.inmemory;

import java.util.List;
import java.util.stream.Collectors;

import com.softexpert.library.entity.Author;
import com.softexpert.library.entity.Book;
import com.softexpert.library.entity.Category;
import com.softexpert.library.repository.AuthorRepository;
import com.softexpert.library.repository.BookRepository;
import com.softexpert.library.repository.RepositoryFactory;

public class BookInMemoryRepository extends InMemoryRepository<Book> implements BookRepository {

    @Override
    public Book save(Book book) {
        book.generateBarCode();
        book.validate();
        return super.save(book);
    }
    
    @Override
    public boolean update(Book book) {
        book.validate();
        return super.update(book);
    }
    
    @Override
    public List<Book> listAll() {
        return data.values()
                .stream()
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> listByTitle(String search) {
        return data.values()
                .stream()
                .filter(livro -> livro.getTitle().toLowerCase().contains(search.toLowerCase()))
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> listByCategory(Category category) {
        return data.values()
                .stream()
                .filter(livro -> livro.getCategory().getId() == category.getId())
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> listByAuthorName(String authorName) {

        AuthorRepository authorRepository = (AuthorRepository) RepositoryFactory.getRepositoryFrom(Author.class);
        List<Author> authorsByName = authorRepository.listByName(authorName);

        return data.values()
                .stream()
                .filter(livro -> {
                    for (Author author : authorsByName) {
                        if (livro.getAuthors().contains(author)) {
                            return true;
                        }
                    }
                    return false;
                })
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toList());
    }
    
}
