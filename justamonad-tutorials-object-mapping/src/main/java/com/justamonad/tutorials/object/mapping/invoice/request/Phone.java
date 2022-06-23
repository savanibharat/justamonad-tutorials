package com.justamonad.tutorials.object.mapping.invoice.request;

public class Phone {
    private String countryCode;
    private String nationalNumber;
    private String phoneType;
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getNationalNumber() {
        return nationalNumber;
    }
    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }
    public String getPhoneType() {
        return phoneType;
    }
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}

