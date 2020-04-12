package com.justamonad.tutorials.optional;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

public class OptionalStreamTest {

	@Test
	public void test() {
		Arrays.asList(1, 2, 3, 4, 5).stream().map(val -> findByID(val));// Stream<Optional<Integer>>
	}

	private Optional<Integer> findByID(Integer number) {
		return Optional.of(number);
	}

}
