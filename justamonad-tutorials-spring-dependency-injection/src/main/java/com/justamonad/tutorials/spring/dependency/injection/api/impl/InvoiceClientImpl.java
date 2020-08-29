package com.justamonad.tutorials.spring.dependency.injection.api.impl;

import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;

@Named
@Scope("request")
public class InvoiceClientImpl implements IInvoiceClient {

	private static final String INVOICE_TARGET = "v2/invoicing/invoices/";

	private final Function<String, WebTarget> clientFactory;
	private final ErrorHandler errorHandler;

	@Inject
	public InvoiceClientImpl(
			Function<String, WebTarget> clientFactory, 
			ErrorHandler errorHandler) {
		this.clientFactory = clientFactory;
		this.errorHandler = errorHandler;
	}

	@Override
	public InvoiceResponse getInvoice(String invoiceId) {
		WebTarget webTarget = clientFactory.apply(INVOICE_TARGET + invoiceId);
		Response response = webTarget.request().get();
		errorHandler.handleError(response);
		return response.readEntity(InvoiceResponse.class);
	}

}
