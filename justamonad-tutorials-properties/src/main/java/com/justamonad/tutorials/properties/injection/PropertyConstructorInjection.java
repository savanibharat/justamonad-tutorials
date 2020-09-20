package com.justamonad.tutorials.properties.injection;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyConstructorInjection {

	private final String message;
	
	public PropertyConstructorInjection(@Value("${justamonad.properties.welcome-message}") String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
