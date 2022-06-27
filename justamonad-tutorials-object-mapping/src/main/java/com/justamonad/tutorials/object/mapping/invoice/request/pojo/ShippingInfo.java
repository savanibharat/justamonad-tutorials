package com.justamonad.tutorials.object.mapping.invoice.request.pojo;

public class ShippingInfo {
    private Name name;
    private Address address;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
