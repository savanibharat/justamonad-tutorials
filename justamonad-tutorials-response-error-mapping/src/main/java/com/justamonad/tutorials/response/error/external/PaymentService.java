package com.justamonad.tutorials.response.error.external;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.justamonad.tutorials.response.error.mapping.Error;
import com.justamonad.tutorials.response.error.objects.Transaction;

@Service
public class PaymentService {

	public Response executePayment(Transaction transaction) {
		// execute transaction by calling downstream services
		return Response.status(Response.Status.BAD_REQUEST).entity(constructDummyError()).build();
	}

	private Error constructDummyError() {
		return new Error.Builder().withCode(400).withDomain("global").withLocation("parameter")
				.withMessage("current is missing in request").build();
	}

}
