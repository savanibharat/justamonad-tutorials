package com.justamonad.tutorials.reactive;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxOperationMapTest {

	@Test
	public void mapTest() {

		Flux<String> strings = Flux.just("a", "b", "c").map(String::toUpperCase);

		StepVerifier.create(strings).expectNext("A", "B", "C").verifyComplete();

		Flux.just("a", "b", "c").map(str -> {
			System.out.println(str);
			return str.toUpperCase();
		}).subscribe();

	}

}
