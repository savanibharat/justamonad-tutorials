package com.justamonad.tutorials.collectors;

import com.google.common.collect.ImmutableMap;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMapTest {

    @Test
    public void toMapTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                Function.identity()));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());
    }

    @Test(expected = IllegalStateException.class)
    public void toMapDuplicateKey() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                txn -> txn));

        // use Function.identity instead of txn -> txn.
        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toMapBinaryOpReplace() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        BinaryOperator<Transaction> mergeFunction = (Transaction o1, Transaction o2) -> o1;
        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                txn -> txn,
                                mergeFunction));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toMapBinaryOpDelete() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (o1, o2) -> null));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(1, result.size());

    }

    @Test
    public void toMapBinaryOpReplaceNewMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (o1, o2) -> o1,
                                LinkedHashMap::new));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toMapBinaryOpDeleteNewMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (o1, o2) -> null,
                                LinkedHashMap::new));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void toUnmodifiableMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toUnmodifiableMap(
                                Transaction::transactionId,
                                txn -> txn));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toUnmodifiableMapBinaryOpReplace() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toUnmodifiableMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (currentValue, newValue) -> currentValue));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toUnmodifiableMapBinaryOpRemove() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toUnmodifiableMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (currentValue, newValue) -> null));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void toGuavaImmutableMap() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        ImmutableMap.toImmutableMap(
                                Transaction::transactionId,
                                txn -> txn));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ImmutableMap);
        Assert.assertEquals(2, result.size());
    }

    // Collectors.toMap throws IllegalStateException.
    @Test(expected = IllegalArgumentException.class)
    public void toGuavaImmutableMapDuplicateKey() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        ImmutableMap.toImmutableMap(
                                Transaction::transactionId,
                                txn -> txn));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ImmutableMap);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaImmutableMapBinaryOpReplace() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        BinaryOperator<Transaction> mergeFunction = (Transaction o1, Transaction o2) -> o1;
        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        ImmutableMap.toImmutableMap(
                                Transaction::transactionId,
                                txn -> txn,
                                mergeFunction));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ImmutableMap);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toGuavaImmutableMapBinaryOpDelete() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        ImmutableMap.toImmutableMap(
                                Transaction::transactionId,
                                txn -> txn,
                                (o1, o2) -> null));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertTrue(result instanceof ImmutableMap);
        Assert.assertEquals(1, result.size());
    }

}
