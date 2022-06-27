package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.PrimaryRecipient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public final class PrimaryRecipientsConverter {

    public List<PrimaryRecipient> to() {
        PrimaryRecipient primaryRecipient = new PrimaryRecipient();
        return Collections.singletonList(primaryRecipient);
    }

}
