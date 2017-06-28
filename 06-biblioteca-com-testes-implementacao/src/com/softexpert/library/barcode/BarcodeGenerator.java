package com.softexpert.library.barcode;

import com.softexpert.library.entity.Book;

public class BarcodeGenerator {

    public void generateFullBarcodeFor(Book book) {
        BarcodeInformation info = new BarcodeInformation()
                .countryCode("789")
                .companyCode("33333")
                .productCode(String.format("%04d", book.getId()));
        String barcode = generateFullBarcode(info);
        book.setBarcode(barcode);
    }
    
    public String generateFullBarcode(BarcodeInformation info) {
        String barcode = info.getBarcodeSuffix();
        if (!barcode.matches("^[0-9]{12}$")) {
            throw new IllegalArgumentException("Sufixo do código de barras deve ter 12 caracteres!");
        }
        return barcode + generateChecksum(barcode);
    }

    private int generateChecksum(String barcodeSuffix) {
        int[] numbers = barcodeSuffix.chars().map(Character::getNumericValue).toArray();
        int sumEven = numbers[1] + numbers[3] + numbers[5] + numbers[7] + numbers[9] + numbers[11];
        int sumOdd = numbers[0] + numbers[2] + numbers[4] + numbers[6] + numbers[8] + numbers[10];
        int result = sumOdd + sumEven * 3;
        int checksum = 10 - (result % 10);
        return checksum;
    }

}
