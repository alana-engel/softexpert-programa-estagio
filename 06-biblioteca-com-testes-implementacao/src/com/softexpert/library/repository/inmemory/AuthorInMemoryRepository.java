package com.softexpert.library.repository.inmemory;

import java.util.List;
import java.util.stream.Collectors;

import com.softexpert.library.entity.Author;
import com.softexpert.library.repository.AuthorRepository;

public class AuthorInMemoryRepository extends InMemoryRepository<Author> implements AuthorRepository {

    @Override
    public List<Author> listByName(String authorName) {
        return data.values()
                .stream()
                .filter(author -> author.getName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());
    }

}
