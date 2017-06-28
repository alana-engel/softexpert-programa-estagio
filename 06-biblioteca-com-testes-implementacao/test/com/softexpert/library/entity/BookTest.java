package com.softexpert.library.entity;

import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.softexpert.library.repository.InvalidEntityException;

public class BookTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Book book = new Book();

    @Test
    public void shouldValidateBook() {
        book.setTitle("IT");
        book.addAuthor(new Author());
        book.setAcquisition(yesterday());
        book.setBarcode("111");
        book.setCategory(new Category());
        book.validate();
    }

    @Test
    public void testInvalidTitle() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve conter um título");
        book.validate();
    }

    @Test
    public void testInvalidAuthor() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve conter pelo menos um autor");
        book.setTitle("IT");
        book.validate();
    }

    @Test
    public void testInvalidBarcode() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve conter um código de barras");
        book.setTitle("IT");
        book.addAuthor(new Author());
        book.validate();
    }

    @Test
    public void testInvalidCategory() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve conter uma categoria");
        book.setTitle("IT");
        book.addAuthor(new Author());
        book.setBarcode("111");
        book.validate();
    }

    @Test
    public void testAcquisitionNull() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve possuir uma data de aquisição");
        book.setTitle("IT");
        book.addAuthor(new Author());
        book.setBarcode("111");
        book.setCategory(new Category());
        book.validate();
    }

    @Test
    public void testAcquisitionInFuture() {
        expectedException.expect(InvalidEntityException.class);
        expectedException.expectMessage("deve possuir uma data de aquisição");
        book.setTitle("IT");
        book.addAuthor(new Author());
        book.setBarcode("111");
        book.setCategory(new Category());
        book.setAcquisition(tomorrow());
        book.validate();
    }

    private Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    private Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

}
