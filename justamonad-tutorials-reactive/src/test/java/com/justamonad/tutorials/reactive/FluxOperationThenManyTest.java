package com.justamonad.tutorials.reactive;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxOperationThenManyTest {

	@Test
	public void thenManyTest() {

		Flux<Integer> integers = Flux.just(1, 2, 3);
		Flux<String> strings = Flux.just("a", "b", "c");

		Flux<String> thenMany = integers.thenMany(strings);

		StepVerifier.create(thenMany).expectNext("a", "b", "c").verifyComplete();

	}
	
}
