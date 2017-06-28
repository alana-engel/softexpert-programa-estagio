package com.softexpert.library.barcode;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.softexpert.library.entity.Book;

public class BarcodeGeneratorTest {

    private BarcodeGenerator generator = new BarcodeGenerator();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldGenerateCorrectBarcodeForCocaCola() {
        BarcodeInformation info = new BarcodeInformation("789", "49000", "1151");
        String barcode = generator.generateFullBarcode(info);
        assertThat(barcode, equalTo("7894900011517"));
    }

    @Test
    public void shouldGenerateCorrectBarcodeForKitKat() {
        BarcodeInformation info = new BarcodeInformation("789", "10001", "1907");
        String barcode = generator.generateFullBarcode(info);
        assertThat(barcode, equalTo("7891000119075"));
    }

    @Test
    public void shouldGenerateCorrectBarcodeForBook() {
        Book book = new Book();
        book.setId(15);
        generator.generateFullBarcodeFor(book);
        assertThat(book.getBarcode(), equalTo("7893333300151"));
    }

    @Test
    public void shouldThrowsExceptionWhenGeneratingForWrongLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Sufixo do código de barras deve ter 12 caracteres!");
        BarcodeInformation info = new BarcodeInformation("789", "490000", "1151");
        generator.generateFullBarcode(info);
    }

    @Test
    public void shouldThrowsExceptionWhenGeneratingForBookWithWrongCode() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Sufixo do código de barras deve ter 12 caracteres!");
        Book book = new Book();
        book.setId(99999);
        generator.generateFullBarcodeFor(book);
    }

}
