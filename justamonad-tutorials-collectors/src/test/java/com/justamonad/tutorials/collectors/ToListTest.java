package com.justamonad.tutorials.collectors;

import com.google.common.collect.ImmutableList;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ToListTest {

    @Test
    public void toList() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toList());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toUnmodifiableList() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableList());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaImmutableList() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(ImmutableList.toImmutableList());

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

}
