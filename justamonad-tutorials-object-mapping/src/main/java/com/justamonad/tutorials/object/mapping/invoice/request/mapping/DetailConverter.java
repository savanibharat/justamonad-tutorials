package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Detail;
import org.springframework.stereotype.Component;

@Component
public final class DetailConverter {

    public Detail to() {
        Detail detail = new Detail();
        detail.setCurrencyCode("USD");
        detail.setInvoiceDate("11-11-2022");
        detail.setInvoiceNumber("INV-11-11-11-11111");
        detail.setMemo("memo");
        detail.setNote("note");
        detail.setReference("reference 123");
        return detail;
    }

}
