package com.justamonad.tutorials.optional;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class OptionalMapTest {

	private final Logger LOGGER = Logger.getGlobal();

	// map method in Optional provides a way to transform value in Optional from
	// one type to another. The transformation can be of same type too.
	
	// This method must be used as post-processing on result values of Optional.
	// Example
	// getFileName() // returns Optional<String>
	// 	.map(name -> new FileInputStream(name));
	
	// In our example we get the result and 
	@Test
	public void testMap() {

		String result = "   abc  ";
		if (result != null && result.contains("abc")) {
			// do something with result
			LOGGER.log(Level.INFO, () -> result.trim());
		}

		Optional<String> optString = Optional.of(result + "  ");

		optString
		.filter(res -> res.contains("abc"))
		.map(String::trim)
		.ifPresent(res -> LOGGER.log(Level.INFO, () -> res));
	}

}
