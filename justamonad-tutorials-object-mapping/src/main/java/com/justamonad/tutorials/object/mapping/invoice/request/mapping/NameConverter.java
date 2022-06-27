package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Name;
import org.springframework.stereotype.Component;

@Component
public final class NameConverter {

    public Name to() {
        Name name = new Name();
        name.setGivenName("John");
        name.setSurname("Doe");
        return name;
    }

}
