package com.justamonad.tutorials.collectors.video;

import static java.util.function.Function.identity;

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
 * T - Integer ,A - List<Integer>, R - List<Integer>
 */
public final class ArrayListCollector implements Collector<Integer, List<Integer>, List<Integer>> {

	@Override
	public Supplier<List<Integer>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<Integer>, Integer> accumulator() {
		return (list, elem) -> list.add(elem);
	}

	@Override
	public BinaryOperator<List<Integer>> combiner() {
		return (left, right) -> {
			left.addAll(right);
			return left;
		};
	}

	@Override
	public Function<List<Integer>, List<Integer>> finisher() {
		return identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.of(Characteristics.IDENTITY_FINISH);
	}

}
