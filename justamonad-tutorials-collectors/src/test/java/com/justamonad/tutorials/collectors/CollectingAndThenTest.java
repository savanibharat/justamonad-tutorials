package com.justamonad.tutorials.collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectingAndThenTest {

    @Test
    public void toListTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                Collections::unmodifiableList));

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaListTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
						Collectors.collectingAndThen(
								Collectors.toList(),
								ImmutableList::copyOf));

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toSetTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toSet(),
                                Collections::unmodifiableSet));

		System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaSetTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
						Collectors.collectingAndThen(
								Collectors.toSet(),
								ImmutableSet::copyOf));

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionSortedSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        SortedSet<Long> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
                .map(txn -> txn.transactionId())
				.collect(
						Collectors.collectingAndThen(
								Collectors.toCollection(TreeSet::new),
                                ImmutableSortedSet::copyOfSorted));

        System.out.println("\nOutput::");
        result.forEach(System.out::println);

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toUnmodifiableMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

        Map<Long, Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
                .collect(
						Collectors.collectingAndThen(
								Collectors.toMap(
										txn -> txn.transactionId(),
										txn -> txn),
							Collections::unmodifiableMap));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

        Map<Long, Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
                .collect(
						Collectors.collectingAndThen(
								Collectors.toMap(
										txn -> txn.transactionId(),
										txn -> txn),
							ImmutableMap::copyOf));

        Assert.assertEquals(2, result.size());

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
    }

    @Test
    public void toGuavaSortedMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

        SortedMap<Long, Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
                .collect(
						Collectors.collectingAndThen(
								Collectors.toMap(
									txn -> txn.transactionId(),              // keyMapper
									txn -> txn,                              // valueMapper
									(currentValue, newValue) -> currentValue, // mergeFunction
									TreeMap::new                              // Supplier
								),
							ImmutableSortedMap::copyOfSorted));

        // use Function.identity instead of txn -> txn.
        Assert.assertEquals(2, result.size());

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
		Assert.assertEquals(2, result.size());
    }

    @Test
    public void collectingAndThenUsingAveraging() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Double collect = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .map(Transaction::invoice)
                .map(Invoice::invoiceTotal)
                .collect(
						Collectors.collectingAndThen(
								Collectors.averagingLong(Money::getAmountMajorInt),
								Function.identity()));

        System.out.println("\nOutput::");
        System.out.println(collect);
    }

}
