package com.justamonad.tutorials.object.mapping.invoice.request;

public class Item {
    private String name;
    private String description;
    private String quantity;
    private Amount unitAmount;
    private Tax tax;
    private Discount discount;
    private String unitOfMeasure;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public Amount getUnitAmount() {
        return unitAmount;
    }
    public void setUnitAmount(Amount unitAmount) {
        this.unitAmount = unitAmount;
    }
    public Tax getTax() {
        return tax;
    }
    public void setTax(Tax tax) {
        this.tax = tax;
    }
    public Discount getDiscount() {
        return discount;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
