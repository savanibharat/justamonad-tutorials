package com.justamonad.tutorials.leetcode.array;

public class RemoveElement27 {

	public int removeElement(int[] nums, int val) {
		int left = 0;

		for (int right = 0; right < nums.length; right++) {
			if (nums[right] != val) {
				nums[left] = nums[right];
				left++;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
		int k = new RemoveElement27().removeElement(input, 2);
		for(int i = 0 ; i < k ; i++) {
			System.out.println(input[i]);
		}
	}

}
