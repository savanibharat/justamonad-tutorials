package com.justamonad.tutorials.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class CodeAsBehaviorTest {

	@Test
	public void test() {
		evenNumbers(Arrays.asList(1, 2, 3, 4, 5));

	}

	List<Integer> evenNumbers(List<Integer> numbers) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer value : numbers) {
			if (value.intValue() % 2 == 0) {
				result.add(value);
			}
		}
		return Collections.unmodifiableList(result);
	}

	List<Integer> positiveEvenNumbers(List<Integer> numbers) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer value : numbers) {
			if (value.intValue() > 0 && value.intValue() % 2 == 0) {
				result.add(value);
			}
		}
		return Collections.unmodifiableList(result);
	}

	List<Integer> filterNumbers(
			List<Integer> numbers, 
			Predicate<Integer> predicate) {
		
		List<Integer> result = new ArrayList<Integer>();
		for (Integer value : numbers) {
			if (predicate.test(value)) {
				result.add(value);
			}
		}
		return Collections.unmodifiableList(result);
	}

}
