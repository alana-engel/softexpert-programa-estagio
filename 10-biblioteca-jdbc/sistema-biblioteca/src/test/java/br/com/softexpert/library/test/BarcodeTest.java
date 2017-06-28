package br.com.softexpert.library.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.softexpert.library.library.Barcode;

public class BarcodeTest {
	Barcode barcode = new Barcode();
	@Test
	public void testGenerateBarcode(){
		String bcode = barcode.getBarcode(8);
		assertTrue((bcode.length()==16));
	}
	@Test
	public void testGenerateBarcode2(){
		String bcode = barcode.getBarcode(87);
		assertTrue((bcode.length()==16));
	}
	@Test
	public void testGenerateBarcode3(){
		String bcode = barcode.getBarcode(87);
		String code ="789 12345 0087 8";
		assertEquals(bcode,code);
	}
}
