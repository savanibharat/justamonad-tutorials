package com.justamonad.tutorials.spring.dependency.injection.api.impl;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;

public interface IInvoiceClient {

	public InvoiceResponse getInvoice(String invoiceId);

}
