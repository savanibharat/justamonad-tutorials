package com.justamonad.tutorials.collectors;

import com.google.common.collect.ImmutableSet;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToSetTest {

    @Test
    public void toSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toSet());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertTrue(result instanceof HashSet);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toUnmodifiableSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableSet());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertFalse(result instanceof HashSet);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaImmutableSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(ImmutableSet.toImmutableSet());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

}
