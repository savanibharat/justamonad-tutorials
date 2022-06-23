package com.justamonad.tutorials.object.mapping.invoice.request;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRequest {
    private Detail detail;
    private Invoicer invoicer;
    private List<PrimaryRecipient> primaryRecipients = new ArrayList<PrimaryRecipient>();
    private List<Item> items = new ArrayList<>();
    private Configuration configuration;
    private Amount amount;

    public Detail getDetail() {
        return detail;
    }
    public void setDetail(Detail detail) {
        this.detail = detail;
    }
    public Invoicer getInvoicer() {
        return invoicer;
    }
    public void setInvoicer(Invoicer invoicer) {
        this.invoicer = invoicer;
    }
    public List<PrimaryRecipient> getPrimaryRecipients() {
        return primaryRecipients;
    }
    public void setPrimaryRecipients(List<PrimaryRecipient> primaryRecipients) {
        this.primaryRecipients = primaryRecipients;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public Configuration getConfiguration() {
        return configuration;
    }
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    public Amount getAmount() {
        return amount;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
