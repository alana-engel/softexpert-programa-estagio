package com.softexpert.library.entity;

public abstract class BasicEntity {

    protected int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BasicEntity) {
            BasicEntity entity = (BasicEntity) obj;
            return entity.id == id;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
}
