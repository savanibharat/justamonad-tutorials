//package com.justamonad.tutorials.comparator.apache;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.commons.collections4.ComparatorUtils;
//import org.apache.commons.collections4.Transformer;
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.justamonad.tutorials.common.Transaction;
//import com.justamonad.tutorials.common.Transactions;
//
//class ApacheComparatorsTest {
//
//	@Test
//	public void testNaturalComparator() {
//		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
//		strings.sort(ComparatorUtils.naturalComparator());
//		List<String> output = Arrays.asList("a", "aa", "aaa", "z", "zz", "zzz");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testNaturalComparatorJava8() {
//		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
//		strings.sort(Comparator.naturalOrder());
//		List<String> output = Arrays.asList("a", "aa", "aaa", "z", "zz", "zzz");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testNaturalComparatorCompileTimeError() {
////		List<Transaction>  txns = Transactions.getDataSet();
////		txns.sort(ComparatorUtils.naturalComparator());
////		List<String> output = Arrays.asList("a", "aa", "aaa", "z", "zz", "zzz");
//	}
//
//	@Test
//	public void testReverseComparator() {
//		List<String> strings = Arrays.asList("aaa", "aa", "a", "zzz", "zz", "z");
//		strings.sort(ComparatorUtils.reversedComparator(null));
//		List<String> output = Arrays.asList("zzz", "zz", "z", "aaa", "aa", "a");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testReverseComparatorUsingLength() {
//		List<String> strings = Arrays.asList("azz", "aa", "zzz", "aaaa", "zz", "z");
//		strings.sort(ComparatorUtils.reversedComparator((s1, s2) -> Integer.compare(s1.length(), s2.length())));
//		List<String> output = Arrays.asList("aaaa", "azz", "zzz", "aa", "zz", "z");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testReverseComparatorUsingLengthJava8() {
//		List<String> strings = Arrays.asList("azz", "aa", "zzz", "aaaa", "zz", "z");
//		Comparator<String> comparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
//		strings.sort(comparator.reversed());
//		List<String> output = Arrays.asList("aaaa", "azz", "zzz", "aa", "zz", "z");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testReversedComparatorRunTimeError() {
//		try {
//			List<Transaction> txns = Transactions.getDataSet();
//			txns.sort(ComparatorUtils.reversedComparator(null));
//			Assert.fail();
//		} catch (ClassCastException e) {
//			Assert.assertTrue(true);
//		}
//	}
//
//	@Test
//	public void testBooleanComparatorTrueFirst() {
//		List<Boolean> booleanVals = Arrays.asList(false, false, true, false, true, true);
//		boolean trueValuesFirst = true;
//		List<Boolean> collect = booleanVals.stream().sorted(ComparatorUtils.booleanComparator(trueValuesFirst))
//				.collect(Collectors.toList());
//
//		List<Boolean> output = Arrays.asList(true, true, true, false, false, false);
//		Assert.assertEquals(output, collect);
//	}
//
//	@Test
//	public void testBooleanComparatorFalseFirst() {
//		List<Boolean> booleanVals = Arrays.asList(false, false, true, false, true, true);
//		boolean trueValuesFirst = false;
//		List<Boolean> collect = booleanVals.stream().sorted(ComparatorUtils.booleanComparator(trueValuesFirst))
//				.collect(Collectors.toList());
//
//		List<Boolean> output = Arrays.asList(false, false, false, true, true, true);
//		Assert.assertEquals(output, collect);
//	}
//
//	@Test
//	public void testNullsLowComparator() {
//		List<String> strings = Arrays.asList("azz", null, "aa", "zzz", null, null, "aaaa", "zz", "z");
//		strings.sort(ComparatorUtils.nullLowComparator((s1, s2) -> Integer.compare(s1.length(), s2.length())));
//		List<String> output = Arrays.asList(null, null, null, "z", "aa", "zz", "azz", "zzz", "aaaa");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testNullsFirstJava8() {
//		List<String> strings = Arrays.asList("azz", null, "aa", "zzz", null, null, "aaaa", "zz", "z");
//		strings.sort(Comparator.nullsFirst((s1, s2) -> Integer.compare(s1.length(), s2.length())));
//		List<String> output = Arrays.asList(null, null, null, "z", "aa", "zz", "azz", "zzz", "aaaa");
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testNullsLastComparator() {
//		List<String> strings = Arrays.asList("azz", null, "aa", "zzz", null, null, "aaaa", "zz", "z");
//		strings.sort(ComparatorUtils.nullHighComparator((s1, s2) -> Integer.compare(s1.length(), s2.length())));
//		List<String> output = Arrays.asList("z", "aa", "zz", "azz", "zzz", "aaaa", null, null, null);
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testNullsHighComparator() {
//		List<String> strings = Arrays.asList("azz", null, "aa", "zzz", null, null, "aaaa", "zz", "z");
//		strings.sort(Comparator.nullsLast((s1, s2) -> Integer.compare(s1.length(), s2.length())));
//		List<String> output = Arrays.asList("z", "aa", "zz", "azz", "zzz", "aaaa", null, null, null);
//		Assert.assertEquals(output, strings);
//	}
//
//	@Test
//	public void testTransformedComparator() {
//		List<Transaction> transactions = Transactions.getDataSet();
//		System.out.println("Before sort");
//		transactions.forEach(val -> System.out.println(val.date()));
//		Comparator<LocalDate> comparator = LocalDate::compareTo;
//		Transformer<Transaction, LocalDate> transformer = Transaction::date;
//		Comparator<Transaction> dateComp = ComparatorUtils.transformedComparator(comparator, transformer);
//		transactions.sort(dateComp);
//		System.out.println("After sort");
//		transactions.forEach(val -> System.out.println(val.date()));
//	}
//
//	@Test
//	public void testComparatorComparingKeyExtractorComparator() {
//		List<Transaction> transactions = Transactions.getDataSet();
//		System.out.println("Before sort");
//		transactions.forEach(val -> System.out.println(val.date()));
//		Comparator<Transaction> dateComp = Comparator.comparing(txn -> txn.date(), LocalDate::compareTo);
//		transactions.sort(dateComp);
//		System.out.println("After sort");
//		transactions.forEach(val -> System.out.println(val.date()));
//	}
//
//	@Test
//	public void testMinComparator() {
//		Comparator<String> strLength = (s1, s2) -> Integer.compare(s1.length(), s2.length());
//		String min = ComparatorUtils.min("Sansa", "Jon", strLength);
//		Assert.assertEquals("Jon", min);
//	}
//
//	@Test
//	public void testMaxComparator() {
//		Comparator<String> strLength = (s1, s2) -> Integer.compare(s1.length(), s2.length());
//		String min = ComparatorUtils.max("Sansa", "Jon", strLength);
//		Assert.assertEquals("Sansa", min);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testChainedComparatorDate() {
//		List<Transaction> transactions = Transactions.getDataSet();
//		System.out.println("Before sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//		Comparator<Transaction> transactionDateComp = (txn1, txn2) -> txn1.date().compareTo(txn2.date());
//		Comparator<Transaction> comp = ComparatorUtils.chainedComparator(transactionDateComp);
//		transactions.sort(comp);
//		System.out.println("After sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testChainedComparatorDateAndCountry() {
//		List<Transaction> transactions = Transactions.getDataSet();
//		System.out.println("Before sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//		Comparator<Transaction> transactionDateComp = (txn1, txn2) -> txn1.date().compareTo(txn2.date());
//		Comparator<Transaction> transactionCountryComp = (txn1, txn2) -> txn1.country().compareTo(txn2.country());
//		Comparator<Transaction> comp = ComparatorUtils.chainedComparator(transactionDateComp, transactionCountryComp);
//		transactions.sort(comp);
//		System.out.println("After sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//	}
//
//	@Test
//	public void testChainedComparatorDateAndCountryUsingList() {
//		List<Transaction> transactions = Transactions.getDataSet();
//		System.out.println("Before sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//
//		Comparator<Transaction> transactionDateComp = (txn1, txn2) -> txn1.date().compareTo(txn2.date());
//		Comparator<Transaction> transactionCountryComp = (txn1, txn2) -> txn1.country().compareTo(txn2.country());
//		List<Comparator<Transaction>> comparators = Arrays.asList(transactionDateComp, transactionCountryComp);
//
//		Comparator<Transaction> comp = ComparatorUtils.chainedComparator(comparators);
//		transactions.sort(comp);
//
//		System.out.println("After sort");
//		transactions.forEach(val -> System.out.println(val.date() + "," + val.country()));
//	}
//
//}
