package com.justamonad.tutorials.leetcode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals56 {

    public static void main(String[] args) {
        MergeIntervals56 m = new MergeIntervals56();
        System.out.println(Arrays.deepToString(m.merge(new int[][]{{1, 3}, {3, 5}, {5, 7}})));
    }

    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (interval1, interval2) -> Integer.compare(interval1[0], interval2[0]));

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        int index = 0;
        while (index < intervals.length) {
            int[] previousInterval = list.get(list.size() - 1);
            int[] currInterval = intervals[index];
            if (previousInterval[1] >= currInterval[0]) {
                // Input: [[1,4],[2,3]]
                // Output:[[1,4]]
                previousInterval[1] = Math.max(previousInterval[1], currInterval[1]);
            } else {
                list.add(currInterval);
            }
            index++;
        }

        return list.toArray(new int[list.size()][2]);

        // int[][] result = new int[list.size()][2];
        // int k = 0;
        // for(int[] interval : list) {
        // result[k++] = interval;
        // }
        // return result;
    }

}
