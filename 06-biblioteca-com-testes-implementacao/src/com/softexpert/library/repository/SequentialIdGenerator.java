package com.softexpert.library.repository;

import com.softexpert.library.entity.BasicEntity;

public class SequentialIdGenerator {

    private int lastId;
    
    public int calculateNextId(BasicEntity entity) {
        entity.setId(++lastId);
        return lastId;
    }
    
}
