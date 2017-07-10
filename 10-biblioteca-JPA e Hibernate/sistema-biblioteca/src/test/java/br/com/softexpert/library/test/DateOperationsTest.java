package br.com.softexpert.library.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import br.com.softexpert.library.exception.DateException;
import br.com.softexpert.library.library.DateOperations;

public class DateOperationsTest {
	DateOperations op = new DateOperations();
	@Test
	public void testGetConvertedDate(){
		String date = "29/12/1994";
		Date r;
		r=op.getConvertedDate(date);
		String dateToString = op.convertDateToString(r);
		assertEquals(dateToString,date);
	}
	@Test(expected = DateException.class)
	public void testGetConvertedDateError() throws RuntimeException{
		op.getConvertedDate("aaa");
	}
	
	@Test
	public void testCompareDateFalse(){
		String date = "29/12/9000";
		Date r;
		r=op.getConvertedDate(date);
		assertFalse(op.CompareDate(r));
	}
	
	@Test
	public void testCompareDateTrue(){
		String date = "29/12/2000";
		Date r;
		r=op.getConvertedDate(date);
		assertTrue(op.CompareDate(r));
	}
	
	
	
}
