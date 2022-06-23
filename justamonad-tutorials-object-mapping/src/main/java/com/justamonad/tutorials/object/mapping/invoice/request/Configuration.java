package com.justamonad.tutorials.object.mapping.invoice.request;

public class Configuration {
    private PartialPayment partialPayment;
    private Boolean allowTip;
    private Boolean taxCalculatedAfterDiscount;
    private Boolean taxInclusive;
    private String templateId;
    public PartialPayment getPartialPayment() {
        return partialPayment;
    }
    public void setPartialPayment(PartialPayment partialPayment) {
        this.partialPayment = partialPayment;
    }
    public Boolean getAllowTip() {
        return allowTip;
    }
    public void setAllowTip(Boolean allowTip) {
        this.allowTip = allowTip;
    }
    public Boolean getTaxCalculatedAfterDiscount() {
        return taxCalculatedAfterDiscount;
    }
    public void setTaxCalculatedAfterDiscount(Boolean taxCalculatedAfterDiscount) {
        this.taxCalculatedAfterDiscount = taxCalculatedAfterDiscount;
    }
    public Boolean getTaxInclusive() {
        return taxInclusive;
    }
    public void setTaxInclusive(Boolean taxInclusive) {
        this.taxInclusive = taxInclusive;
    }
    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}

