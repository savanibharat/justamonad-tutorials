package com.justamonad.tutorials.leetcode.sorting;

import java.util.Arrays;
import java.util.LinkedList;

public class MergeIntervalsAddInterval57 {

    public static void main(String[] args) {
        int[][] arr = { { 1, 3 }, { 6, 9 } };
        int[] interval = { 2, 5 };
        System.out.println(Arrays.deepToString(insert(arr, interval)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        int index = 0;
        int length = intervals.length;

        LinkedList<int[]> output = new LinkedList<>();

        while (index < length && intervals[index][0] < newStart ) {
            output.add(intervals[index++]);
        }

        if (output.isEmpty() || output.getLast()[1] < newStart) {
            output.add(newInterval);
        } else {
            output.getLast()[1] = Math.max(output.getLast()[1], newEnd);
        }

        while (index < length) {
            int[] interval = intervals[index++];
            int start = interval[0], end = interval[1];
            if (output.getLast()[1] < start) {
                output.add(interval);
            } else {
                output.getLast()[1] = Math.max(output.getLast()[1], end);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }

}
