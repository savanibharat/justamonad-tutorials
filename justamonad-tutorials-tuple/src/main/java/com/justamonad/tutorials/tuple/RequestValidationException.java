package com.justamonad.tutorials.tuple;

public final class RequestValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 4550211563533067086L;

	public RequestValidationException(String errorDatas) {
		super(errorDatas);
	}

}
