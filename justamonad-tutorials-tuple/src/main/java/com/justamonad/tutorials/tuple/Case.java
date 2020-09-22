package com.justamonad.tutorials.tuple;

import java.util.List;
import java.util.function.Supplier;

public class Case<R> {

	private final List<Tuple2<Supplier<Boolean>, Supplier<R>>> cases;

	private Case(List<Tuple2<Supplier<Boolean>, Supplier<R>>> cases) {
		this.cases = cases;
	}

	public static <R> Case<R> of(List<Tuple2<Supplier<Boolean>, Supplier<R>>> cases) {
		return new Case<>(List.copyOf(cases));
	}

	public void match() {
		for (Tuple2<Supplier<Boolean>, Supplier<R>> tuple2 : cases) {
			if (tuple2.t1.get()) {
				throw new RequestValidationException(tuple2.t2.get().toString());
			}
		}
	}

}
