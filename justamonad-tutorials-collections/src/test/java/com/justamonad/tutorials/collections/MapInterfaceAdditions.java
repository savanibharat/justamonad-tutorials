package com.justamonad.tutorials.collections;

import java.util.*;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;

@SuppressWarnings("unused")
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
        names.put("Jon", 3);

        Integer newValue = Integer.valueOf(100);
        Integer oldValue = names.replace("Jon", newValue);

        Assert.assertEquals(Integer.valueOf(3), oldValue);

        Assert.assertEquals(newValue, names.get("Jon"));

    }

    @Test
    public void replaceTest2() {

//		If the key doesn't exists then replace method returns null.

        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", null);

        Integer oldValue = names.replace("Ramsey", Integer.valueOf(100));

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

        boolean isReplaced = names.replace("Jon", 3, 100);

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

        boolean isReplaced = names.replace("Jon", 56, 100);

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

        Integer newVal = names.get("Jon");
        Assert.assertEquals(Integer.valueOf(100_000), newVal);
    }

    @Test
    public void putIfAbsentTest2() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);
        Assert.assertTrue(names.containsKey("Jon"));

        Integer oldValue = names.putIfAbsent("Jon", 100_000);
        Assert.assertEquals(Integer.valueOf(3), oldValue);
        Assert.assertEquals(Integer.valueOf(3), names.get("Jon"));
    }

    @Test
    public void putIfAbsentTest3() {

        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", null);
        Assert.assertTrue(names.containsKey("Jon"));

        Integer oldValue = names.putIfAbsent("Jon", 100_000);
        Assert.assertEquals(null, oldValue);
        Assert.assertEquals(Integer.valueOf(100_000), names.get("Jon"));

    }

    @Test
    public void removeTest1() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);

        names.remove("Jon", 3);

        Assert.assertFalse(names.containsKey("Jon"));
    }

    @Test
    public void removeTest2() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);

        names.remove("Jon", 123);

        Assert.assertTrue(names.containsKey("Jon"));
    }

    @Test
    public void computeIfAbsentTest() {
//		Map<String, Integer> names = new HashMap<>();
//		names.put("Bran", 4);
//		names.put("Jon", 3);

        int[][] arr = {{0, 1}, {1, 2}, {3, 4}, {1, 50}};

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] a : arr) {
            if (graph.containsKey(a[0])) {
                graph.get(a[0]).add(a[1]);
            } else {
                graph.put(a[0], new HashSet<>());
                graph.get(a[0]).add(a[1]);
            }
        }

        Map<Integer, Set<Integer>> graph_compute = new HashMap<>();

        for (int[] a : arr) {
            // if key exists then nothing is done. old value is returned. else new
            // value is computed and inserted in map.
            graph_compute.computeIfAbsent(a[0], k -> new HashSet<>()).add(a[1]);
            System.out.println(graph_compute);
        }
//        System.out.println(graph);
//        System.out.println(graph_compute);
    }

    @Test
    public void computeIfPresentTest() {
        //use this example
        // https://stackoverflow.com/a/24872936/2793109
        /**
         * Computing the word count of dictionary words in a novel.
         * Add few lines of a some novel online.
         * */

        String[] words = "this is a sentence this is bharat savani".split(" ");

        // gets all the words in dictionary.
        Map<String, Integer> frequency = new HashMap<>();

//        for(String str : words) {
//            dictionary.put(str, 0);
//        }

        for (String str : words) {
            if (!frequency.containsKey(str)) {
                frequency.put(str, 0);
            }
            frequency.put(str, frequency.get(str) + 1);
        }
        // words is List<String>
        for (String str : words) {
            if (!frequency.containsKey(str)) {
                frequency.put(str, 0);
            }
            frequency.computeIfPresent(str, (k, v) -> v + 1);
        }

        System.out.println(frequency);
    }

    @Test
    public void compute1Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);
        //3          3     4
        names.compute("Jon", (k, v) -> k.length() + v + "Snow".length());

        Assert.assertEquals(Integer.valueOf(10), names.get("Jon"));
    }

    @Test
    public void compute3Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);
        names.compute("Jon", (k, v) -> null);

        Assert.assertFalse(names.containsKey("Jon"));
    }

    @Test
    public void compute2Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);
        names.compute("Ramsey", (k, v) -> "Snow".length());

        Assert.assertEquals(Integer.valueOf(4), names.get("Ramsey"));
    }

    @Test
    public void merge1Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);

        names.merge("Bran", 10, (v1, v2) -> v1 + v2);
        Assert.assertEquals(Integer.valueOf(4 + 10), names.get("Bran"));
    }

    @Test
    public void merge2Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);

        names.merge("Bran", 1, (v1, v2) -> null);
        Assert.assertFalse(names.containsKey("Bran"));
    }

    @Test
    public void merge3Test() {
        Map<String, Integer> names = new HashMap<>();
        names.put("Bran", 4);
        names.put("Jon", 3);

        names.merge("Ramsey", 1, (v1, v2) -> v2);
        Assert.assertEquals(Integer.valueOf(1), names.get("Ramsey"));
    }

    @Test
    public void entryTest() {
        Entry<String, Integer> entry = Map.entry("Jon", 3);
    }

	@Test
    public void copyOfTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Bran", 4);
        map.put("Jon", 3);

        Map<String, Integer> names = Map.copyOf(map);

    }

    @Test
    public void ofEntriesTest() {
        Map<String, String> starks = Map.ofEntries(Map.entry("Jon", "Targaryen"), Map.entry("Arya", "Stark"),
                Map.entry("Sansa", "Stark"),
                Map.entry("Bran", "Stark"),
                Map.entry("Rickon", "Stark"));
    }

    @Test
    public void ofTest() {
        Map.of();
        Map.of("Jon", "Targaryen");
        Map.of("Jon", "Targaryen", "Arya", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark", "Rickard", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark", "Rickard", "Stark", "Lyarra", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark", "Rickard", "Stark", "Lyarra", "Stark", "Benjen", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark", "Rickard", "Stark", "Lyarra", "Stark", "Benjen", "Stark", "Catelyn", "Stark");
        Map.of("Jon", "Targaryen", "Arya", "Stark", "Sansa", "Stark", "Bran", "Stark", "Rickon", "Stark", "Rickard", "Stark", "Lyarra", "Stark", "Benjen", "Stark", "Catelyn", "Stark", "Eddard", "Stark");
    }

}
