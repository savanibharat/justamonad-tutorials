package com.justamonad.tutorials.reactive;

import java.net.URL;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxFlatMapOutboundCall {

	@Test
	public void httpCallTest() throws Throwable {
		Set<Long> times = new TreeSet<>();
		for (int i = 0; i < 5; i++) {

			URLConnectionReader pypl = new URLConnectionReader(new URL("https://www.paypal.com"));
			URLConnectionReader aapl = new URLConnectionReader(new URL("https://www.apple.com"));
			URLConnectionReader goog = new URLConnectionReader(new URL("https://www.google.com"));
			URLConnectionReader nflx = new URLConnectionReader(new URL("https://www.netflix.com"));
			long start = System.currentTimeMillis();
//			Flux<URLConnectionReader> fluxTasks = Flux.just(pypl, aapl, goog, nflx).map(task -> {
//				task.read();
//				return task;
//			});

//			Flux<URLConnectionReader> fluxTasks = Flux.just(pypl, aapl, goog, nflx).map(task -> {
//				task.read();
//				return task;
//			}).subscribeOn(Schedulers.elastic());

			Flux<URLConnectionReader> fluxTasks = Flux.just(pypl, aapl, goog, nflx)
					.flatMap(task -> Mono.just(task).subscribeOn(Schedulers.elastic()).map(execute()));

			Flux.concat(fluxTasks.map(Mono::just).toIterable()).subscribe();
			long end = System.currentTimeMillis();
			times.add(end - start);
		}
		System.out.println(times);
	}

	private Function<? super URLConnectionReader, ? extends URLConnectionReader> execute() {
		return t -> {
			t.read();
			return t;
		};
	}

}
