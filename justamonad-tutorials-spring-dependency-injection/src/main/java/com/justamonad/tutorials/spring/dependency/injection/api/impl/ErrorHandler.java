package com.justamonad.tutorials.spring.dependency.injection.api.impl;

import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

@Named
public class ErrorHandler {

	public void handleError(Response response) {
		if (response.getStatusInfo().getFamily() != Family.SUCCESSFUL) {
			final int statusCode = response.getStatusInfo().getStatusCode();
			throw new WebApplicationException(statusCode);
		}
		// else do nothing.
	}

}
