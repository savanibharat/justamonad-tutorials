package com.justamonad.tutorials.object.mapping.invoice.request;

public class Discount {
    private String percent;
    private Amount amount;
    public String getPercent() {
        return percent;
    }
    public void setPercent(String percent) {
        this.percent = percent;
    }
    public Amount getAmount() {
        return amount;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}

