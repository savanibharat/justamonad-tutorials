package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public final class ConfigurationConverter {

    private final PartialPaymentConverter partialPaymentConverter;

    public Configuration to() {
        Configuration configuration = new Configuration();
        configuration.setAllowTip(Boolean.TRUE);
        configuration.setPartialPayment(partialPaymentConverter.to());
        configuration.setTaxInclusive(Boolean.TRUE);
        configuration.setTemplateId("template id");
        configuration.setTaxCalculatedAfterDiscount(Boolean.TRUE);
        return configuration;
    }

}
