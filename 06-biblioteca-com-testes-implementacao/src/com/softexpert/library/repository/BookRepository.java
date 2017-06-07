package com.softexpert.library.repository;

import java.util.List;

import com.softexpert.library.entity.Book;
import com.softexpert.library.entity.Category;

public interface BookRepository extends Repository<Book> {

    public List<Book> listByTitle(String search);

    public List<Book> listByCategory(Category category);

    public List<Book> listByAuthorName(String authorName);

}
