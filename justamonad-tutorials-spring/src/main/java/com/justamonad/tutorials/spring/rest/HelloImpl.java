package com.justamonad.tutorials.spring.rest;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloImpl implements IHello {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloAllHeaders(Map<String, String> headers) {
		headers.forEach((k, v) -> System.out.println(k + " : " + v));
		return new ResponseEntity<String>("request-id : " + headers.get("request-id"), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloAllHeaders(MultiValueMap<String, String> headers) {
		headers.forEach((k, v) -> System.out.println(k + " : " + v));
		return new ResponseEntity<String>("request-id : " + headers.get("request-id").get(0), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloAllHeaders(HttpHeaders headers) {
		headers.forEach((k, v) -> System.out.println(k + " : " + v));
		return new ResponseEntity<String>("request-id : " + headers.get("request-id").get(0), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloSpecificHeader(String requestId) {
		return new ResponseEntity<String>("request-id : " + requestId, HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloOptionalHeaderNotRequired(String requestId) {
		final ResponseEntity<String> responseEntity;
		if (requestId != null) {
			responseEntity = new ResponseEntity<String>("request-id : " + requestId, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("request-id : " + requestId, HttpStatus.OK);
		}
		return responseEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> sayHelloOptionalHeaderDefaultValue(String requestId) {
		return new ResponseEntity<String>("request-id : " + requestId, HttpStatus.OK);
	}

}
