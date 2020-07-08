package com.justamonad.tutorials.spring.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloImpl implements IHello {

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public ResponseEntity<String> sayHelloAllHeaders(Map<String, String> headers) {
		headers.forEach((k, v) -> System.out.println(k + " : " + v));
		return new ResponseEntity<String>(headers.get("Request-Id"), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public ResponseEntity<String> sayHelloSpecificHeader(String requestId) {
		return new ResponseEntity<String>(requestId, HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public ResponseEntity<String> sayHelloOptionalHeaderNotRequired(String someHeader) {
		final ResponseEntity<String> responseEntity;
		if (someHeader != null) {
			responseEntity = new ResponseEntity<String>(someHeader, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>(someHeader, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public ResponseEntity<String> sayHelloOptionalHeaderDefaultValue(String someHeader) {
		return new ResponseEntity<String>(someHeader, HttpStatus.OK);
	}

}
