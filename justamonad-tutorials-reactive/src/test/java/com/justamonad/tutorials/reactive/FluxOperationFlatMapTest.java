package com.justamonad.tutorials.reactive;

import java.time.Duration;

import org.junit.Test;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxOperationFlatMapTest {

	/**
	 * <pre>
	 * Two operators, flatMap and concatMap, work pretty much the same. They both
	 * merge items emitted by inner streams into the outer stream. The difference
	 * between flatMap and concatMap is that the order in which the items arrive is
	 * different. flatMap interleaves items from the inner streams; the order may be
	 * different.
	 * </pre>
	 * 
	 * <pre>
	 * Suppose you had an outer stream with values 1, 2, and 3. Let’s
	 * suppose you needed to send those values to some network service that returns
	 * a Flux<String>. You could flatMap over the outer stream, launching network
	 * calls as you go. Some network calls might take 10 ms, others 100ms. You don’t
	 * know. And in this case, the order doesn’t matter. So we might see the results
	 * from 2 emitted before the result for 1.
	 * </pre>
	 * 
	 * <pre>
	 * Here’s a simple example that
	 * artificially delays each inner stream. So the first item is the most delayed,
	 * the second item is less delayed, and the third item is least delayed. The
	 * result is that the items in the outer stream emit backward, 3, 2, 1.
	 * Whichever items from the inner stream finish publishing data, then merge into
	 * the outer stream. As the data in the inner stream finishes emitting, it
	 * merges into the outer stream.
	 * </pre>
	 * 
	 * <b> Use flatMap only if there is no dependencies between elements of Flux.</b>
	 * <b> If there is dependeny use concatMap </b>
	 */
	@Test
	public void flatMap() {

		Flux<Integer> data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
				.flatMap(id -> delayReplayFor(id.id, id.delay));

		StepVerifier.create(data).expectNext(3, 2, 1).verifyComplete();

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
