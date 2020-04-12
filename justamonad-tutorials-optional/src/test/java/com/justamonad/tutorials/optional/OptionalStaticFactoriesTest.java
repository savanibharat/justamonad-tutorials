package com.justamonad.tutorials.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalStaticFactoriesTest {

	/**
	 * empty() method creates an Optional object for which the value is absent.
	 * This is extremely important method as it denotes that the operation
	 * performed doesn't return a value or a value is absent for the operation
	 * that was performed.
	 */
	@Test(expected = NoSuchElementException.class)
	public void emptyTest() {
		Optional<String> optStr = Optional.empty();
		Assert.assertFalse(optStr.isPresent());
		optStr.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * of method is a static factory method defined in Optional class which
	 * creates instance of Optional if and only if the value passed is not null.
	 * The method throws NullPointerException if the value passed is null.
	 * 
	 * This method must be used only if you can guarantee that the value will
	 * not be null.
	 * 
	 */
	@Test
	public void ofTest() {
		Optional<String> optStr = Optional.of("value");
		optStr.ifPresent(System.out::print);
	}

	/**
	 * ofNullable method creates an Optional object by combining the power of
	 * empty() and of() method.
	 * 
	 * If the value passed is null then empty() Optional is returned else a new
	 * Optional object is created using Optional.of(value).
	 * 
	 * It might be tempting to ditch empty() and of() methods in favor of
	 * ofNullable() but this can be a trap as this the code-base would become
	 * hard to understand.
	 * 
	 * Remember while ofNullable() combines the functionality of empty() and
	 * of() methods it doesn't clearly state the intent of Optional object. The
	 * intent of empty() and of() methods is clear while intent of ofNullable is
	 * not that clear.
	 */
	@Test
	public void ofNullableTest() {
		Optional.ofNullable("value");
		Optional.ofNullable(null);
	}

}
