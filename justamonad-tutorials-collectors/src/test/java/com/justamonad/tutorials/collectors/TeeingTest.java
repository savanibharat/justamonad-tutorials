package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import io.vavr.Tuple2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeeingTest {

    @Test
    public void teeingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<String, Transaction> result = dataSet.stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.teeing(
                                Collectors.minBy(Comparator.comparing(Transaction::amount)),
                                Collectors.maxBy(Comparator.comparing(Transaction::amount)),
                                (min, max) -> Map.ofEntries(
                                        Map.entry("MIN", min.get()), // Be careful about .get() on Optional.
                                        Map.entry("MAX", max.get()))));

        result.forEach((k, v)-> System.out.println(k + " " + v.amount()));
    }

    @Test
    public void everyCountryMinMaxTeeingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Tuple2<String, Transaction>>> collect = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.teeing(
                                        Collectors.minBy(Comparator.comparing(Transaction::amount)),
                                        Collectors.maxBy(Comparator.comparing(Transaction::amount)),
                                        (min, max) -> List.of(
                                                new Tuple2<>("MIN", min.get()), // Be careful about .get() on Optional.
                                                new Tuple2<>("MAX", max.get())))));

        System.out.println("\nOutput::");
        collect.forEach((k, v) -> System.out.println(
                k + " " + v.stream().map(tuple2 -> tuple2._1 + " " + tuple2._2.amount()).collect(Collectors.toList())));
    }

}
