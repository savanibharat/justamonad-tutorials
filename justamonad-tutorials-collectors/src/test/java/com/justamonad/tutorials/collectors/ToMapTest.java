package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ToMapTest {

//	toMap() method is used to collect the data from Stream into the Map. As of today the toMap() method returns
//	a Collector that uses HashMap. That means the data will be collected into an HashMap.
//	If you want provide your own implementation of Map then you need to use overloaded method toMap() and
//	provide a Supplier<> of Map.
//	Example Supplier<Map<Integer, String>> linkedMapSupplier = LinkedHashMap::new;

//	The result returned is mutable HashMap. Which means that the returned HashMap can be modified.
//	Below example collects the US country's invoices into the Map. Key - transaction_id and Value - Invoice
    @Test
    public void toMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

        Map<Long, Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toMap(
                                txn -> txn.transactionId(),
                                txn -> txn));
        // use Function.identity instead of txn -> txn.
        Assert.assertEquals(2, result.size());

//		Map<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
//				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn.invoice()));
//		// use method reference txn -> txn.invoice() Transaction::invoice
//		System.out.println();
//		txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
//		Assert.assertEquals(2, txnInvoice.size());

    }

    //	Let us look at an interesting condition of collecting data to Map. What happens if duplicate key
//	comes into the Map during collection of data elements? The method throws IllegalStateException.
//	Below example demonstrates this.
//	We get the transaction data set. Then we add existing transaction into the data set. This means that we
//	have two transactions with same transaction_id. This will cause toMap method to throw IllegalStateException.
//
//	If you want to handle this condition without throwing an exception then you need to provide key-merge function.
//	Let us take a look at that.
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
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
                                txn -> txn.transactionId(),
                                txn -> txn,
                                (currentValue, newValue) -> null));

        System.out.println("\nOutput::");
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(1, result.size());
    }

}
