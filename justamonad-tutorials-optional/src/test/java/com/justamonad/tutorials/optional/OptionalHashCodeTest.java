package com.justamonad.tutorials.optional;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalHashCodeTest {

	@Test
	public void testEmptyOptionalHashCode() {
		Optional<String> optString = Optional.empty();
		Assert.assertEquals(0, optString.hashCode());
	}

	@Test
	public void testValuePresentOptionalHashCode() {
		int hashCode = Optional.of("abc").hashCode();
		Assert.assertTrue(hashCode != 0);
	}

}
