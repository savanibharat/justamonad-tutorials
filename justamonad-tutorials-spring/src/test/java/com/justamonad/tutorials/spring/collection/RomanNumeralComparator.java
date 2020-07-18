package com.justamonad.tutorials.spring.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RomanNumeralComparator implements Comparator<String> {

	private static Map<Character, Integer> ROMAN_NUMERAL_CONVERTER = Collections.emptyMap();

	static {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		ROMAN_NUMERAL_CONVERTER = Collections.unmodifiableMap(map);
	}

	/**
	 * Implement custom comparison.
	 */
	@Override
	public int compare(String name1, String name2) {

		String[] roman1 = name1.split(" ");
		String[] roman2 = name2.split(" ");
		int compare = 0;
		if (roman1[0].compareTo(roman2[0]) == 0) {
			int romanNumeralToInt1 = romanNumeralToInt(roman1[1]);
			int romanNumeralToInt2 = romanNumeralToInt(roman2[1]);

			compare = (romanNumeralToInt1 == romanNumeralToInt2 ? 0
					: (romanNumeralToInt1 > romanNumeralToInt2 
					? 1 : -1));

		} else {
			compare = roman1[0].compareTo(roman2[0]);
		}
		return compare;
	}

	/**
	 * Idea here is to iterate roman numeral from right to left. If we move from
	 * left to right. <code>
	 * Name VIII
	 * Name IX
	 * </code>
	 * 
	 * <p>
	 * For above example if we move from left to right comparison for roman numeral
	 * than we mistake IX as smaller than VIII. So move from higher index to lower
	 * index.
	 * </p>
	 * 
	 * Just convert the numeral to integer value and compare by int.
	 */
	private int romanNumeralToInt(String roman) {
		char[] arr = roman.toCharArray();

		int total = 0;
		int maxNumeral = 0;
		for (int i = arr.length - 1; i >= 0; i--) {

			// if it doesn't contain the key in map then input is not valid.
			if (!ROMAN_NUMERAL_CONVERTER.containsKey(arr[i])) {
				throw new IllegalArgumentException("Input is not valid.");
			}

			int val = ROMAN_NUMERAL_CONVERTER.get(arr[i]);
			if (val >= maxNumeral) {
				maxNumeral = val;
				total = total + val;
			} else {
				total = total + val;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		RomanNumeralComparator romanComparator = new RomanNumeralComparator();

		String[] str = { "Henry II", "Edward VII", "Henry I", "Edward X", "Bharat I", "Bharat L" };
		Arrays.sort(str, romanComparator);
//		Set<String> kingName = new TreeSet<>(romanComparator);
//		kingName.add("Henry II");
//		kingName.add("Edward VII");
//		kingName.add("Henry I");
//		kingName.add("Edward X");
//		kingName.add("Bharat I");
//		kingName.add("Bharat L");
//[Bharat I, Bharat L, Edward VII, Edward X, Henry I, Henry II]

		System.out.println(Arrays.toString(str));
	}
}
