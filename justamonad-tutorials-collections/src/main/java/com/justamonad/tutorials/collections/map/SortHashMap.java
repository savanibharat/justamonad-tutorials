package com.justamonad.tutorials.collections.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortHashMap {

	public Map<String, Integer> inputMap() {
		Map<String, Integer> map = new HashMap<>();
		map.put("Sansa", 12);
		map.put("Arya", 16);
		map.put("Jon", 19);
		map.put("Robb", 23);
		map.put("Bran", 9);
		map.put("Rickon", 5);
		return map;
	}

	public void treeMap() {
		Map<String, Integer> sortedMap = new TreeMap<>(inputMap());
		sortedMap.forEach((k, v) -> System.out.println("key = " + k + " : Value = " + v));
	}

	public void treeMapPutAll() {
		Map<String, Integer> sortedMap = new TreeMap<>();
		sortedMap.putAll(inputMap());
		sortedMap.forEach((k, v) -> System.out.println("key = " + k + " : Value = " + v));
	}

	public void collectionsSort() {
		Map<String, Integer> inputMap = inputMap();
		List<String> keys = new ArrayList<>(inputMap().keySet());
		Collections.sort(keys);

		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		for (String key : keys) {
			sortedMap.put(key, inputMap.get(key));
		}
		sortedMap.forEach((k, v) -> System.out.println("key = " + k + " : Value = " + v));
	}

	@SuppressWarnings("hiding")
	public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {

		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

}
