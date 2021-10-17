package com.justamonad.tutorials.vavr.eitherdemo;

import javax.ws.rs.WebApplicationException;

//import io.vavr.Predicates;
import io.vavr.control.Either;
//import static io.vavr.API.$;
//import static io.vavr.API.Case;
//import static io.vavr.API.Match;
//import static io.vavr.Predicates.instanceOf;
//import static io.vavr.Predicates.is;

//import static io.vavr.Patterns.$Right;
//import static io.vavr.Patterns.$Left;
//Match(either).of(
//Case($Right($()), ex -> new RuntimeException(ex)),
//Case($Right($()), ex -> new RuntimeException(ex)),
//Case($(), either.get()));

public final class EitherDemo {

	public static void main(String[] args) throws Throwable {
		EitherDemo t = new EitherDemo();
		try {
			t.userLoad("wae");
		} catch (NullPointerException n) {
			System.out.println("NPE");
		} catch (WebApplicationException w) {
			System.out.println("WAE");
		} catch (RuntimeException r) {
			System.out.println("RTE");
		}
	}

	/**
	 * <ol>
	 * <li>Make outbound call</li>
	 * <li>Service Exception : catch WebApplicationException and throw it</li>
	 * <li>Generic Exception : catch Exception and throw it</li>
	 * </ol>
	 */
	public String userLoad(String accountNumber) {
		return makeOutBoundCall(accountNumber).getOrElseThrow(ex -> new RuntimeException(ex));
	}

	private Either<Throwable, String> makeOutBoundCall(String accountNumber) {
		if (accountNumber.equalsIgnoreCase("wae")) {
			return Either.left(new WebApplicationException());
		} else if (accountNumber.equalsIgnoreCase("npe")) {
			return Either.left(new NullPointerException());
		}
		return Either.right("response");
	}

}
