package com.justamonad.tutorials.optional;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalEqualsTest {

	@Test
	public void testEmptyOptionals() {
		Optional<String> optString = Optional.empty();
		Assert.assertTrue(optString.equals(Optional.empty()));
	}

	@Test
	public void testPresentOptionals() {
		Optional<String> optString = Optional.of("abc");
		Assert.assertTrue(optString.equals(Optional.of("abc")));
	}

}
