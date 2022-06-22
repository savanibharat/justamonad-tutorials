package com.justamonad.tutorials.leetcode.array;

public class SortArrayByParity922 {

    public int[] sortArrayByParityIIExtraSpace(int[] nums) {
        int[] result = new int[nums.length];
        int evenIndex = 0;
        for (int val : nums) {
            if (val % 2 == 0) {
                result[evenIndex] = val;
                evenIndex = evenIndex + 2;
            }
        }
        int oddIndex = 1;
        for (int val : nums) {
            if (val % 2 != 0) {
                result[oddIndex] = val;
                oddIndex = oddIndex + 2;
            }
        }
        return result;
    }

    public int[] sortArrayByParityII(int[] nums) {
        int evenIndex = 0, oddIndex = nums.length - 1;

        while (evenIndex < nums.length && oddIndex >= 0) {
            // Skip all even numbers that are in the proper place.
            while (evenIndex < nums.length && nums[evenIndex] % 2 == 0)
                evenIndex = evenIndex + 2;

            // Skip all odd numbers that are in the proper place.
            while (oddIndex >= 0 && nums[oddIndex] % 2 != 0)
                oddIndex = oddIndex - 2;

            // If all indexes are covered then numbers are in the proper place.
            if (oddIndex < 0 || evenIndex >= nums.length) break;

            // If we reached at this point then there
            // exists even and odd number pair that need
            // to be placed in right place.
            int temp = nums[evenIndex];
            nums[evenIndex] = nums[oddIndex];
            nums[oddIndex] = temp;
        }
        return nums;
    }

}
