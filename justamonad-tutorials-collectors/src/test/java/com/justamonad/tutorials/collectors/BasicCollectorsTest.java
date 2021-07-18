package com.justamonad.tutorials.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;

public class BasicCollectorsTest {

	@Test
	public void test() {

		List<Transaction> dataSet = Transactions.getDataSet();
		List<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toList());

		Assert.assertFalse(result.isEmpty());

	}

	@Test
	public void testIntegerList() {

		List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(new ArrayListCollectorImpl<Integer>());

		Assert.assertFalse(result.isEmpty());

	}

	/**
	 * Try making the collector unordered and concurrent, it will fail.
	 * */
	@Test
	public void testIntegerListUsingCollectors() {
		List<Integer> result = IntStream.range(1, 10_000).boxed().parallel()
				.collect(new ArrayListCollectorImpl<Integer>());

		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void collectorOfWithoutFinisher() {

		Supplier<List<Integer>> supplier = ArrayList::new;

		BiConsumer<List<Integer>, Integer> accumulator = (list, val) -> list.add(val);

		BinaryOperator<List<Integer>> combiner = (left, right) -> {
			left.addAll(right);
			return left;

		};

		Characteristics characteristics = Characteristics.IDENTITY_FINISH;

		Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(supplier, accumulator, combiner,
				characteristics);

		List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(toList);

		Assert.assertFalse(result.isEmpty());

	}

	@Test
	public void collectorOfWithFinisher() {

		Supplier<List<Integer>> supplier = ArrayList::new;

		BiConsumer<List<Integer>, Integer> accumulator = (list, val) -> list.add(val);

		BinaryOperator<List<Integer>> combiner = (left, right) -> {
			left.addAll(right);
			return left;

		};

		Function<List<Integer>, List<Integer>> finisher = Function.identity();

		Characteristics characteristics = Characteristics.IDENTITY_FINISH;

		Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(supplier, accumulator, combiner,
				finisher, characteristics);

		List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(toList);

		Assert.assertFalse(result.isEmpty());

	}

}
