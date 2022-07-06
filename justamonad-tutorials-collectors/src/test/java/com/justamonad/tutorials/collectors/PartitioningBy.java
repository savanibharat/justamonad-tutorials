package com.justamonad.tutorials.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {

    @Test
    public void partitioningByDemo() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, List<Integer>> result =
                Arrays
                        .stream(a)    // IntStream
                        .boxed()    // Stream<Integer>
                        .collect(
                                Collectors.partitioningBy(
                                        // Partition based on Predicate
                                        val -> val.intValue() % 2 == 0));
        System.out.println(result);
    }

    @Test
    public void partitioningByAndCountingDemo() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, Long> result =
                Arrays
                        .stream(a)    // IntStream
                        .boxed()    // Stream<Integer>
                        .collect(
                                Collectors.partitioningBy(
                                        // Partition based on Predicate
                                        val -> val.intValue() % 2 == 0,
                                        // add values partitioned by Predicate
                                        Collectors.counting()));
        System.out.println(result);
    }

    @Test
    public void partitioningByAndSummingIntDemo() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, Integer> result =
                Arrays
                        .stream(a)    // IntStream
                        .boxed()    // Stream<Integer>
                        .collect(
                                Collectors.partitioningBy(
                                        // Partition based on Predicate
                                        val -> val.intValue() % 2 == 0,
                                        // add values partitioned by Predicate
                                        Collectors.summingInt(Integer::intValue)));
        System.out.println(result);
    }

}
