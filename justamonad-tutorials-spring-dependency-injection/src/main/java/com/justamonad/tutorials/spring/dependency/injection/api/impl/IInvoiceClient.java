package com.justamonad.tutorials.spring.dependency.injection.api.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;

@Path("/v1/invoices")
@Produces("application/json")
public interface IInvoiceClient {

	@GET
	public InvoiceResponse getInvoice(String invoiceId);

}
