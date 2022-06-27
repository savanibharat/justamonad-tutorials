package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.PartialPayment;

public class PartialPaymentConverter {

    public PartialPayment to() {
        PartialPayment partialPayment = new PartialPayment();
        partialPayment.setAllowPartialPayment(Boolean.FALSE);
        return partialPayment;
    }

}
