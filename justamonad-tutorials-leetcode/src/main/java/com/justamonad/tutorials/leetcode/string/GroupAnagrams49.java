package com.justamonad.tutorials.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams49 {

	private static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
			73, 79, 83, 89, 97, 101 };

	public List<List<String>> groupAnagramsUsingPrimeNumbers(String[] strs) {

		Map<java.math.BigInteger, List<String>> result = new HashMap<>();

		for (String str : strs) {
			java.math.BigInteger product = java.math.BigInteger.ONE;
			for (char ch : str.toCharArray()) {
				product = product.multiply(java.math.BigInteger.valueOf(PRIMES[ch - 'a']));
			}
			System.out.println(product);

			List<String> values = result.getOrDefault(product, new ArrayList<>());
			values.add(str);
			result.put(product, values);
		}
		return new ArrayList<>(result.values());
	}

	public static void main(String[] args) {

		String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(groupAnagrams(arr));
		System.out.println(groupAnagramsUsingSorting(arr));
		System.out.println(groupAnagramsUsingCollectors(arr));
	}

	/**
	 * <p>
	 * Time : O(N K log K). Outloop runs for O(N) and K is max length of string. so
	 * sorting time is k log K.
	 * </p>
	 * <p>
	 * Space : O(NK). Total content stored is N strings with K length.
	 * </p>
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String hash = hash(str);
			List<String> list = map.getOrDefault(hash, new ArrayList<>());
			list.add(str);
			map.put(hash, list);
		}
		return new ArrayList<>(map.values());
	}

	private static String hash(String str) {
		int[] arr = new int[26];
		for (char ch : str.toCharArray()) {
			arr[ch - 'a']++;
		}
		return Arrays.toString(arr);
	}

	/**
	 * <p>
	 * Time: O(NK). Total content stored is N strings with K length.
	 * </p>
	 * <p>
	 * Space: O(NK). Total content stored is N strings with K length.
	 * </p>
	 */
	public static List<List<String>> groupAnagramsUsingSorting(String[] strs) {
		Map<String, List<String>> groups = new HashMap<>();
		for (String s : strs) {
			String sortedString = sortString(s);
			List<String> list = groups.getOrDefault(sortedString, new ArrayList<>());
			list.add(s);
			groups.put(sortedString, list);
		}
		return new ArrayList<>(groups.values());
	}

	private static String sortString(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	public static List<List<String>> groupAnagramsUsingCollectors(String[] strs) {
		return new ArrayList<>(
				Arrays
					.stream(strs)
					.collect(
							Collectors.groupingBy(GroupAnagrams49::sortString)).values());
//			return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(GroupAnagrams49::hash)).values());
	}

}
