package com.justamonad.tutorials.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.comparator.Comparators;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class SpringComparatorsTest {

	@Test(expected = ClassCastException.class)
	public void testClassCastException() {
		Comparator<Transaction> comparable = Comparators.comparable();
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.sort(comparable);
	}

	@Test
	public void testNumbersComparable() {
		Comparator<Integer> comparable = Comparators.comparable();
		List<Integer> numbers = Arrays.asList(56, 73, 42, 3, 7);
		numbers.sort(comparable);
		Assert.assertEquals(Arrays.asList(3, 7, 42, 56, 73), numbers);
	}

	@Test(expected = ClassCastException.class)
	public void nullsLowException() {
		Comparator<Transaction> comparable = Comparators.nullsLow();
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.sort(comparable);
	}

	@Test
	public void nullsLowComparator() {
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.add(1, null);
		ComparatorsTest.print("Before sorting", transactions);
		Comparator<Transaction> comparator = (t1, t2) -> t1.date().compareTo(t2.date());
		Comparator<Transaction> nullsLow = Comparators.nullsLow(comparator);
		transactions.sort(nullsLow);
		ComparatorsTest.print("After sorting", transactions);
	}

	@Test
	public void nullsHigh() {
		List<Integer> numbers = Arrays.asList(56, 73, null, 42, 3, 7, null);
		numbers.sort(Comparators.nullsHigh());
		Assert.assertEquals(Arrays.asList(3, 7, 42, 56, 73, null, null), numbers);
		System.out.println(numbers);
	}

	@Test(expected = ClassCastException.class)
	public void nullsHighException() {
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.sort(Comparators.nullsHigh());
	}

	@Test
	public void nullsHighCompartor() {
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.add(1, null);
		ComparatorsTest.print("Before sorting", transactions);
		Comparator<Transaction> comparator = (t1, t2) -> t1.date().compareTo(t2.date());
		Comparator<Transaction> nullsHigh = Comparators.nullsHigh(comparator);
		transactions.sort(nullsHigh);
		ComparatorsTest.print("After sorting", transactions);
	}

}
