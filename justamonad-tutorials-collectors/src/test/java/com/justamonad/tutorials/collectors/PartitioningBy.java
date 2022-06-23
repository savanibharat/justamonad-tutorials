package com.justamonad.tutorials.collectors;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {

    public static void main(String[] args) {
        partitioningByDemo();
    }

    static void partitioningByDemo() {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Map<Boolean, Integer> result =
                Arrays
                        .stream(a) 	// IntStream
                        .boxed()	// Stream<Integer>
                        .collect(
                                Collectors.partitioningBy(
                                        // Partition based on Predicate
                                        val -> val.intValue() % 2 == 0,
                                        // add values partitioned by Predicate
                                        Collectors.summingInt(Integer::intValue)));
        System.out.println(result);

    }

}
