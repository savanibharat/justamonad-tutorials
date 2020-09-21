package com.justamonad.tutorials.spring.di;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
public class ResponseHandlerConstructorInjection {

	private final ErrorHandler errorHandler;
	private final ClientResponseConverter clientResponseConverter;

	@Inject
	public ResponseHandlerConstructorInjection(ErrorHandler errorHandler,
			ClientResponseConverter clientResponseConverter) {
		this.errorHandler = errorHandler;
		this.clientResponseConverter = clientResponseConverter;
	}

	public ClientResponseDTO handleResponse(Response response) {
		errorHandler.handleError(response);

		ClientResponse clientResponse = response.readEntity(ClientResponse.class);
		// convert clientResponse to domain object and return.
		return clientResponseConverter.convert(clientResponse);
	}

}
