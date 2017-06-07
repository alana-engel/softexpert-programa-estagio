package com.softexpert.library.entity;

import java.util.Date;

import com.softexpert.library.utils.DateConverter;

public class Author extends BasicEntity {
    
    private String name;
    private Date birthday;
    private String nationality;
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getNationality() {
        return this.nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    @Override
    public String toString() {
        return "(" + getId() + ") " + this.name + ", nascido em " + DateConverter.asString(this.birthday) + ", de nacionalidade " + this.nationality;
    }

}
