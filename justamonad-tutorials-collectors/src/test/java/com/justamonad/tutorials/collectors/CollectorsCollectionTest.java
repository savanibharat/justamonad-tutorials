package com.justamonad.tutorials.collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.annotations.Beta;
import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;

public class CollectorsCollectionTest {

	@Test
	public void toList() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toList());
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toUnmodifiableList() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toUnmodifiableList());
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toSet());
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toUnmodifiableSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toUnmodifiableSet());
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
		// use Function.identity instead of txn -> txn.
		Assert.assertEquals(2, result.size());

		Map<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn.invoice()));
		// use method reference txn -> txn.invoice() Transaction::invoice
		System.out.println();
		txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
		Assert.assertEquals(2, txnInvoice.size());

	}

	@Test(expected = IllegalStateException.class)
	public void toMapDuplicateKey() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
		// use Function.identity instead of txn -> txn.
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toMapBinaryOpReplace() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn, (o1, o2) -> o1));
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toMapBinaryOpDelete() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		// deleted the entry if BinOp returns null.
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn, (o1, o2) -> null));
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(1, result.size());

	}

	@Test
	public void toMapBinaryOpReplaceNewMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn, (o1, o2) -> o1, LinkedHashMap::new));
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toMapBinaryOpDeleteNewMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		// deleted the entry if BinOp returns null.
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US).collect(
				Collectors.toMap(txn -> txn.transactionId(), txn -> txn, (o1, o2) -> null, LinkedHashMap::new));
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(1, result.size());

	}

	@Test
	public void toConcurrentMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
		// use Function.identity instead of txn -> txn.
		Assert.assertEquals(2, result.size());

		Map<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn.invoice()));
		// use method reference txn -> txn.invoice() Transaction::invoice
		System.out.println();
		txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
		Assert.assertEquals(2, txnInvoice.size());

	}
	
	@Test
	@Beta
	public void toConcurrentMapAndThenUnmodifiableMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
		// use Function.identity instead of txn -> txn.
		Assert.assertEquals(2, result.size());

		Collector<Transaction, ?, ConcurrentMap<Long, Invoice>> toConcurrentMap = Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn.invoice());
		
		Collector<Transaction, ?, Map<Long, Invoice>> collectingAndThen = 
				Collectors.collectingAndThen(toConcurrentMap, 
						map -> (Map<Long, Invoice>)Map.ofEntries(map.entrySet().toArray(new Map.Entry[0])));
		
		Map<Long, Invoice> txnInvoice = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(collectingAndThen);
		
		// use method reference txn -> txn.invoice() Transaction::invoice
		System.out.println();
		txnInvoice.forEach((k, v) -> System.out.println(k + " :: " + v.date()));
		Assert.assertEquals(2, txnInvoice.size());

	}

	@Test(expected = IllegalStateException.class)
	public void toConcurrentMapDuplicateKey() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
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

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
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
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US).collect(
				Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn, (currentValue, newValue) -> null));
		System.out.println();
		result.forEach((k, v) -> System.out.println(k + " :: " + v.country()));
		Assert.assertEquals(1, result.size());

	}

	@Test
	public void toConcurrentMapBinaryOpReplaceNewMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.add(dataSet.get(0));
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
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
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
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

	@Test
	public void toCollectionLinkedHashSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toCollection(LinkedHashSet::new));
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionTreeSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Long> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId()).collect(Collectors.toCollection(TreeSet::new));
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionTreeSetReverseOrder() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Long> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId())
				.collect(Collectors.toCollection(() -> new TreeSet<Long>(Comparator.reverseOrder())));
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionPriorityQueue() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		PriorityQueue<Long> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId()).collect(Collectors.toCollection(PriorityQueue::new));
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionPriorityQueueReverseOrder() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		PriorityQueue<Long> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId())
				.collect(Collectors.toCollection(() -> new PriorityQueue<Long>(Comparator.reverseOrder())));
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionArrayListWithCapacity() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toCollection(() -> new ArrayList<Transaction>(20)));
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

}
