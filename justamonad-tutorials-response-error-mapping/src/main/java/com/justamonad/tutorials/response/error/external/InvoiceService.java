package com.justamonad.tutorials.response.error.external;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.justamonad.tutorials.response.error.mapping.Error;
import com.justamonad.tutorials.response.error.objects.Invoice;

@Service
public class InvoiceService {

	public Response checkInvoice(Invoice invoice) {

		// check with downstream invoice service whether this invoice is good or
		// not.

		return Response.status(Response.Status.BAD_REQUEST).entity(constructDummyError()).build();
	}

	private Error constructDummyError() {
		return new Error.Builder().withCode(500).withDomain("global").withMessage("Internal Service Error").build();
	}

}
