package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Address;
import org.springframework.stereotype.Component;

@Component
public final class AddressConverter {

    public Address to() {
        Address address = new Address();
        address.setAddressLine1("123 Fake ST");
        address.setAddressLine2("APT 123");
        address.setAdminArea1("San Jose");
        address.setCountryCode("US");
        address.setPostalCode("95112");
        return address;
    }

}
