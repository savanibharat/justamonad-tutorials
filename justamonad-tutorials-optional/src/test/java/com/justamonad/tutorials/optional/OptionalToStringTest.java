package com.justamonad.tutorials.optional;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalToStringTest {

	@Test
	public void testEmptyOptionalHashCode() {
		Optional<String> optString = Optional.empty();
		Assert.assertEquals("Optional.empty", optString.toString());
	}

	@Test
	public void testValuePresentOptionalHashCode() {
		Optional<String> optString = Optional.of("abc");
		Assert.assertEquals("Optional[abc]", optString.toString());
	}

}
