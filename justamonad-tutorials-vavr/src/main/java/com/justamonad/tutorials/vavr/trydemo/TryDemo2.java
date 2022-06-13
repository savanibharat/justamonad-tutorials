package com.justamonad.tutorials.vavr.trydemo;

import java.util.function.Function;

import javax.ws.rs.WebApplicationException;

import io.vavr.control.Try;

public final class TryDemo2 {

	public static void main(String[] args) throws Throwable {
		TryDemo2 t = new TryDemo2();
		t.userLoad("");
	}

	/**
	 * <ol>
	 * <li>Make outbound call</li>
	 * <li>Service Exception : catch WebApplicationException and throw it</li>
	 * <li>Generic Exception : catch Exception and throw it</li>
	 * </ol>
	 */
	public String userLoad(String accountNumber) {
		return Try
				.of(() -> makeOutBoundCall(accountNumber, false, true, false))
				.onSuccess(val -> System.out.println(val))
				.onFailure(throwable -> System.out.println("Ex is " + throwable))
				.get();
				// Use this as we can't throw throwable as it is checked exception or just do get with impunity.
				// If instance of Try is Failure it will throw the exception of that type directly.
				//.getOrElseThrow(throwable -> new RuntimeException(throwable));
		
//		final String response;
//		try {
//			response = makeOutBoundCall(accountNumber, false, true);
//		} catch(WebApplicationException e) {
//			throw e;
//		} catch(NullPointerException e) {
//			throw e;
//		}
//		return response;
	}

	private String makeOutBoundCall(String accountNumber, boolean throwWAP, boolean throwNPE,
									boolean throwInterruptedEx) throws Exception {
		if (throwWAP) {
			throw new WebApplicationException();
		} else if (throwNPE) {
			throw new NullPointerException();
		} else if(throwInterruptedEx) {
			throw new InterruptedException();
		}
		return "response";
	}

}
