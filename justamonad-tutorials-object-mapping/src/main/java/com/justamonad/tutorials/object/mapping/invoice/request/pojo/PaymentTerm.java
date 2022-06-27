package com.justamonad.tutorials.object.mapping.invoice.request.pojo;

public class PaymentTerm {
    private String termType;
    private String dueDate;
    public String getTermType() {
        return termType;
    }
    public void setTermType(String termType) {
        this.termType = termType;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

