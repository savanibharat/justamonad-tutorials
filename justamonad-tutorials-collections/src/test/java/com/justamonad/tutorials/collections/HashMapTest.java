package com.justamonad.tutorials.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

//Map<String, String> symbols = new HashMap<>();
//symbols.put("%", "Percent");
//symbols.put("*", "Asterisk");
//symbols.put("~", "Tilde");
//symbols.put("$", "Dollar");
//symbols.put("#", "Octothorpe");

@SuppressWarnings("unused")
public class HashMapTest {

	@Test
	public void hashMapNoArgs() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		Assert.assertEquals(txnDataSet.size(), transactions.size());
	}

	@Test
	public void hashMapInitialCapacity() {

		int initialCapacity = 20;
		Map<Long, Transaction> transactions = new HashMap<>(initialCapacity);

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		Assert.assertEquals(txnDataSet.size(), transactions.size());
	}

	@Test
	public void hashMapInitialCapacityWithLoadFactor() {
		// Class comment 3,4 and 5
		int initialCapacity = 16;
		float loadFactor = 0.75f;
		Map<Long, Transaction> transactions = new HashMap<>(initialCapacity, loadFactor);

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		Assert.assertEquals(txnDataSet.size(), transactions.size());
	}

	@Test
	public void hashMapCopy() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		// initialCapacity and loadFactor is 16 and 0.75f
		Map<Long, Transaction> txnsCopy = new HashMap<>(transactions);
		Assert.assertEquals(transactions.size(), txnsCopy.size());
	}

	@Test(expected = NullPointerException.class)
	public void hashMapCopyNPE() {
		@SuppressWarnings("unused")
		Map<Long, Transaction> transactions = new HashMap<>(null);
	}

	@Test
	public void putMethod1() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Assert.assertEquals(transactions.size(), transactions.size());
	}

	@Test
	public void putMethod2() {
		Map<Long, String> transactions = new HashMap<>();
		transactions.put(null, "value1");

		transactions.put(null, "value999");
	}

	@Test
	public void putMethod3() {
		Map<Long, String> transactions = new HashMap<>();
		transactions.put(null, "value1");

		String previousValue = transactions.put(null, "value999");
		Assert.assertEquals("value1", previousValue);
	}

	@Test
	public void putMethod4() {
		Map<Long, String> transactions = new HashMap<>();
		transactions.put(null, null);

		String previousValue = transactions.put(null, "value999");
		Assert.assertEquals(null, previousValue);
	}

	@Test
	public void getMethod1() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		Long transactionId = txnDataSet.get(0).transactionId();
		Transaction transaction = transactions.get(transactionId);
		Assert.assertNotNull(transaction.transactionId());
	}

	@Test
	public void getMethod2() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		transactions.put(null, txnDataSet.get(0));
		Transaction transaction = transactions.get(null);
		Assert.assertNotNull(transaction);
	}

	@Test
	public void getMethod3() {
		Map<Long, Transaction> transactions = new HashMap<>();

		transactions.put(null, null);
		Transaction transaction = transactions.get(null);
		Assert.assertNull(transaction);
	}

	@Test
	public void getMethod4() {
		Map<Long, Transaction> transactions = new HashMap<>();

		// doesn't exists.
		Transaction transaction = transactions.get(Long.valueOf(238989324787234L));
		Assert.assertNull(transaction);
	}

	@Test
	public void containsKey1() {
		Map<String, String> symbols = new HashMap<>();
		symbols.put("%", "Percent");
		symbols.put("*", "Asterisk");
		symbols.put("~", "Tilde");
		symbols.put("$", "Dollar");
		symbols.put("#", "Octothorpe");

		boolean containsAsterick = symbols.containsKey("*");
		Assert.assertTrue(containsAsterick);

		boolean containsOne = symbols.containsKey("1");
		Assert.assertFalse(containsOne);
	}

	@Test
	public void containsKey2() {
		Map<String, String> symbols = new HashMap<>();
		symbols.put(null, "some value");

		boolean containsNull = symbols.containsKey(null);
		Assert.assertTrue(containsNull);

	}

	// use combination of containsKey and get to get value out as put accepts null
	// values.
	@Test
	public void containsKey3() {
		Map<String, String> symbols = new HashMap<>();
		symbols.put("some key", null);

		if (symbols.containsKey("some key")) {
			symbols.get("some key");
		}
	}

	@Test
	public void containsValue1() {
		Map<String, String> symbols = new HashMap<>();
		symbols.put("%", "Percent");
		symbols.put("*", "Asterisk");
		symbols.put("~", "Tilde");
		symbols.put("$", "Dollar");
		symbols.put("#", "Octothorpe");

		boolean containsAsterick = symbols.containsValue("Asterisk");
		Assert.assertTrue(containsAsterick);

		boolean containsOne = symbols.containsValue("1");
		Assert.assertFalse(containsOne);
	}

	@Test
	public void putAll() {
		Map<String, String> children = new HashMap<>();
		children.put("Jon", "Snow");
		children.put("Brandon", "Stark");
		children.put("Arya", "Stark");
		children.put("Sansa", "Stark");
		children.put("Rickon", "Stark");

		Map<String, String> elders = new HashMap<>();
		elders.put("Eddard", "Stark");
		elders.put("Catlyn", "Stark");
		elders.put("Lyanna", "Stark");
		elders.put("Benjen", "Stark");

		Map<String, String> houseStark = new HashMap<>();
		houseStark.putAll(children);
		houseStark.putAll(elders);

		Assert.assertEquals(9, houseStark.size());
	}

	@Test
	public void clear() {
		Map<String, String> children = new HashMap<>();
		children.put("Jon", "Snow");
		children.put("Brandon", "Stark");
		children.put("Arya", "Stark");
		children.put("Sansa", "Stark");
		children.put("Rickon", "Stark");

		Assert.assertEquals(5, children.size());

		children.clear();

		// everything is nulled out. but the table size remains the same.
		Assert.assertEquals(0, children.size());

	}

	@Test
	public void keySet1() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Set<Long> keySet = transactions.keySet();
//		3084133165970564918
//		7013460311305119709
//		3002995788216969326
//		6129339334756346255
		for (Long txnId : keySet) {
			System.out.println(txnId);
		}
	}

	@Test
	public void keySet2() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		transactions.put(null, txnDataSet.get(0));
		Set<Long> keySet = transactions.keySet();
//		3084133165970564918
//		7013460311305119709
//		3002995788216969326
//		6129339334756346255
//		null
		for (Long txnId : keySet) {
			System.out.println(txnId);
		}
	}

	@Test
	public void keySet3() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Set<Long> keySet = transactions.keySet();
		// dont remove it this way. use remove(K) method
		Iterator<Long> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

	@Test
	public void remove1() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Assert.assertEquals(txnDataSet.size(), transactions.size());

		long transactionId = txnDataSet.get(0).transactionId();
		Transaction txn = transactions.remove(transactionId);

		Assert.assertEquals(3, transactions.size());

	}

	@Test
	public void remove2() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		transactions.put(null, txnDataSet.get(0));
		Assert.assertEquals(5, transactions.size());
		Assert.assertTrue(transactions.containsKey(null));

		Transaction txn = transactions.remove(null);

		Assert.assertEquals(4, transactions.size());
	}

	@Test
	public void remove3() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Transaction txn = transactions.remove(Long.valueOf(1L));

		Assert.assertNull(txn);
	}

	@Test
	public void values() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Collection<Transaction> txns = transactions.values();
		txns.stream().forEach(txn -> System.out.println(txn.currency() + " " + txn.amount()));
	}

//	6908070615736267361 AUD 16.00
//	6640384187622978960 CAD 11.00
//	3329744428125366233 USD 25.00
//	3971869604345832907 USD 20.00
	@Test
	public void entrySet() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Set<Entry<Long, Transaction>> entrySet = transactions.entrySet();
		for (Entry<Long, Transaction> entry : entrySet) {
			Long txnId = entry.getKey();
			Transaction txn = entry.getValue();
			System.out.println(txnId + " " + txn.currency() + " " + txn.amount());
		}
	}

	@Test
	public void size() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		Assert.assertEquals(4, transactions.size());
	}

	@Test
	public void isEmpty() {
		Map<Long, Transaction> transactions = new HashMap<>();

		Assert.assertTrue(transactions.isEmpty());

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}

		Assert.assertFalse(transactions.isEmpty());
	}

	@Test
	public void equals1() {
		Map<String, String> children = new HashMap<>();
		children.put("Brandon", "Stark");
		children.put("Arya", "Stark");
		children.put("Sansa", "Stark");
		children.put("Rickon", "Stark");

		Map<String, String> elders = new HashMap<>();
		elders.put("Eddard", "Stark");
		elders.put("Catlyn", "Stark");
		elders.put("Lyanna", "Stark");
		elders.put("Benjen", "Stark");

		Assert.assertFalse(children.equals(elders));
	}

	@Test
	public void equals2() {
		Map<String, String> children = new HashMap<>();
		children.put("Brandon", "Stark");
		children.put("Arya", "Stark");
		children.put("Sansa", "Stark");
		children.put("Rickon", "Stark");

		Map<String, String> starkChildren = new HashMap<>(children);

		Assert.assertTrue(children.equals(starkChildren));
	}

	@Test
	public void hashCode1() {
		Map<Long, Transaction> transactions = new HashMap<>();

		List<Transaction> txnDataSet = Transactions.getDataSet();

		for (Transaction txn : txnDataSet) {
			transactions.put(txn.transactionId(), txn);
		}
		// hashcode 333740093
		Assert.assertTrue(transactions.hashCode() != 0);
	}

	@Test
	public void hashCode2() {
		Map<Long, Transaction> transactions = new HashMap<>();
		transactions.put(null, null);
		Assert.assertEquals(1, transactions.size());
		// hashcode 333740093
		Assert.assertTrue(transactions.hashCode() == 0);
	}

}
