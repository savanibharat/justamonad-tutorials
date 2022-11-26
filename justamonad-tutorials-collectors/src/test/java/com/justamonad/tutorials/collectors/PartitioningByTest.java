package com.justamonad.tutorials.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningByTest {

    @Test
    public void partitioningEvenOdd() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, List<Integer>> result =
                Arrays
                    .stream(a)  // IntStream
                    .boxed()    // Stream<Integer>
                    .collect(
                        Collectors.partitioningBy(
                            val -> val % 2 == 0)); // Partition based on Predicate

        System.out.println("\nOutput::");
        System.out.println(result);
    }

    @Test
    public void partitioningCountOfEvenOdd() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, Long> result =
                Arrays
                    .stream(a)  // IntStream
                    .boxed()    // Stream<Integer>
                    .collect(
                            Collectors.partitioningBy(
                                // Partition based on Predicate
                                val -> val % 2 == 0,
                                // add values partitioned by Predicate
                                Collectors.counting()));

        System.out.println("\nOutput::");
        System.out.println(result);
    }

    @Test
    public void partitioningEvenOddAndSummingDemo() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, Integer> result =
                Arrays
                    .stream(a)    // IntStream
                    .boxed()    // Stream<Integer>
                    .collect(
                            Collectors.partitioningBy(
                                // Partition based on Predicate
                                val -> val % 2 == 0,
                                // add values partitioned by Predicate
                                Collectors.summingInt(Integer::intValue)));

        System.out.println("\nOutput::");
        System.out.println(result);
    }

    @Test
    public void partitioningEvenOddAndSummarizingIntDemo() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Boolean, IntSummaryStatistics> result =
                Arrays
                        .stream(a)    // IntStream
                        .boxed()    // Stream<Integer>
                        .collect(
                                Collectors.partitioningBy(
                                        // Partition based on Predicate
                                        val -> val % 2 == 0,
                                        // add values partitioned by Predicate
                                        Collectors.summarizingInt(Integer::intValue)));

        System.out.println("\nOutput::");
        System.out.println(result);
    }

}
