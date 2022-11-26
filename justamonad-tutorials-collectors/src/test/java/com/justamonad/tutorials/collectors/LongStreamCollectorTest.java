package com.justamonad.tutorials.collectors;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LongStreamCollectorTest {

    @Test
    public void collectorTest1() {
        List<Long> numbers = LongStream
                .of(1, 2, 3, 4, 5)
                .collect(ArrayList::new, List::add, List::addAll);

        System.out.println(numbers);
    }

    @Test
    public void collectorTest2() {
        List<Long> numbers = LongStream
                .of(1, 2, 3, 4, 5) //long to Long
                .boxed()
                .collect(Collectors.toList());

        System.out.println(numbers);
    }

}
