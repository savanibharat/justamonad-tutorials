package com.justamonad.tutorials.spring.rest;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
	@RequestMapping(method = RequestMethod.GET, path = "/all-headers-map")
	ResponseEntity<String> sayHelloAllHeaders(@RequestHeader Map<String, String> headers);

	/**
	 * @see #sayHelloAllHeaders(Map)
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all-headers-multi-map")
	ResponseEntity<String> sayHelloAllHeaders(@RequestHeader MultiValueMap<String, String> headers);

	/**
	 * @see #sayHelloAllHeaders(Map)
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all-headers-httpheaders")
	ResponseEntity<String> sayHelloAllHeaders(@RequestHeader HttpHeaders headers);

	/**
	 * Returns the{@link HttpStatus#OK}.
	 * 
	 * Returns {@link HttpStatus#OK} if Request-ID header is present else returns
	 * {@link HttpStatus#BAD_REQUEST}.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/one-header")
	ResponseEntity<String> sayHelloSpecificHeader(@RequestHeader(name = "request-id") String requestId);

	/**
	 * Returns {@link HttpStatus#OK} along with request-id if request-id header is
	 * present else returns {@link HttpStatus#OK} and null request-id.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/optional-header")
	ResponseEntity<String> sayHelloOptionalHeaderNotRequired(
			@RequestHeader(name = "request-id", required = false) String requestId);

	/**
	 * Returns {@link HttpStatus#OK} with the value of header in request. If the
	 * value doesn't exists then it will return default value.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/optional-header-default-value")
	ResponseEntity<String> sayHelloOptionalHeaderDefaultValue(
			@RequestHeader(name = "request-id", defaultValue = "default-value") String requestId);

}
