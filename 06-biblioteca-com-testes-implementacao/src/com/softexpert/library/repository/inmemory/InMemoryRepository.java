package com.softexpert.library.repository.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softexpert.library.entity.BasicEntity;
import com.softexpert.library.repository.Repository;
import com.softexpert.library.repository.SequentialIdGenerator;

public abstract class InMemoryRepository<T extends BasicEntity> implements Repository<T> {

    protected Map<Integer, T> data = new HashMap<>();
    private SequentialIdGenerator idGenerator = new SequentialIdGenerator();

    @Override
    public T save(T entity) {
        idGenerator.calculateNextId(entity);
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean delete(T entity) {
        return data.remove(entity.getId()) != null;
    }

    @Override
    public boolean update(T entity) {
        T savedEntity = data.get(entity.getId());
        if (savedEntity == null) {
            return false;
        }
        return data.put(entity.getId(), entity) != null;
    }

    @Override
    public T loadById(int id) {
        return data.get(id);
    }

    @Override
    public List<T> listAll() {
        return new ArrayList<>(data.values());
    }

}
