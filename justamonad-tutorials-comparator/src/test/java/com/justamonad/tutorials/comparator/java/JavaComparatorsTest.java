package com.justamonad.tutorials.comparator.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class JavaComparatorsTest {

	@Test
	public void compareTo() {
		List<Transaction> transactions = Transactions.getDataSet();
		Comparator<Transaction> TIME_COMPARATOR = new Comparator<Transaction>() {
			@Override
			public int compare(Transaction t1, Transaction t2) {
				return t1.date().compareTo(t2.date());
			}
		};
		transactions.sort(TIME_COMPARATOR);
		Collections.sort(transactions, TIME_COMPARATOR);
	}

	@Test
	public void compareToUsingLambda() {
		List<Transaction> transactions = Transactions.getDataSet();
		Comparator<Transaction> TIME_COMPARATOR = (Transaction t1, Transaction t2) -> t1.date().compareTo(t2.date());
		transactions.sort(TIME_COMPARATOR);
		transactions.stream().forEach(val -> System.out.println(val.date()));
	}

	@Test
	public void reversed() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sorting", transactions);
		Comparator<Transaction> timeComp = (t1, t2) -> t1.date().compareTo(t2.date());
		Comparator<Transaction> reverseTimeComp = timeComp.reversed();
		transactions.sort(reverseTimeComp);
		print("After sorting", transactions);
	}

	@Test
	public void thenComparingComparator() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sorting", transactions);
		Comparator<Transaction> countryComp = (t1, t2) -> t1.country().compareTo(t2.country());
		Comparator<Transaction> timeComp = (t1, t2) -> t1.date().compareTo(t2.date());
		Comparator<Transaction> thenComparing = countryComp.thenComparing(timeComp);
		transactions.sort(thenComparing);
		print("After sorting", transactions);
	}

	@Test
	public void thenComparingReverseComparator() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sorting", transactions);
		Comparator<Transaction> countryComp = (t1, t2) -> t1.country().compareTo(t2.country());
		Comparator<Transaction> timeComp = (t1, t2) -> t1.date().compareTo(t2.date());
		Comparator<Transaction> timeReverse = timeComp.reversed();
		Comparator<Transaction> thenComparing = countryComp.thenComparing(timeReverse);
		transactions.sort(thenComparing);
		print("After sorting", transactions);
	}

	@Test
	public void thenComparingKeyExtractorComparator() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sorting", transactions);
		Comparator<Transaction> countryComp = (t1, t2) -> t1.country().compareTo(t2.country());
		transactions.sort(countryComp);
		printCurrency("After sorting", transactions);
		Comparator<BigDecimal> amountComp = BigDecimal::compareTo;
		Comparator<Transaction> thenComparing = countryComp.thenComparing(Transaction::amount, amountComp);
		transactions.sort(thenComparing);
		printCurrency("After sorting", transactions);
	}

	@Test
	public void thenComparingKeyExtractor() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sorting", transactions);
		Comparator<Transaction> countryComp = (t1, t2) -> t1.country().compareTo(t2.country());
		transactions.sort(countryComp);
		print("Before sorting", transactions);
		Comparator<Transaction> thenComparing = countryComp.thenComparing(Transaction::date);
		transactions.sort(thenComparing);
		print("Before sorting", transactions);
	}

	@Test
	public void thenComparingInt() {
		List<Transaction> transactions = Transactions.getDataSet();
		Collections.shuffle(transactions);
		printNumeric("Before sorting", transactions);
		Comparator<Transaction> dateComp = (t1, t2) -> t1.date().compareTo(t2.date());
		transactions.sort(dateComp);
		printNumeric("Before sorting", transactions);
		Comparator<Transaction> thenComparing = dateComp.thenComparingInt(txn -> txn.country().getNumeric());
		transactions.sort(thenComparing);
		printNumeric("Before sorting", transactions);
	}

	@Test
	public void thenComparingLong() {
		List<Transaction> transactions = Transactions.getDataSet();
		printTxnId("Before sorting", transactions);
		Comparator<Transaction> dateComp = (t1, t2) -> t1.date().compareTo(t2.date());
		transactions.sort(dateComp);
		printTxnId("Before sorting", transactions);
		Comparator<Transaction> thenComparing = dateComp.thenComparingLong(Transaction::transactionId);
		transactions.sort(thenComparing);
		printTxnId("Before sorting", transactions);
	}

	@Test
	public void thenComparingDouble() {
		List<Transaction> transactions = Transactions.getDataSet();
		printCurrency("Before sorting", transactions);
		Comparator<Transaction> dateComp = (t1, t2) -> t1.date().compareTo(t2.date());
		transactions.sort(dateComp);
		printCurrency("Before sorting", transactions);
		Comparator<Transaction> thenComparing = dateComp.thenComparingDouble(txn -> txn.amount().doubleValue());
		transactions.sort(thenComparing);
		printCurrency("Before sorting", transactions);
	}

	@Test
	public void nullsFirst() {
		List<Integer> numbers = Arrays.asList(56, 73, null, 42, 3, 7, null);
		numbers.sort(Comparator.nullsFirst(Integer::compare));
		Assert.assertEquals(Arrays.asList(null, null, 3, 7, 42, 56, 73), numbers);
		System.out.println(numbers);
	}

	@Test
	public void nullsFirstLambdaExpr() {
		List<Integer> numbers = Arrays.asList(56, 73, null, 42, 3, 7, null);
		numbers.sort(Comparator.nullsFirst((x, y) -> Integer.compare(x, y)));
		Assert.assertEquals(Arrays.asList(null, null, 3, 7, 42, 56, 73), numbers);
		System.out.println(numbers);
	}

	@Test
	public void nullsLast() {
		List<Integer> numbers = Arrays.asList(56, 73, null, 42, 3, 7, null);
		numbers.sort(Comparator.nullsLast(Integer::compare));
		Assert.assertEquals(Arrays.asList(3, 7, 42, 56, 73, null, null), numbers);
		System.out.println(numbers);
	}

	@Test
	public void nullsLastLambdaExpr() {
		List<Integer> numbers = Arrays.asList(56, 73, null, 42, 3, 7, null);
		numbers.sort(Comparator.nullsLast((x, y) -> Integer.compare(x, y)));
		Assert.assertEquals(Arrays.asList(3, 7, 42, 56, 73, null, null), numbers);
		System.out.println(numbers);
	}

	@Test
	public void reverseOrder() {
		List<Integer> numbers = Arrays.asList(56, 73, 42, 3, 7);
		numbers.sort(Comparator.reverseOrder());
		Assert.assertEquals(Arrays.asList(73, 56, 42, 7, 3), numbers);
		System.out.println(numbers);
	}

	@Test
	public void naturalOrder() {
		List<Integer> numbers = Arrays.asList(56, 73, 42, 3, 7);
		numbers.sort(Comparator.naturalOrder());
		Assert.assertEquals(Arrays.asList(73, 56, 42, 7, 3), numbers);
		System.out.println(numbers);
	}

	@Test
	public void reverseOrderNullsLast() {
		List<Integer> numbers = Arrays.asList(null, 56, null, 73, 42, 3, 7);
		Comparator<Integer> nullsFirst = Comparator.nullsFirst(Integer::compare);
		Comparator<Integer> reversedNullsFirst = nullsFirst.reversed();
		numbers.sort(reversedNullsFirst);
		Assert.assertEquals(Arrays.asList(73, 56, 42, 7, 3, null, null), numbers);
		System.out.println(numbers);
	}

	@Test
	public void reversedNaturalOrderNumbers() {
		List<Integer> numbers = Arrays.asList(56, 73, 42, 3, 7);
		numbers.sort(Comparator.naturalOrder());
		Assert.assertEquals(Arrays.asList(3, 7, 42, 56, 73), numbers);
		System.out.println(numbers);
	}

	@Test
	public void reversedNaturalOrderStrings() {
		List<String> strings = Arrays.asList("Shelby", "Jon", "Sansa", "Shelly");
		strings.sort(Comparator.naturalOrder());
		System.out.println(strings);
	}

	// Takes two comparable object and convert it to comparator.
	// Comparator<Comparable<>>
	@Test
	public void reversedNaturalOrderStringsInteresting() {
		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
		strings.sort(Comparator.naturalOrder());
		System.out.println(strings);
	}

	@Test
	public void reversedReverseOrderStringsInteresting() {
		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
		strings.sort(Comparator.reverseOrder());
		System.out.println(strings);
	}

	@Test
	public void naturalOrderStringsInteresting() {
		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
		strings.sort(Comparator.naturalOrder());
		Assert.assertEquals(Arrays.asList("a", "aa", "aaa", "z", "zz", "zzz"), strings);
	}

	@Test
	public void publicStaticComparing() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sort", transactions);
		Comparator<Transaction> comparing = Comparator.comparing(Transaction::date, LocalDate::compareTo);
		transactions.sort(comparing);
		print("Before sort", transactions);
	}

	@Test
	public void publicStaticComparingReverse() {
		List<Transaction> transactions = Transactions.getDataSet();
		print("Before sort", transactions);
		Comparator<Transaction> comparing = Comparator.comparing(Transaction::date, LocalDate::compareTo).reversed();
		transactions.sort(comparing);
		print("Before sort", transactions);
	}

	@Test
	public void publicStaticComparingComparable() {
		List<Transaction> transactions = Transactions.getDataSet();
		printCurrency("Before sort", transactions);
		Comparator<Transaction> comparing = Comparator.comparing(Transaction::amount);
		transactions.sort(comparing);
		printCurrency("Before sort", transactions);
	}

	@Test
	public void comparingInt() {
		List<Transaction> transactions = Transactions.getDataSet();
		Collections.shuffle(transactions);
		printNumeric("Before sorting", transactions);
		Comparator<Transaction> comparingInt = Comparator.comparingInt(txn -> txn.country().getNumeric());
		transactions.sort(comparingInt);
		printNumeric("Before sorting", transactions);
	}

	@Test
	public void comparingLong() {
		List<Transaction> transactions = Transactions.getDataSet();
		printTxnId("Before sorting", transactions);
		Comparator<Transaction> thenComparing = Comparator.comparingLong(Transaction::transactionId);
		transactions.sort(thenComparing);
		printTxnId("Before sorting", transactions);
	}

	@Test
	public void comparingDouble() {
		List<Transaction> transactions = Transactions.getDataSet();
		printCurrency("Before sorting", transactions);
		Comparator<Transaction> thenComparing = Comparator.comparingDouble(txn -> txn.amount().doubleValue());
		transactions.sort(thenComparing);
		printCurrency("Before sorting", transactions);
	}

	public static void print(String message, List<Transaction> transactions) {
		System.out.println(message);
		transactions.forEach(val -> {
			if (val != null) {
				System.out.println(val.date() + ", " + val.country());
				return;
			} else {
				System.out.println("null");
			}
		});
		System.out.println();
	}

	public static void printNumeric(String message, List<Transaction> transactions) {
		System.out.println(message);
		transactions.forEach(
				val -> System.out.println(val.date() + ", " + val.country() + ", " + val.country().getNumeric()));
		System.out.println();
	}

	public static void printTxnId(String message, List<Transaction> transactions) {
		System.out.println(message);
		transactions.forEach(val -> System.out.println(val.date() + ", " + val.country() + ", " + val.transactionId()));
		System.out.println();
	}

	public static void printCurrency(String message, List<Transaction> transactions) {
		System.out.println(message);
		transactions.forEach(val -> System.out.println(val.date() + ", " + val.amount() + ", " + val.country()));
		System.out.println();
	}

}
