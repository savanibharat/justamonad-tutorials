package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;

public class RequestValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 4550211563533067086L;

	public RequestValidationException(List<ErrorData> errorDatas) {
		super(errorDatas.get(0).message());
	}

}
