package com.justamonad.tutorials.spring.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "/v1/hello")
public interface IHello {

	/**
	 * Prints all the headers and returns {@link HttpStatus#OK}.
	 * 
	 * Returns {@link HttpStatus#OK} if headers are present else returns
	 * {@link HttpStatus#BAD_REQUEST}.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all-headers")
	public ResponseEntity<String> sayHelloAllHeaders(@RequestHeader Map<String, String> headers);

	/**
	 * Returns the{@link HttpStatus#OK}.
	 * 
	 * Returns {@link HttpStatus#OK} if Request-ID header is present else returns
	 * {@link HttpStatus#BAD_REQUEST}.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/one-header")
	public ResponseEntity<String> sayHelloSpecificHeader(@RequestHeader(name = "Request-ID") String requestId);

	/**
	 * Returns {@link HttpStatus#OK} if Request-ID header is present else returns
	 * {@link HttpStatus#BAD_REQUEST}.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/optional-header")
	public ResponseEntity<String> sayHelloOptionalHeaderNotRequired(
			@RequestHeader(name = "some-header", required = false) String someHeader);

	/**
	 * Returns {@link HttpStatus#OK} with the value of header in request. If the
	 * value doesn't exists then it will return default value.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/optional-header-default-value")
	public ResponseEntity<String> sayHelloOptionalHeaderDefaultValue(
			@RequestHeader(name = "some-header", defaultValue = "some-header-default-value") String someHeader);

}
