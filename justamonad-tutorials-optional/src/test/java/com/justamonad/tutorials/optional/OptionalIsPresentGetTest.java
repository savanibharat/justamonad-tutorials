package com.justamonad.tutorials.optional;

import java.util.Optional;

import org.junit.Test;

public class OptionalIsPresentGetTest {

	/**
	 * Optional is great way to make sure whether result is present or not.
	 * 
	 * Apart from other methods I previously discussed, this article, primarily
	 * discusses one of the anti-patterns of Optional.
	 * 
	 * There are several ways to extract value, if present, from an Optional.
	 * But using below two methods to extract value is not a good way and lead
	 * to code pollution.
	 * 
	 * Optional class provides a get() method which returns the value, if
	 * present. If value is not present then get() method throws
	 * NullPointerException.
	 * 
	 * So in order to use get() we need to prove that value exists in Optional.
	 * Unfortunately, this leads to testability using isPresent().
	 * 
	 * How do generally avoid NPE? By checking if we are trying to deference a null pointer.
	 * This is how we do it.
	    String result = "result";
		if (result != null) {
			// do something with result
		}
	 * 
	 * Using Optional we can achieve essentially same thing using isPresent() and get() method.
	 *  Optional<String> optResult = Optional.of("result");
		if (optResult.isPresent()) {
			String res = optResult.get();
			// do something with result
			System.out.println(res);
		}
	 * 
	 * Let us work through an example of null testability and Optional code.
	 * 
	 * Null comparison makes an explicit check for nulls. We have done this time
	 * and again. We know why we write this code, what is the intent of this
	 * code.
	 *
	 * If using Optional, we are using two method calls essentially to achieve the
	 * same result as of Null comparison.
	 * 
	 */
	@Test
	public void testFilter() {

		String result = "result";
		if (result != null) {
			// do something with result
		}

		Optional<String> optResult = Optional.of("result");
		if (optResult.isPresent()) {
			String res = optResult.get();
			// do something with result
			System.out.println(res);
		}

	}

}
