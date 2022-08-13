package com.justamonad.tutorials.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;

public class FluxOperationThenManyTest {

    @Test
    public void thenManyTest() {
        Flux<Integer> integers = Flux.just(1, 2, 3).log();
        Flux<String> strings = Flux.just("a", "b", "c").log();

        Flux<String> thenMany = integers.thenMany(strings);

        StepVerifier.create(thenMany).expectNext("a", "b", "c").verifyComplete();
    }

}
