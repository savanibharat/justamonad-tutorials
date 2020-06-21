package com.justamonad.tutorials.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DefaultMethodsTest {

	public void defaultMethodSort() {

//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		numbers.sort(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer val1, Integer val2) {
//				return Integer.compare(val1, val2);
//			}
//		});
		
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		Comparator<Integer> comparator = (val1, val2) -> Integer.compare(val1, val2);
		numbers.sort(comparator.reversed());
		Comparator.reverseOrder();

	}

}
