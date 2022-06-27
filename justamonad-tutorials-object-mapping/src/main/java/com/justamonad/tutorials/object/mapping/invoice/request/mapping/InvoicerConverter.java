package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Invoicer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public final class InvoicerConverter {

    private final AddressConverter addressConverter;
    private final NameConverter nameConverter;

    public Invoicer to() {
        Invoicer invoicer = new Invoicer();
        invoicer.setAddress(addressConverter.to());
        invoicer.setAdditionalNotes("additional notes");
        invoicer.setEmailAddress("email");
        invoicer.setLogoUrl("");
        invoicer.setName(nameConverter.to());
        invoicer.setTaxId("");
        invoicer.setWebsite("https://www.justamonad.com");
        return invoicer;
    }

}
