package com.justamonad.tutorials.object.mapping.invoice.request;

public class PrimaryRecipient {
    private BillingInfo billingInfo;
    private ShippingInfo shippingInfo;
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }
    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }
    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }
}
