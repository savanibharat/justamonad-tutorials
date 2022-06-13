//package com.justamonad.tutorials.comparator.guava;
//
//import static java.util.Comparator.naturalOrder;
//import static java.util.Comparator.reverseOrder;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collector;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.google.common.collect.Comparators;
//
//public class GuavaComparatorsTest {
//
//	@Test
//	public void isInOrderTrueUsingIntegerCompare() {
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//		boolean inOrder = Comparators.isInOrder(numbers, Integer::compare);
//		Assert.assertTrue(inOrder);
//	}
//
//	@Test
//	public void isInOrderComparatorTrue() {
//		List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 4);
//		boolean inOrder = Comparators.isInOrder(numbers, Comparator.naturalOrder());
//		Assert.assertTrue(inOrder);
//	}
//
//	@Test
//	public void isInOrderFalse() {
//		List<Integer> numbers = Arrays.asList(1, 2, 4, -1);
//		boolean inOrder = Comparators.isInOrder(numbers, Integer::compare);
//		Assert.assertFalse(inOrder);
//	}
//
//	@Test
//	public void isInOrderReverseTrue() {
//		List<Integer> numbers = Arrays.asList(4, 3, 3, 2, 1);
//		boolean inOrder = Comparators.isInOrder(numbers, Comparator.reverseOrder());
//		Assert.assertTrue(inOrder);
//	}
//
//	@Test
//	public void isInOrdernatualReverseTrue() {
//		List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 1);
//		boolean reverseOrder = Comparators.isInOrder(numbers, reverseOrder());
//		boolean naturalOrder = Comparators.isInOrder(numbers, naturalOrder());
//		Assert.assertTrue(reverseOrder == naturalOrder);
//	}
//
//	@Test
//	public void isInStrictOrderComparatorTrue1() {
//		List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 4);
//		boolean isInStrictOrder = Comparators.isInStrictOrder(numbers, naturalOrder());
//		Assert.assertFalse(isInStrictOrder);
//	}
//
//	@Test
//	public void isInStrictOrderComparatorTrue2() {
//		List<Integer> numbers = Arrays.asList(1, 12, 15, 19);
//		boolean isInStrictOrder = Comparators.isInStrictOrder(numbers, naturalOrder());
//		Assert.assertTrue(isInStrictOrder);
//	}
//
//	@Test
//	public void lexical() {
//		Comparator<Iterable<Integer>> lexical = Comparators.lexicographical(Integer::compare);
//		List<List<Integer>> numbers = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2), Arrays.asList(1),
//				Arrays.asList(1, 1), new ArrayList<>());
//		System.out.println(numbers);
//		numbers.sort(lexical);
//		System.out.println(numbers);
//	}
//
//	@Test
//	public void lexicalReversed() {
//		Comparator<Iterable<Integer>> lexical = Comparators.lexicographical(Integer::compare).reversed();
//		List<List<Integer>> numbers1 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2), Arrays.asList(1),
//				Arrays.asList(1, 1), new ArrayList<>());
//		System.out.println(numbers1);
//		numbers1.sort(lexical);
//		System.out.println(numbers1);
//	}
//
//	@Test
//	public void least() {
//		Collector<Integer, ?, List<Integer>> leastCollector = Comparators.least(2, Integer::compare);
//		List<Integer> numbers = Arrays.asList(5, 67, 9, 23, 6, 7);
//		System.out.println(numbers);
//		List<Integer> collect = numbers.stream().collect(leastCollector);
//		System.out.println(collect);
//	}
//
//	@Test
//	public void leastComparingInt() {
//		Collector<String, ?, List<String>> leastCollector = Comparators.least(3,
//				Comparator.comparingInt(String::length));
//		List<String> words = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog");
//		System.out.println(words);
//		List<String> collect = words.stream().collect(leastCollector);
//		System.out.println(collect);
//	}
//
//	@Test
//	public void greatest() {
//		Collector<Integer, ?, List<Integer>> greatestCollector = Comparators.greatest(2, Integer::compare);
//		List<Integer> numbers = Arrays.asList(5, 67, 9, 23, 6, 7);
//		System.out.println(numbers);
//		List<Integer> collect = numbers.stream().collect(greatestCollector);
//		System.out.println(collect);
//	}
//
//	@Test
//	public void greatestComparingInt() {
//		Collector<String, ?, List<String>> greatestCollector = Comparators.greatest(3,
//				Comparator.comparingInt(String::length));
//		List<String> words = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog");
//		System.out.println(words);
//		List<String> collect = words.stream().collect(greatestCollector);
//		System.out.println(collect);
//	}
//
//	@Test
//	public void emptiesFirst() {
//		List<Optional<Integer>> numbers = Arrays.asList(Optional.of(5), Optional.of(67), Optional.of(9),
//				Optional.empty(), Optional.of(23), Optional.of(6), Optional.empty(), Optional.of(7), Optional.empty());
//		System.out.println(numbers);
//		Comparator<Optional<Integer>> emptiesFirst = Comparators.emptiesFirst(Integer::compare);
//		numbers.sort(emptiesFirst);
//		System.out.println(numbers);
//	}
//
//	@Test
//	public void emptiesFirstReversed() {
//		List<Optional<Integer>> numbers = Arrays.asList(Optional.of(5), Optional.of(67), Optional.of(9),
//				Optional.empty(), Optional.of(23), Optional.of(6), Optional.empty(), Optional.of(7), Optional.empty());
//		System.out.println(numbers);
//		Comparator<Optional<Integer>> emptiesFirst = Comparators.emptiesFirst(Integer::compare).reversed();
//		numbers.sort(emptiesFirst);
//		System.out.println(numbers);
//	}
//
//	@Test
//	public void emptiesLast() {
//		List<Optional<Integer>> numbers = Arrays.asList(Optional.of(5), Optional.of(67), Optional.of(9),
//				Optional.empty(), Optional.of(23), Optional.of(6), Optional.empty(), Optional.of(7), Optional.empty());
//		Comparator<Optional<Integer>> emptiesFirst = Comparators.emptiesLast(Integer::compare);
//		numbers.sort(emptiesFirst);
//		System.out.println(numbers);
//	}
//
//	@Test
//	public void emptiesLastString() {
//		List<Optional<String>> names = Arrays.asList(Optional.of("Jon"), Optional.of("Arya"), Optional.of("Sansa"),
//				Optional.empty(), Optional.of("Robb"), Optional.of("Bran"), Optional.empty(), Optional.of("Rickon"),
//				Optional.empty());
//		Comparator<Optional<String>> emptiesLast = Comparators.emptiesLast(String::compareTo);
//		System.out.println(names);
//		names.sort(emptiesLast);
//		System.out.println(names);
//	}
//
//}
