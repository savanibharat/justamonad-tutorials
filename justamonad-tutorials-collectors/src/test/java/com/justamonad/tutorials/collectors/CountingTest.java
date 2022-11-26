package com.justamonad.tutorials.collectors;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingTest {

    @Test
    public void wordFrequencyCountingTest() {

        List<String> words = List.of("abc", "xyz", "abc", "def", "xyf", "def", "abc", "abc");

        Map<String, Long> result = words
                                    .stream()
                                    .collect(
                                        Collectors.groupingBy(
                                            Function.identity(),
                                            Collectors.counting()));

        System.out.println(result);
    }

    @Test
    public void wordLengthCountingTest() {

        List<String> months = List.of("January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December");

        Map<Integer, Long> result = months
                .stream()
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.counting()));

        System.out.println(result);
    }
}
