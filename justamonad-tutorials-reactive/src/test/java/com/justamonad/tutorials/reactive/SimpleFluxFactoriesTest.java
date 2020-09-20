package com.justamonad.tutorials.reactive;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SimpleFluxFactoriesTest {

	@Test
	public void simple() {

		// Check total range
		Publisher<Integer> range = Flux.range(0, 10);
		StepVerifier.create(range).expectNextCount(10).verifyComplete();

		// Check sequence of elements. Elements must be in order.
		Flux<String> letters = Flux.just("A", "B", "C");
		StepVerifier.create(letters).expectNext("A", "B", "C").verifyComplete();

		// Check now(execution time) and later-now(completed execution).
		long now = System.currentTimeMillis();
		Mono<Date> greetingMono = Mono.just(new Date(now));
		StepVerifier.create(greetingMono).expectNext(new Date(now)).verifyComplete();

		// Check empty
		Mono<Object> empty = Mono.empty();
		StepVerifier.create(empty).verifyComplete();

		// Check array elements
		Flux<Integer> fromArray = Flux.fromArray(new Integer[] { 1, 2, 3 });
		StepVerifier.create(fromArray).expectNext(1, 2, 3).verifyComplete();
		StepVerifier.create(fromArray).expectNext(1).expectNext(2).expectNext(3).verifyComplete();

		// Check List elements
		Flux<Integer> fromIterable = Flux.fromIterable(Arrays.asList(1, 2, 3));
		StepVerifier.create(fromIterable).expectNext(1, 2, 3).verifyComplete();
		StepVerifier.create(fromIterable).expectNext(1).expectNext(2).expectNext(3).verifyComplete();

		// Flux using java.util.stream.Stream
		AtomicInteger integer = new AtomicInteger();
		Supplier<Integer> supplier = integer::incrementAndGet;
		Flux<Integer> integerFlux = Flux.fromStream(Stream.generate(supplier));
		StepVerifier.create(integerFlux.take(3)).expectNext(1).expectNext(2).expectNext(3).verifyComplete();

	}
}
