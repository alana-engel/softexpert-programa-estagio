package com.softexpert.library.repository;

import java.util.List;

import com.softexpert.library.entity.BasicEntity;

public interface Repository<T extends BasicEntity> {

    public T save(T entity);

    public boolean delete(T entity);

    public boolean update(T entity);

    public T loadById(int id);

    public List<T> listAll();

}
