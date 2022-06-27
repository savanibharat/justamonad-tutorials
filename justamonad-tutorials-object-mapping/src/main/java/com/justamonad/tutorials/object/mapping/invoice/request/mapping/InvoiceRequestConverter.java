package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Address;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Amount;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Configuration;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Detail;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Invoice;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Invoicer;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Item;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Name;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.PartialPayment;
import com.justamonad.tutorials.object.mapping.invoice.request.pojo.PrimaryRecipient;

import java.util.Collections;
import java.util.List;

public final class InvoiceRequestConverter {

    public Invoice to() {
        Invoice invoice = new Invoice();
        invoice.setInvoicer(getInvoicer());
        invoice.setAmount(getAmount());
        invoice.setConfiguration(getConfiguration());
        invoice.setDetail(getDetail());
        invoice.setItems(getItems());
        invoice.setPrimaryRecipients(getPrimaryRecipients());
        return invoice;
    }

    private Invoicer getInvoicer() {
        Invoicer invoicer = new Invoicer();
        invoicer.setAddress(getAddress());
        invoicer.setAdditionalNotes("additional notes");
        invoicer.setEmailAddress("email");
        invoicer.setLogoUrl("");
        invoicer.setName(getName());
        invoicer.setTaxId("");
        invoicer.setWebsite("https://www.justamonad.com");
        return invoicer;
    }

    private Address getAddress() {
        Address address = new Address();
        address.setAddressLine1("123 Fake ST");
        address.setAddressLine2("APT 123");
        address.setAdminArea1("San Jose");
        address.setCountryCode("US");
        address.setPostalCode("95112");
        return address;
    }

    private Name getName() {
        Name name = new Name();
        name.setGivenName("John");
        name.setSurname("Doe");
        return name;
    }

    private Amount getAmount() {
        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue("10.00");
        return amount;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setAllowTip(Boolean.TRUE);
        configuration.setPartialPayment(getPartialPayment());
        configuration.setTaxInclusive(Boolean.TRUE);
        configuration.setTemplateId("template id");
        configuration.setTaxCalculatedAfterDiscount(Boolean.TRUE);
        return configuration;
    }

    private PartialPayment getPartialPayment() {
        PartialPayment partialPayment = new PartialPayment();
        partialPayment.setAllowPartialPayment(Boolean.FALSE);
        return partialPayment;
    }

    private Detail getDetail() {
        Detail detail = new Detail();
        detail.setCurrencyCode("USD");
        detail.setInvoiceDate("11-11-2022");
        detail.setInvoiceNumber("INV-11-11-11-11111");
        detail.setMemo("memo");
        detail.setNote("note");
        detail.setReference("reference 123");
        return detail;
    }

    private List<Item> getItems() {
        Item item = new Item();
        return Collections.singletonList(item);
    }

    private List<PrimaryRecipient> getPrimaryRecipients() {
        PrimaryRecipient primaryRecipient = new PrimaryRecipient();
        return Collections.singletonList(primaryRecipient);
    }

}
