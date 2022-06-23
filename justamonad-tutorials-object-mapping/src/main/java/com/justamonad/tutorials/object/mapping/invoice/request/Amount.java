package com.justamonad.tutorials.object.mapping.invoice.request;

public class Amount {
    private String currencyCode;
    private String value;
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
