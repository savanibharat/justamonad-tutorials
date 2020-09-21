package com.justamonad.tutorials.reactive;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxOperationSwitchMapTest {

	/**
	 * <pre>
	 * Both flatMap and concatMap eventually process every inner stream so long as
	 * they all finally complete.
	 * </pre>
	 * 
	 * <pre>
	 * switchMap is different; it cancels any outstanding
	 * inner publishers as soon as a new value arrives.
	 * </pre>
	 * 
	 * <pre>
	 * Imagine a network service
	 * offering predictions based on input characters - the quintessential lookahead
	 * service.
	 * </pre>
	 * 
	 * <pre>
	 * You type "re" in a textbox, triggering a network request, and
	 * predictions for possible completions return. You type "rea" and trigger
	 * another network request.
	 * </pre>
	 * 
	 * <pre>
	 * You type "reac" and trigger yet another request.
	 * </pre>
	 * 
	 * <pre>
	 * You might type faster than the network service can provide predications, which
	 * means you might type "react" before the predictions for "reac" are available.
	 * Use switchMap to cancel the previous as-yet incomplete network calls,
	 * preserving only the latest outstanding network call for "react" and,
	 * eventually, "reactive." In the example, characters are typed faster than the
	 * network service call delivering predictions, so there’s continuously an
	 * outstanding request.
	 * </pre>
	 * 
	 * <pre>
	 * In this example, we use delayElements(long) to
	 * artificially delay the publication of elements in the streams. So, the outer
	 * stream (the words typed) emits new values every 100 ms. The inner stram (the
	 * network call) emits values every 500 ms. The outer stream only ever sees the
	 * final results for the last word, "reactive."
	 * </pre>
	 */
	@Test
	public void switchMapWithLookaheads() {

		Flux<String> source = Flux.just("re", "rea", "reac", "react", "reactive").delayElements(Duration.ofMillis(100))
				.switchMap(this::lookUp);
		
		StepVerifier.create(source).expectNext("reactive -> reactive").verifyComplete();

	}

	private Flux<String> lookUp(String word) {
		return Flux.just(word + " -> reactive").delayElements(Duration.ofMillis(500));
	}

}
