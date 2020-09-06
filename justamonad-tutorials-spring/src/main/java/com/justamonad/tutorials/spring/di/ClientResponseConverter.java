package com.justamonad.tutorials.spring.di;

import javax.inject.Named;

@Named
public class ClientResponseConverter {

	public ClientResponseDTO convert(ClientResponse clientResponse ) {
		return new ClientResponseDTO();
	}
	
}
