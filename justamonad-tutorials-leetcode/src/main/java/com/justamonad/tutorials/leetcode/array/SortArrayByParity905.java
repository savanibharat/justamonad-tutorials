package com.justamonad.tutorials.leetcode.array;

import java.util.Arrays;

/**
 * <pre>
 * Given an array nums of non-negative integers, return an array consisting of
 * all the even elements of nums, followed by all the odd elements of nums. You
 * may return any answer array that satisfies this condition.
 * 
 * Example 1:
 * 
 * Input: 
 * nums = [3,1,2,4] 
 * Output: [2,4,3,1] 
 * The outputs [4,2,3,1], [2,4,1,3],
 * and [4,2,1,3] would also be accepted.
 * 
 * Note: 
 * 1 <= nums.length <= 5000 
 * 0 <= nums[i] <= 5000
 * 
 * </pre>
 */
public final class SortArrayByParity905 {

	public int[] sortArrayByParity(int[] arr) {
		int oddIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				int temp = arr[i];
				arr[i] = arr[oddIndex];
				arr[oddIndex] = temp;
				oddIndex++;
			}
		}
		return arr;
	}

	public int[] sortArrayByParityExtraSpace(int[] arr) {
		int[] result = new int[arr.length];
		int resultIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				result[resultIndex] = arr[i];
				resultIndex++;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				result[resultIndex] = arr[i];
				resultIndex++;
			}
		}
		return result;
	}
	
	public int[] sortArrayByParityUsingStream(int[] arr) {
        return 
            Arrays
             .stream(arr)
             .boxed()
             .sorted((a, b) -> Integer.compare(a % 2, b % 2))
             .mapToInt(Integer::intValue)
             .toArray();
    }
	
	public int[] sortArrayByParityByNotMovingElementsThatAreAlreadyInPlace(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        
        while(i < j) {
            while(i < nums.length && nums[i] % 2 == 0) i++;
            while(j >= 0 && nums[j] % 2 != 0) j--;
            if(i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        
        return nums;        
    }

	public static void main(String[] args) {
		int[] result = new SortArrayByParity905().sortArrayByParity(new int[] { 30, 10, 20, 40 });
		System.out.println(Arrays.toString(result));
	}

}
