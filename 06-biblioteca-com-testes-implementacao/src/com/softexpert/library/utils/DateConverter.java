package com.softexpert.library.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public static String asString(Date date) {
        return dateFormat.format(date);
    }
    
    public static Date asDate(String dateAsString) throws ParseException {
        return dateFormat.parse(dateAsString);
    }

}
