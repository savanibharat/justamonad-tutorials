package com.justamonad.tutorials.tuple;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Tuple2<T1, T2> {

	public final T1 _1;
	public final T2 _2;

	private Tuple2(T1 _1, T2 _2) {
		this._1 = _1;
		this._2 = _2;
	}

	public static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
		Objects.requireNonNull(_1);
		Objects.requireNonNull(_2);
		return new Tuple2<T1, T2>(_1, _2);
	}

	public int arity() {
		return 2;
	}

	public <R> R apply(BiFunction<T1, T2, R> biFunction) {
		Objects.requireNonNull(biFunction);
		return biFunction.apply(_1, _2);
	}

	public <R> Tuple2<R, T2> apply_1(Function<T1, R> function) {
		Objects.requireNonNull(function);
		return Tuple2.of(function.apply(_1), _2);
	}
	
	public <R> Tuple2<T1, R> apply_2(Function<T2, R> function) {
		Objects.requireNonNull(function);
		return Tuple2.of(_1, function.apply(_2));
	}

	public <R1, R2> Tuple2<R1, R2> applyBoth(BiFunction<T1, T2, Tuple2<R1, R2>> biFunction) {
		Objects.requireNonNull(biFunction);
		return biFunction.apply(_1, _2);
	}

	public <R1, R2> Tuple2<R1, R2> apply(Function<T1, R1> function1, Function<T2, R2> function2) {
		Objects.requireNonNull(function1);
		Objects.requireNonNull(function2);
		return Tuple2.of(function1.apply(_1), function2.apply(_2));
	}

}