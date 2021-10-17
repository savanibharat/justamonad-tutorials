package com.justamonad.tutorials.vavr.trydemo;

import java.util.function.Function;

import javax.ws.rs.core.Response;

import io.vavr.control.Try;

public final class TryDemo {

	public static void main(String[] args) throws Throwable {
		TryDemo t = new TryDemo();
//		t.throwEx();
		
		Try<Response> flatMap = t.validateRequest("").flatMap(s -> t.throwEx());
		System.out.println(flatMap);
		
		
	}

	private Try<Response> throwEx() {
		return Try.of(() -> callFailureHttpClient(false));
	}

	private Response callFailureHttpClient(boolean pass) {
		if (pass) {
			return Response.ok().build();
		}
		throw new NullPointerException();
	}

	private Try<String> validateRequest(String request) {
		if (request != null) {
			return Try.of(() -> request);
		}
		return Try.failure(new NullPointerException());
	}

	private void callHttp() {
		callFailureHttpClient(false);
	}

}
