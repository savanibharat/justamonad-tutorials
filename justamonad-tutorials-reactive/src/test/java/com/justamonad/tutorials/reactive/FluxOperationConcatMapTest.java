package com.justamonad.tutorials.reactive;

import java.time.Duration;

import org.junit.Test;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxOperationConcatMapTest {

	/**
	 * <pre>
	 * The concatMap operator, on the other hand, preserves the order of items. The
	 * main disadvantage of concatMap is that it has to wait for each Publisher<T>
	 * to complete its work. You lose asynchronicity on the emitted items. It does
	 * its work one-by-one, so you can guarantee the ordering of the results.
	 * </pre>
	 * 
	 * <pre>
	 * The following events, in the following order, mutate the state in a customer
	 * record: "read," "update," "read," "delete," and "read." These commands should
	 * be processed in the same order; you don’t want those updates processed in
	 * parallel. Use concatMap to ensure that ordering.
	 * </pre>
	 */
	@Test
	public void concatMap() {

		Flux<Integer> data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
				.concatMap(id -> delayReplayFor(id.id, id.delay));

		StepVerifier.create(data).expectNext(1, 2, 3).verifyComplete();

	}

	private Flux<Integer> delayReplayFor(int id, int delay) {
		return Flux.just(id).delayElements(Duration.ofMillis(delay));
	}

	@AllArgsConstructor
	static class Pair {
		private int id;
		private int delay;
	}

}
