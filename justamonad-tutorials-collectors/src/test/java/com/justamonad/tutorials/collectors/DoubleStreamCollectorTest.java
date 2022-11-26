package com.justamonad.tutorials.collectors;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class DoubleStreamCollectorTest {

    @Test
    public void collectorTest1() {
        List<Double> numbers = DoubleStream
                .of(1, 2, 3, 4, 5)
                .collect(ArrayList::new, List::add, List::addAll);

        System.out.println(numbers);
    }

    @Test
    public void collectorTest2() {
        List<Double> numbers = DoubleStream
                .of(1, 2, 3, 4, 5) // double to Double
                .boxed()
                .collect(Collectors.toList());

        System.out.println(numbers);
    }

}
