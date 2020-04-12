package com.justamonad.tutorials.optional;

import java.util.Optional;

import org.junit.Test;

public class OptionalFlatMapTest {

	@Test
	public void testFlatMap() {

		Optional<String> optString = Optional.ofNullable(null);//("input");
//		Optional<String> optMap = optString.map(OptionalFlatMapTest::getOutput);
		Optional<Optional<String>> optOptString = optString.map(OptionalFlatMapTest::getOutputOpt);
		System.out.println(optOptString);
		
		Optional<String> flatMapString = optString.flatMap(OptionalFlatMapTest::getOutputOpt);
		System.out.println(flatMapString);
	}

	static String getOutput(String input) {
		return input == null ? null : input;
	}

	static Optional<String> getOutputOpt(String input) {
		return input == null ? Optional.empty() : Optional.of(input);
	}

}
