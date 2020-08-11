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

	@Inject
	private Function<String, WebTarget> clientFactory;

	@Inject
	private ErrorHandler errorHandler;

	@Override
	public InvoiceResponse getInvoice(String invoiceId) {
		WebTarget webTarget = clientFactory.apply("v2/invoicing/invoices/" + invoiceId);
		Response response = webTarget.request().get();
		errorHandler.handleError(response);
		return response.readEntity(InvoiceResponse.class);
	}

}
