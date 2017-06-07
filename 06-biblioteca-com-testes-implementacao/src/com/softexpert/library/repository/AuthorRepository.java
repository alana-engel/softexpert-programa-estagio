package com.softexpert.library.repository;

import java.util.List;

import com.softexpert.library.entity.Author;

public interface AuthorRepository extends Repository<Author> {

    public List<Author> listByName(String authorName);
}
