package com.justamonad.tutorials.object.mapping.invoice.request;

public class PartialPayment {
    private Boolean allowPartialPayment;
    private MinimumAmountDue minimumAmountDue;
    public Boolean getAllowPartialPayment() {
        return allowPartialPayment;
    }
    public void setAllowPartialPayment(Boolean allowPartialPayment) {
        this.allowPartialPayment = allowPartialPayment;
    }
    public MinimumAmountDue getMinimumAmountDue() {
        return minimumAmountDue;
    }
    public void setMinimumAmountDue(MinimumAmountDue minimumAmountDue) {
        this.minimumAmountDue = minimumAmountDue;
    }
}

