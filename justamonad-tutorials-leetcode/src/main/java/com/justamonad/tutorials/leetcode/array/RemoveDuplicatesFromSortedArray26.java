package com.justamonad.tutorials.leetcode.array;

public class RemoveDuplicatesFromSortedArray26 {

	public int removeDuplicates(int[] nums) {
		int left = 0;
		for (int right = 1; right < nums.length; right++) {
			if (nums[left] != nums[right]) {
				left++;
				nums[left] = nums[right];
			}
		}
		return left + 1;
	}

	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray26 r = new RemoveDuplicatesFromSortedArray26();
		int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int k = r.removeDuplicates(nums);
		for(int i = 0; i < k; i ++) {
			System.out.println(nums[i]);
		}
	}

}
