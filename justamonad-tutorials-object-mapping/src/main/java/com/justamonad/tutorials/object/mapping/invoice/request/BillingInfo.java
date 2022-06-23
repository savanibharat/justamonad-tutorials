package com.justamonad.tutorials.object.mapping.invoice.request;

import java.util.ArrayList;
import java.util.List;

public class BillingInfo {
    private Name name;
    private Address address;
    private String emailAddress;
    private List<Phone> phones = new ArrayList<>();
    private String additionalInfoValue;
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
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public List<Phone> getPhones() {
        return phones;
    }
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    public String getAdditionalInfoValue() {
        return additionalInfoValue;
    }
    public void setAdditionalInfoValue(String additionalInfoValue) {
        this.additionalInfoValue = additionalInfoValue;
    }
}
