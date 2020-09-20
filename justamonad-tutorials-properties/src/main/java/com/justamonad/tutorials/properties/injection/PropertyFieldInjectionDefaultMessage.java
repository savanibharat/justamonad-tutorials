package com.justamonad.tutorials.properties.injection;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyFieldInjectionDefaultMessage {

	@Value("${justamonad.properties.welcome-messe:Hello World}")
	private String message;

	public String getMessage() {
		return message;
	}

}
