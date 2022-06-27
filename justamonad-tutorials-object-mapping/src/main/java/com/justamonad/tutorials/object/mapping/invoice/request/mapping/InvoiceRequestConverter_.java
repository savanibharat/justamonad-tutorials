package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import com.justamonad.tutorials.object.mapping.invoice.request.pojo.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public final class InvoiceRequestConverter_ {

    private final InvoicerConverter invoicerConverter;
    private final AmountConverter amountConverter;
    private final ConfigurationConverter configurationConverter;
    private final DetailConverter detailConverter;
    private final ItemsConverter itemsConverter;
    private final PrimaryRecipientsConverter primaryRecipientsConverter;

//    @Autowired
//    public InvoiceRequestConverter_(
//            InvoicerConverter invoicerConverter,
//            AmountConverter amountConverter,
//            ConfigurationConverter configurationConverter,
//            DetailConverter detailConverter,
//            ItemsConverter itemsConverter,
//            PrimaryRecipientsConverter primaryRecipientsConverter) {
//        this.invoicerConverter = invoicerConverter;
//        this.amountConverter = amountConverter;
//        this.configurationConverter = configurationConverter;
//        this.detailConverter = detailConverter;
//        this.itemsConverter = itemsConverter;
//        this.primaryRecipientsConverter = primaryRecipientsConverter;
//    }

    public Invoice to() {
        Invoice invoice = new Invoice();
        invoice.setInvoicer(invoicerConverter.to());
        invoice.setAmount(amountConverter.to());
        invoice.setConfiguration(configurationConverter.to());
        invoice.setDetail(detailConverter.to());
        invoice.setItems(itemsConverter.to());
        invoice.setPrimaryRecipients(primaryRecipientsConverter.to());
        return invoice;
    }

}
