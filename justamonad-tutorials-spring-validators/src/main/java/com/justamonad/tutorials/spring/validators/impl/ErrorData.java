package com.justamonad.tutorials.spring.validators.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ErrorData {
	
	private String message;
	private List<String> errorArgs;

	private ErrorData(String message, List<String> errorArgs) {
		this.message = message;
		this.errorArgs = Collections.unmodifiableList(errorArgs);
	}

	public static ErrorData of(String message, String... errorArgs) {
		return new ErrorData(message, Arrays.asList(errorArgs));
	}

	public String message() {
		return message;
	}

	public List<String> errorArgs() {
		return errorArgs;
	}

}
