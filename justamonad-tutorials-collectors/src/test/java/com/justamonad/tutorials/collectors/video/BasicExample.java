package com.justamonad.tutorials.collectors.video;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class BasicExample {

	public void test() {

		List<Integer> evenNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
			.stream()
			.filter(val -> val % 2 == 0)
			//.collect(Collectors.toUnmodifiableList());
			.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		
		System.out.println(evenNumbers);
		
	}
	
	public static void main(String[] args) {
		
		BasicExample basicExample = new BasicExample();
		basicExample.test();
		
	}

}
