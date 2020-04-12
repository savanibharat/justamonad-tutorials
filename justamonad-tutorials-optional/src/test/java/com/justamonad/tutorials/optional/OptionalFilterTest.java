package com.justamonad.tutorials.optional;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class OptionalFilterTest {

	Logger logger = Logger.getGlobal();

	// Optional is great way to avoid NullPointerException but the usage of
	// Optional should not not be limited to that.
	//
	// It often happens that once the result is returned it is consumed only if
	// it matches the expectations. This expectations can depend on use-case.
	// Let's take for instance a String result is returned and should be
	// consumed only if it contains certain CharSequence.
	//
	// In our example we get a result string. We than check if result is non
	// null and use contains method to check for our CharSequence. If it does we
	// log the result else we don't do anything with it.

	// Now let us explore filter method of Optional class. If the value is
	// present matches the specified Predicate then filter
	// method just returns this Optional else it returns empty Optional.
	// filter method will throw NullPointerException if specified Predicate is null.
	@Test
	public void testFilter() {

		String result = "abc";
		if (result != null && result.contains("abc")) {
			// do something with result
			logger.log(Level.INFO, () -> result);
		}

		Optional<String> optString = Optional.of(result);

		optString
		.filter(res -> res.contains("abc"))
		.ifPresent(res -> logger.log(Level.INFO, () -> res));

	}

}
