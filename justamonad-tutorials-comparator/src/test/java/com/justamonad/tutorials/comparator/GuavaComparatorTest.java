package com.justamonad.tutorials.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Comparators;

public class GuavaComparatorTest {

	@Test
	public void isInOrderTrueUsingIntegerCompare() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		boolean inOrder = Comparators.isInOrder(numbers, Integer::compare);
		Assert.assertTrue(inOrder);
	}

	@Test
	public void isInOrderComparatorTrue() {
		List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 4);
		boolean inOrder = Comparators.isInOrder(numbers, Comparator.naturalOrder());
		Assert.assertTrue(inOrder);
	}

	@Test
	public void isInOrderFalse() {
		List<Integer> numbers = Arrays.asList(1, 2, 4, -1);
		boolean inOrder = Comparators.isInOrder(numbers, Integer::compare);
		Assert.assertFalse(inOrder);
	}

	@Test
	public void isInOrderReverseTrue() {
		List<Integer> numbers = Arrays.asList(4, 3, 2, 1);
		boolean inOrder = Comparators.isInOrder(numbers, Comparator.reverseOrder());
		Assert.assertTrue(inOrder);
	}

	@Test
	public void isInStrictOrderComparatorTrue1() {
		List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 4);
		boolean isInStrictOrder = Comparators.isInStrictOrder(numbers, Comparator.naturalOrder());
		Assert.assertFalse(isInStrictOrder);
	}

	@Test
	public void isInStrictOrderComparatorTrue2() {
		List<Integer> numbers = Arrays.asList(1, 12, 15, 19);
		boolean isInStrictOrder = Comparators.isInStrictOrder(numbers, Comparator.naturalOrder());
		Assert.assertTrue(isInStrictOrder);
	}

	@Test
	public void lexical() {
		Comparator<Integer> comp = Integer::compare;
		Comparator<Iterable<Integer>> lexical = Comparators.lexicographical(comp);
//		[] < [1] < [1, 1] < [1, 2] < [2]
		List<List<Integer>> numbers1 = Arrays.asList(new ArrayList<>(), Arrays.asList(1), Arrays.asList(1, 1),
				Arrays.asList(1, 2), Arrays.asList(2));

		numbers1.sort(lexical);
		System.out.println(numbers1);
	}

	@Test
	public void lexicalReversed() {
		Comparator<Integer> comp = Integer::compare;
		Comparator<Iterable<Integer>> lexicalReversed = Comparators.lexicographical(comp).reversed();
//		[[2], [1, 2], [1, 1], [1], []]
		List<List<Integer>> numbers1 = Arrays.asList(new ArrayList<>(), Arrays.asList(1), Arrays.asList(1, 1),
				Arrays.asList(1, 2), Arrays.asList(2));
		numbers1.sort(lexicalReversed);
		System.out.println(numbers1);
	}

	@Test
	public void least() {
		Collector<Integer, ?, List<Integer>> least = Comparators.least(2, Integer::compare);
		List<Integer> numbers = Arrays.asList(5, 67, 9, 23, 6, 7);
		List<Integer> collect = numbers.stream().collect(least);
		System.out.println(collect);
	}

	@Test
	public void greatest() {
		Collector<Integer, ?, List<Integer>> least = Comparators.greatest(2, Integer::compare);
		List<Integer> numbers = Arrays.asList(5, 67, 9, 23, 6, 7);
		List<Integer> collect = numbers.stream().collect(least);
		System.out.println(collect);
	}

	@Test
	public void emptiesFirst() {
		List<Optional<Integer>> numbers = Arrays.asList(Optional.of(5), Optional.of(67), Optional.of(9),
				Optional.empty(), Optional.of(23), Optional.of(6), Optional.empty(), Optional.of(7), Optional.empty());
		Comparator<Optional<Integer>> emptiesFirst = Comparators.emptiesFirst(Integer::compare);
		numbers.sort(emptiesFirst);
		System.out.println(numbers);
	}

	@Test
	public void emptiesLast() {
		List<Optional<Integer>> numbers = Arrays.asList(Optional.of(5), Optional.of(67), Optional.of(9),
				Optional.empty(), Optional.of(23), Optional.of(6), Optional.empty(), Optional.of(7), Optional.empty());
		Comparator<Optional<Integer>> emptiesFirst = Comparators.emptiesFirst(Integer::compare);
		numbers.sort(emptiesFirst);
		System.out.println(numbers);
	}

}
