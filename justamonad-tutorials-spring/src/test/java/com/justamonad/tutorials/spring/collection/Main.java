package com.justamonad.tutorials.spring.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * <code>
 * Albert II
Polo IV
Alexander V
Elizabeth XXV
Albert XL
Polo XLVI
William IX
Edward XXXIX
Elizabeth XIX

Albert II
Albert XL
Alexander V
Edward XXXIX
Elizabeth XIX
Elizabeth XXV
Polo IV
Polo XLVI
William IX
 * </code>
 */
public class Main {

	public static void main(String[] args) {
		String[] names = { "Albert II", "Polo IV", "Alexander V", "Elizabeth XXV", "Albert XL", "Polo XLVI",
				"William IX", "Edward XXXIX", "Elizabeth XIX" };
		Arrays.stream(names).forEach(System.out::println);
		System.out.println();
		String[] sortedNames = sort(names);
		print(sortedNames);
	}

	private static String[] sort(String[] names) {
		TreeMap<String, TreeMap<Integer, Integer>> map = new TreeMap<>();
		for (int i = 0; i < names.length; i++) {
			String name = names[i].split(" ")[0];
			String number = names[i].split(" ")[1];
			int num = convertToRomanNumber(number);
			if (map.containsKey(name)) {
				map.get(name).put(num, i);
			} else {
				TreeMap<Integer, Integer> temp = new TreeMap<>();
				temp.put(num, i);
				map.put(name, temp);
			}
		}
		String[] answer = new String[names.length];
		int count = 0;
		for (String name : map.keySet()) {
			for (Integer number : map.get(name).keySet()) {
				answer[count] = names[map.get(name).get(number)];
				count++;
			}
		}
		return answer;
	}

	private static int convertToRomanNumber(String number) {
		HashMap<Character, Integer> roman = new HashMap<>();
		roman.put('I', 1);
		roman.put('V', 5);
		roman.put('X', 10);
		roman.put('L', 50);
		roman.put('C', 100);
		int sum = 0;
		int prevDigit = 0;
		int nowDigit = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			nowDigit = roman.get(number.charAt(i));
			if (nowDigit >= prevDigit) {
				sum = sum + nowDigit;
			} else {
				sum = sum - nowDigit;
			}
			prevDigit = nowDigit;
		}
		return sum;
	}

	private static void print(String[] sortedNames) {
		for (int i = 0; i < sortedNames.length; i++) {
			System.out.println(sortedNames[i]);
		}
	}
}