package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToConcurrentMapTest {

    @Test
    public void toConcurrentMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

        ConcurrentMap<Long, Invoice> txnInvoice = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Transaction::invoice));

        System.out.println("\nOutput::");
        txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));

        Assert.assertTrue(txnInvoice instanceof ConcurrentHashMap);
        Assert.assertEquals(2, txnInvoice.size());
    }

    @Test(expected = IllegalStateException.class)
    public void toConcurrentMapDuplicateKey() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        ConcurrentMap<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Function.identity()));

        // use Function.identity instead of txn -> txn.
        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ConcurrentHashMap);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toConcurrentMapBinaryOpReplace() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        ConcurrentMap<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Function.identity(),
                                (currentValue, newValue) -> currentValue));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ConcurrentHashMap);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toConcurrentMapBinaryOpDelete() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        ConcurrentMap<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Function.identity(),
                                (currentValue, newValue) -> null));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ConcurrentHashMap);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void toConcurrentMapBinaryOpReplaceNewMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));
        ConcurrentNavigableMap<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Function.identity(),
                                (currentValue, newValue) -> currentValue,
                                ConcurrentSkipListMap::new));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ConcurrentSkipListMap);
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toConcurrentMapBinaryOpDeleteNewMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        ConcurrentNavigableMap<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toConcurrentMap(
                                Transaction::transactionId,
                                Function.identity(),
                                (currentValue, newValue) -> null,
                                ConcurrentSkipListMap::new));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ConcurrentSkipListMap);
        Assert.assertEquals(1, result.size());
    }

}
