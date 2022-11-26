package com.justamonad.tutorials.collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.stream.Stream;

public class GuavaImmutableCollectionsTest {

    @Test
    public void immutableListTest() {
        ImmutableList<Integer> result = Stream.of(1, 2, 3, 4, 5)
                .collect(ImmutableList.toImmutableList());
        System.out.println(result);
    }

    @Test
    public void immutableSetTest() {
        ImmutableSet<Integer> result = Stream.of(1, 2, 3, 4, 5)
                .collect(ImmutableSet.toImmutableSet());
        System.out.println(result);
    }

    @Test
    public void immutableMapTest() {
        ImmutableMap<Integer, Double> result = Stream.of(1, 2, 3, 4, 5)
                .collect(ImmutableMap.toImmutableMap(k -> k, v -> Math.pow(v, 3)));
        System.out.println(result);
    }

}
