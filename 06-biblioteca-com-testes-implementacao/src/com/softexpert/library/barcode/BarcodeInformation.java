package com.softexpert.library.barcode;

public class BarcodeInformation {

    private String countryCode;
    private String companyCode;
    private String productCode;

    public BarcodeInformation() {

    }

    public BarcodeInformation(String countryCode, String companyCode, String productCode) {
        this.countryCode = countryCode;
        this.companyCode = companyCode;
        this.productCode = productCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }
    public BarcodeInformation countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    public BarcodeInformation companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }
    public String getProductCode() {
        return this.productCode;
    }
    public BarcodeInformation productCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public String getBarcodeSuffix() {
        return countryCode + companyCode + productCode;
    }

}
