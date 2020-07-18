package com.justamonad.tutorials.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;

public class MapInterfaceAdditions {

	// Transactions.getDataSet().stream()
//	.collect(Collectors.toMap(Transaction::transactionId,Function.identity()));
	@Test
	public void getOrDefaultTest1() {

//		 If the key is present the value is returned. Value can be null if the mapping
//		 done in Map had null value for the key in question.

//		If value is not present then default value passed in parameter is returned.

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);
		
		Integer value = names.getOrDefault("Ramsey", -1);
		Assert.assertEquals(Integer.valueOf(-1), value);

	}

	@Test
	public void getOrDefaultTest2() {

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);
		names.put("Ramsey", null);

		Integer value = names.getOrDefault("Ramsey", -1);
		Assert.assertEquals(null, value);

	}

	void print(Map<Long, Transaction> map) {
		map.forEach((k, v) -> System.out.println(k + " " + v.country() + " " + v.date()));
	}

	@Test
	public void forEachTest() {
		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);

		names.forEach((k, v) -> System.out.println(k + " " + v));
	}

	@Test
	public void replaceTest1() {

//		If the key exists in Map it inserts new value of that key specified in parameter. 
//		In this case it returns previous value associated with the key. 
//		It can return null also if previous value set for this key was null.

//		If the key doesn't exists then replace method returns null.

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", null);

		Integer newValue = Integer.valueOf(100);
		Integer oldValue = names.replace("Jon", newValue);

		Assert.assertEquals(null, oldValue);

		Assert.assertEquals(newValue, names.get("Jon"));

	}

	@Test
	public void replaceTest2() {

//		If the key doesn't exists then replace method returns null.

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", null);

		Integer newValue = Integer.valueOf(100);
		Integer oldValue = names.replace("Ramsey", newValue);

		Assert.assertEquals(null, oldValue);

		Assert.assertEquals(oldValue, names.get("Ramsey"));

	}

	@Test
	public void replaceOldWithNewValueTest1() {

//		replace method is used to replace the value of the key provided that 
//		specified key and value in parameter exists in Map.
//		

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);

		Integer newValue = Integer.valueOf(100);

		boolean isReplaced = names.replace("Jon", 3, newValue);

		Assert.assertTrue(isReplaced);
	}

	@Test
	public void replaceOldWithNewValueTest2() {

//		replace method is used to replace the value of the key provided that 
//		specified key and value in parameter exists in Map.
//		

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);

		Integer newValue = Integer.valueOf(100);

		boolean isReplaced = names.replace("Jon", 56, newValue);

		Assert.assertFalse(isReplaced);
	}

	@Test
	public void replaceAllTest() {
		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);

		names.replaceAll((String k, Integer v) -> v * 5);

		names.forEach((k, v) -> System.out.println(k + " " + v));

	}

	@Test
	public void putIfAbsentTest1() {

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		Assert.assertFalse(names.containsKey("Jon"));

		Integer oldValue = names.putIfAbsent("Jon", 100_000);
		Assert.assertTrue(oldValue == null);
		Assert.assertTrue(names.containsKey("Jon"));

	}

	@Test
	public void putIfAbsentTest2() {

		Map<String, Integer> names = new HashMap<>();
		names.put("Bran", 4);
		names.put("Jon", 3);
		Assert.assertTrue(names.containsKey("Jon"));

		Integer oldValue = names.putIfAbsent("Jon", 100_000);
		Assert.assertEquals(Integer.valueOf(3), oldValue);

	}

	@Test
	public void removeTest() {

	}

	@Test
	public void computeIfAbsentTest() {

	}

	@Test
	public void computeIfPresentTest() {

	}

	@Test
	public void computeTest() {

	}

	@Test
	public void mergeTest() {

	}

	@Test
	public void ofEntriesTest() {

	}

	@Test
	public void entryTest() {

	}

	@Test
	public void copyOfTest() {

	}

	@Test
	public void ofTest() {

	}

}
