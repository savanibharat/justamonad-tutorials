package com.justamonad.tutorials.collectors.list;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class ToConcurrentMapTest {

    @Test
    public void toConcurrentMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

//		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
//				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
//		// use Function.identity instead of txn -> txn.
//		Assert.assertEquals(2, result.size());

        ConcurrentMap<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn.invoice()));
        // use method reference txn -> txn.invoice() Transaction::invoice
        System.out.println();
        txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
        Assert.assertEquals(2, txnInvoice.size());

    }

    @SuppressWarnings("unchecked")
//	@Test
//	@Beta
//	public void toConcurrentMapAndThenUnmodifiableMap() {
//
//		List<Transaction> dataSet = Transactions.getDataSet();
//		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));
//
//		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
//				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
//		// use Function.identity instead of txn -> txn.
//		Assert.assertEquals(2, result.size());
//
//		Collector<Transaction, ?, ConcurrentMap<Long, Invoice>> toConcurrentMap = Collectors
//				.toConcurrentMap(txn -> txn.transactionId(), txn -> txn.invoice());
//
//		Collector<Transaction, ?, Map<Long, Invoice>> collectingAndThen = Collectors.collectingAndThen(toConcurrentMap,
//				map -> (Map<Long, Invoice>) Map.ofEntries(map.entrySet().toArray(new Map.Entry[0])));
//
//		Map<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
//				.collect(collectingAndThen);
//
//		// use method reference txn -> txn.invoice() Transaction::invoice
//		System.out.println();
//		txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
//		Assert.assertEquals(2, txnInvoice.size());
//
//	}

    @Test(expected = IllegalStateException.class)
    public void toConcurrentMapDuplicateKey() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        ConcurrentMap<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn));
        // use Function.identity instead of txn -> txn.
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toConcurrentMapBinaryOpReplace() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        ConcurrentMap<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn,
                        (currentValue, newValue) -> currentValue));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toConcurrentMapBinaryOpDelete() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        ConcurrentMap<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn,
                        (currentValue, newValue) -> null));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(1, result.size());

    }

    @Test
    public void toConcurrentMapBinaryOpReplaceNewMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));
        ConcurrentNavigableMap<Long, Transaction> result = dataSet.stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn,
                        (currentValue, newValue) -> currentValue, ConcurrentSkipListMap::new));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toConcurrentMapBinaryOpDeleteNewMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        // deleted the entry if BinOp returns null.
        ConcurrentNavigableMap<Long, Transaction> result = dataSet.stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn,
                        (currentValue, newValue) -> null, ConcurrentSkipListMap::new));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
        Assert.assertEquals(1, result.size());

    }

    @Test
    public void toUnmodifiableMap() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableMap(txn -> txn.transactionId(), txn -> txn));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toUnmodifiableMapBinaryOpReplace() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableMap(txn -> txn.transactionId(), txn -> txn,
                        (currentValue, newValue) -> currentValue));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void toUnmodifiableMapBinaryOpRemove() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.add(dataSet.get(0));
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US).collect(
                Collectors.toUnmodifiableMap(txn -> txn.transactionId(), txn -> txn, (currentValue, newValue) -> null));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));

        Assert.assertEquals(1, result.size());

    }

}
