package com.justamonad.tutorials.collectors;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Collector<T, A, R> i.e. Collector<Integer, List<Integer>, List<Integer>>
 * <ol>
 * <li>T = Integer</li>
 * <li>A = List<Integer></li>
 * <li>R = List<Integer></li>
 */
public class ArrayListCollectorImpl<T> implements Collector<T, List<T>, List<T>> {

	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (left, right) -> {
			left.addAll(right);
			return left;
		};
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.of(Characteristics.IDENTITY_FINISH);
	}
}
