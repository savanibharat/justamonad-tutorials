package com.justamonad.tutorials.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

class ListInterfaceAdditions {

	@Test
	void testReplaceAll() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.replaceAll(val -> val * 10);
		List<Integer> output = Arrays.asList(10, 20, 30, 40, 50, 60);
		Assert.assertEquals(output, numbers);
	}

	@Test
	void testReplaceAllStrings() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		names.replaceAll(String::toUpperCase);
		List<String> output = Arrays.asList("JON", "SANSA", "RICKON", "ARYA", "ROBB");
		Assert.assertEquals(output, names);
	}

	@Test
	void testReplaceAllPreJava8() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		for (ListIterator<Integer> iter = numbers.listIterator(); iter.hasNext();) {
			iter.set(iter.next() * 10);
		}
		List<Integer> output = Arrays.asList(10, 20, 30, 40, 50, 60);
		Assert.assertEquals(output, numbers);
	}

	@Test
	void testSort() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		names.sort(null);
		List<String> output = Arrays.asList("Arya", "Jon", "Rickon", "Robb", "Sansa");
		Assert.assertEquals(output, names);
	}

	@Test
	void testSortTransactions() {
		Assertions.assertThrows(ClassCastException.class, () -> {
			List<Transaction> names = new ArrayList<>();
			names.addAll(Transactions.getDataSet());
			names.sort(null);
		});
	}

	@Test
	void testSortTransactionsUsingDate() {
		List<Transaction> names = new ArrayList<>();
		names.addAll(Transactions.getDataSet());
		names.sort(Comparator.comparing(Transaction::date, LocalDate::compareTo));
	}

	@Test
	void testSpliterator() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		Spliterator<String> spliterator = names.spliterator();
		Map<String, Integer> collect = StreamSupport.stream(spliterator, false)
				.collect(Collectors.toMap(name -> name, name -> name.length()));
		System.out.println(collect);
	}

	@Test
	void testRemoveIf() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		names.add("Bran");
		names.removeIf(name -> name.startsWith("R"));
		List<String> output = Arrays.asList("Jon", "Sansa", "Arya", "Bran");
		Assert.assertEquals(output, names);
	}

	@Test
	void testToArrayGenerator() {
		List<String> names = new ArrayList<>();
		names.add("Athos");
		names.add("Porthos");
		names.add("Aramis");
		String[] usingLambda = names.toArray(val -> new String[val]);
		String[] usingMethodRefs = names.toArray(String[]::new);
		String[] output = names.toArray(new String[0]);
		Assert.assertArrayEquals(output, usingLambda);
		Assert.assertArrayEquals(output, usingMethodRefs);
	}

	@SuppressWarnings("unused")
	@Test
	void testToArrayObject() {
		List<String> names = new ArrayList<>();
		names.add("Athos");
		names.add("Porthos");
		names.add("Aramis");
		String[] namesArr1 = names.toArray(new String[0]);
		Object[] namesArr2 = names.toArray();
	}

	@Test
	void testStream() {
		List<String> names = new ArrayList<>();
		names.add("Athos");
		names.add("Porthos");
		names.add("Aramis");

		Map<String, Integer> nameWithLengths = names.stream()
				.collect(Collectors.toMap(name -> name, name -> name.length()));
		System.out.println(nameWithLengths);

		nameWithLengths = names.stream().collect(
				Collectors.toMap(Function.identity(), String::length));
		System.out.println(nameWithLengths);
	}

	@Test
	void testRemoveIfPreJava8() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		names.add("Bran");
		for (Iterator<String> iter = names.iterator(); iter.hasNext();) {
			if (iter.next().startsWith("R")) {
				iter.remove();
			}
		}

		List<String> output = Arrays.asList("Jon", "Sansa", "Arya", "Bran");
		Assert.assertEquals(output, names);
	}

	@Test
	void testCopyOf() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Rickon");
		names.add("Arya");
		names.add("Robb");
		List<String> namesCopy = List.copyOf(names);
		List<String> output = names;
		Assert.assertEquals(output, namesCopy);
	}

	@Test
	void testOf() {
		List.of();

		List.of("Rickard Stark");

		List.of("Rickard Stark", "Lyarra Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark",
				"Sansa Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark",
				"Sansa Stark", "Robb Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark",
				"Sansa Stark", "Robb Stark", "Bran Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark",
				"Sansa Stark", "Robb Stark", "Bran Stark", "Rickon Stark");

		List.of("Rickard Stark", "Lyarra Stark", "Brandon Stark", "Eddard Stark", "Lyanna Stark", "Benjen Stark",
				"Sansa Stark", "Robb Stark", "Bran Stark", "Rickon Stark", "Arya Stark");
	}

}
