package com.justamonad.tutorials.common;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

public class ObjectsTest {

	@Test
	public void equalsTest() {

		Assert.assertTrue(Objects.equals("Jon", "Jon"));
		
		Assert.assertFalse(Objects.equals("Jon", "Snow"));
		
		Assert.assertTrue(Objects.equals(null, null));
		
		Assert.assertFalse(Objects.equals(null, ""));

	}

}
