package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Amount;
import org.springframework.stereotype.Component;

@Component
public final class AmountConverter {

    public Amount to() {
        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue("10.00");
        return amount;
    }

}
