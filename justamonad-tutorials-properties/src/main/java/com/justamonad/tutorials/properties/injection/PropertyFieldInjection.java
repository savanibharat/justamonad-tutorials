package com.justamonad.tutorials.properties.injection;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyFieldInjection {

	@Value("${justamonad.properties.welcome-message}")
	private String message;

	public String getMessage() {
		return message;
	}

}
