package com.justamonad.tutorials.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements347 {

    public static void main(String[] args) {
        TopKFrequentElements347 k = new TopKFrequentElements347();
        k.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }

    public int[] topKFrequent(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return arr;
        }

//        Map<Integer, Long> frequency =
//                Arrays.stream(arr).boxed().collect(Collectors.groupingBy(val -> val, Collectors.counting()));
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
        }

        // Make queue based on frequency comparison
        Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(
                (entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            q.add(entry);
        }

        // get first k keys.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = q.poll().getKey().intValue();
        }

        return result;
    }

}
