package com.softexpert.library.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.softexpert.library.barcode.BarcodeGenerator;
import com.softexpert.library.repository.InvalidEntityException;
import com.softexpert.library.utils.DateConverter;

public class Book extends BasicEntity {

    private String title;
    private String summary;
    private String barcode;
    private int pages;
    private String location;
    private Date acquisition;
    private Category category;
    private Set<Author> authors = new HashSet<>();
    private BarcodeGenerator generator = new BarcodeGenerator();

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAcquisition() {
        return this.acquisition;
    }

    public void setAcquisition(Date acquisition) {
        this.acquisition = acquisition;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public boolean addAuthor(Author author) {
        return this.authors.add(author);
    }

    public void validate() {
        if (!containsTitle()) {
            throw new InvalidEntityException("O Livro deve conter um título!");
        }
        if (!hasAtLeastOneAuthor()) {
            throw new InvalidEntityException("O Livro deve conter pelo menos um autor!");
        }
        if (!containsBarCode()) {
            throw new InvalidEntityException("O Livro deve conter um código de barras!");
        }
        if (category == null) {
            throw new InvalidEntityException("O Livro deve pertencer a uma categoria!");
        }
        if (acquisition == null) {
            throw new InvalidEntityException("O Livro deve possuir uma data de aquisição");
        }
        if (acquisition.after(new Date())) {
            throw new InvalidEntityException("O Livro não pode possuir uma data de aquisição no futuro");
        }
    }

    public boolean containsTitle() {
        return title != null && !title.trim().isEmpty();
    }

    public boolean containsBarCode() {
        return barcode != null && !barcode.trim().isEmpty();
    }

    public boolean hasAtLeastOneAuthor() {
        return !authors.isEmpty();
    }

    public void generateBarCode() {
        generator.generateFullBarcodeFor(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Título: " + title + " - " + pages + " páginas" + " (" + id + ")");
        builder.append("\nResumo: " + summary);
        builder.append("\nCódigo de barras: " + barcode);
        builder.append("\nData de aquisição: " + DateConverter.asString(acquisition));
        builder.append("\nCategoria: " + category);
        builder.append("\nAutor(es): " + authors);
        return builder.toString();
    }

}
