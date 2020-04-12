package org.justamonad.tutorials.leetcode;

public class CountAndSay {

	public static void main(String[] args) {
		CountAndSay c = new CountAndSay();
		c.countAndSay(5);
	}

	String countAndSay(int n) {

		if (n == 1) {
			return "1";
		}

		StringBuilder sb = new StringBuilder("1");

		for (int i = 1; i < n; i++) {
			sb = countAndSay(i, sb);
			System.out.println(sb);
		}

		return sb.toString();
	}

	private StringBuilder countAndSay(int val, StringBuilder sb) {
		if (val == 1) {
			return sb.append("1");
		}
		StringBuilder nsb = new StringBuilder();
		int count = 1;
		for (int i = 0; i < sb.length(); i++) {
			while (i < sb.length() - 1 && sb.charAt(i) == sb.charAt(i + 1)) {
				count++;
				i++;
			}
			nsb.append(count).append(sb.charAt(i));
			count = 1;
		}
		return nsb;
	}

}
