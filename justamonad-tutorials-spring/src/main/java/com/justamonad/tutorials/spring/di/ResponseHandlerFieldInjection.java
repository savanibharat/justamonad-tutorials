package com.justamonad.tutorials.spring.di;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
public class ResponseHandlerFieldInjection {

	@Inject
	private ErrorHandler errorHandler;

	public Response handleResponse(Response response) {
		errorHandler.handleError(response);
		
		ClientResponse clientResponse = response.readEntity(ClientResponse.class);
		
		return Response.ok().entity(clientResponse).build();
	}

}
