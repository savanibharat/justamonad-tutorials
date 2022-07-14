package com.justamonad.tutorials.collectors;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayListCollectorImplTest {

    @Test
    public void testIntegerList() {
        List<Integer> result = Stream
                .of(1, 2, 3, 4, 5)
                .collect(new ArrayListCollectorImpl<Integer>());

        Assert.assertEquals(5, result.size());
    }

    @Test
    public void testStringList() {
        List<String> result = Stream
                .of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
                .collect(new ArrayListCollectorImpl<String>());

        Assert.assertEquals(5, result.size());
    }

    @Test
    public void arrayListCollectorWithoutFinisherOrCharacteristics() {
        Supplier<List<Integer>> supplier = ArrayList::new;

        BiConsumer<List<Integer>, Integer> accumulator = (list, val) -> list.add(val);

        BinaryOperator<List<Integer>> combiner = (left, right) -> {
            left.addAll(right);
            return left;
        };

        Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(supplier, accumulator, combiner);

        List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(toList);

        Assert.assertEquals(5, result.size());
    }

    @Test
    public void arrayListCollectorWithoutFinisher() {
        Supplier<List<Integer>> supplier = ArrayList::new;

        BiConsumer<List<Integer>, Integer> accumulator = (list, val) -> list.add(val);

        BinaryOperator<List<Integer>> combiner = (left, right) -> {
            left.addAll(right);
            return left;
        };

        Characteristics characteristics = Characteristics.IDENTITY_FINISH;

        Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(
                supplier,
                accumulator,
                combiner,
                characteristics);

        List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(toList);

        Assert.assertEquals(5, result.size());
    }

    @Test
    public void collectorOfWithFinisher() {

        Supplier<List<Integer>> supplier = ArrayList::new;

        BiConsumer<List<Integer>, Integer> accumulator = (list, val) -> list.add(val);

        BinaryOperator<List<Integer>> combiner = (left, right) -> {
            left.addAll(right);
            return left;
        };

        Function<List<Integer>, List<Integer>> finisher = Function.identity();

        Characteristics characteristics = Characteristics.IDENTITY_FINISH;

        Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(supplier, accumulator, combiner,
                finisher, characteristics);

        List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(toList);

        Assert.assertEquals(5, result.size());
    }

}
