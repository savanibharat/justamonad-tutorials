package com.justamonad.tutorials.spring.validators.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorData {

	private final String message;
	private final List<String> errorArgs;

	private ErrorData(String message, List<String> errorArgs) {
		this.message = message;
		this.errorArgs = Collections.unmodifiableList(errorArgs);
	}

	static ErrorData of(String message, String... errorArgs) {
		List<String> errors = Arrays.stream(errorArgs).collect(Collectors.toList());
		return new ErrorData(message, errors);
	}

	public String message() {
		return message;
	}

	public List<String> errorArgs() {
		return errorArgs;
	}

	@Override
	public String toString() {
		return "ErrorData [message=" + message + ", errorArgs=" + errorArgs + "]";
	}

}
