package com.justamonad.tutorials.spring.di;

import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

@Named
public class ErrorHandler {

	public void handleError(Response response) {
		if (response.getStatusInfo().getFamily() != Family.SUCCESSFUL) {
			throw new IllegalStateException();
		}
		// else do nothing.
	}

}
