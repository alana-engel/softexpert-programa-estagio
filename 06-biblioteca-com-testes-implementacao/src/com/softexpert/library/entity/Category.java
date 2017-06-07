package com.softexpert.library.entity;

public class Category extends BasicEntity {

    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "("+id+") "+description;
    }

}
