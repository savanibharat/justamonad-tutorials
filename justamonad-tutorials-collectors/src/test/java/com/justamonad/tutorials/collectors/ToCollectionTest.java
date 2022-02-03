package com.justamonad.tutorials.collectors;

import java.lang.reflect.Method;
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
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.annotations.Beta;
import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;

public class ToCollectionTest {

	@Test
	public void getAllCollectorsMethods() {
		try {
			Class<Collectors> thisClass = Collectors.class;
			Method[] methods = thisClass.getDeclaredMethods();
			Set<String> set = new TreeSet<>();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].toString().startsWith("public static")) {

					String[] split = methods[i].toString().split(" ");
					set.add(split[split.length - 1].replace("java.util.stream.", ""));
					//System.out.println(split[split.length - 1]);
				}
			}
			set.forEach(System.out::println);
		} catch (Throwable e) {
			System.err.println(e);
		}
		
	}

//	Collectors is a final class with private constructor. I am currently using Azul's OpenJDK version azul-14.0.1.
//	It has 44 static methods that has Collector interface's implementation. In this article we will take a look at
//	n different methods.
//	Remember this static methods returns a Collector itself. They don't do any collecting. This responsibility is
//	of collect method of Stream interface. If you want to look at implementation see class 
//	java.util.stream.ReferencePipeline's method collect.

//	This article is primarily geared towards collecting data in Java Collections like List, Set, Map. To ease your 
//	understanding I have added 26 examples for this methods.

//	toList() method is used to collect the data from Stream into the List. As of today the toList() method returns
//	a Collector that uses ArrayList. That means the data will be collected into an ArrayList.
//	If you want provide your own implementation of List then you need to use toCollection method. We will come to 
//	that later in this article.

//	
//	One more thing, the result returned is mutable ArrayList. Which means that the returned ArrayList can be modified.
//	Below example collects the US country's transactions into the List.
	@Test
	public void toList() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toList());
		
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

//	toUnmodifiableList() method is was added to Collectors class in Java 10.
//	toUnmodifiableList() method is used to return data in List which cannot be modified. Or the returned List 
//	is immutable. 
//	The result is NOT wrapped in Collections.unmodifiableList(list). A little bit of history. As of Java 9 List
//	interface was augmented with several static factory that returns immutable List. [provide article line] Read about it here.
//	The result is first collected into ArrayList. And the finisher function of Collector is defined as 
//	list -> (List<T>)List.of(list.toArray())
//	List.of methods copies all the elements from this list into new List and new immutable List is returned.

//	If you are using Java 8 and want to convert to an unmodfiableList then you need to use collectingAndThen method
//	collect(Collectors.toList(), Collections::unmodfiableList);
//	Below example collects the US country's transactions into the immutable List.
	@Test
	public void toUnmodifiableList() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toUnmodifiableList());
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

//	toSet() method is used to collect the data from Stream into the Set. As of today the toSet() method returns
//	a Collector that uses HashSet. That means the data will be collected into an HashSet.
//	If you want provide your own implementation of Set then you need to use toCollection method.
//	Remember Set will filter out duplicate eleemnts.

//	The result returned is mutable HashSet. Which means that the returned HashSet can be modified. 
//	Below example collects the US country's transactions into the Set. 
	@Test
	public void toSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toSet());
		
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

//	toUnmodifiableSet() method is was added to Collectors class in Java 10.
//	toUnmodifiableSet() method is used to return data in Set which cannot be modified. Or the returned Set 
//	is immutable. 
//	The result is NOT wrapped in Collections.unmodifiableSet(set). A little bit of history. As of Java 9 Set
//	interface was augmented with several static factory that returns immutable Set. [provide article line] Read about it here.
//	The result is first collected into HashSet. And the finisher function of Collector is defined as 
//	set -> (Set<T>)Set.of(set.toArray())
//	Set.of methods copies all the elements from this set into new Set and new immutable Set is returned.

//	If you are using Java 8 and want to convert to an unmodfiableSet then you need to use collectingAndThen method
//	collect(Collectors.toSet(), Collections::unmodfiableSet);
//	Below example collects the US country's transactions into the immutable Set.	
	@Test
	public void toUnmodifiableSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toUnmodifiableSet());
		
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

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
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toConcurrentMap(txn -> txn.transactionId(), txn -> txn));
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

		BinaryOperator<Transaction> mergeFunction = (Transaction o1, Transaction o2) -> o1;
		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn, mergeFunction));
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
	@Test
	@Beta
	public void toConcurrentMapAndThenUnmodifiableMap() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.invoice().date()));

		Map<Long, Transaction> result = dataSet.stream().filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toMap(txn -> txn.transactionId(), txn -> txn));
		// use Function.identity instead of txn -> txn.
		Assert.assertEquals(2, result.size());

		Collector<Transaction, ?, ConcurrentMap<Long, Invoice>> toConcurrentMap = Collectors
				.toConcurrentMap(txn -> txn.transactionId(), txn -> txn.invoice());

		Collector<Transaction, ?, Map<Long, Invoice>> collectingAndThen = Collectors.collectingAndThen(toConcurrentMap,
				map -> (Map<Long, Invoice>) Map.ofEntries(map.entrySet().toArray(new Map.Entry[0])));

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

	@Test
	public void toCollectionLinkedHashSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(Collectors.toCollection(LinkedHashSet::new));
		
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionTreeSet() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Long> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId())
				.collect(Collectors.toCollection(TreeSet::new));
		
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionTreeSetReverseOrder() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Set<Long> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId())
				.collect(
						Collectors.toCollection(() -> new TreeSet<Long>(Comparator.reverseOrder())));
		
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

		PriorityQueue<Long> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(txn -> txn.transactionId())
				.collect(
						Collectors.toCollection(() -> new PriorityQueue<Long>(Comparator.reverseOrder())));
		System.out.println();
		result.forEach(System.out::println);

		Assert.assertEquals(2, result.size());

	}

	@Test
	public void toCollectionArrayListWithCapacity() {

		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		List<Transaction> result = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.collect(
						Collectors.toCollection(() -> new ArrayList<Transaction>(20)));
		System.out.println();
		result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

		Assert.assertEquals(2, result.size());

	}

}
