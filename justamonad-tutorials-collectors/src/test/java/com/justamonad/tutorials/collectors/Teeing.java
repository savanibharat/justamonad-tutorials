package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import io.vavr.Tuple2;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Teeing {

    @Test
    public void teeingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<String, Transaction> result = dataSet.stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.teeing(
                                Collectors.minBy(Comparator.comparing(txn -> txn.amount())),
                                Collectors.minBy(Comparator.comparing(txn -> txn.amount())),
                                (min, max) -> Map.ofEntries(
                                        Map.entry("MIN", min.get()),
                                        Map.entry("MAX", max.get()))));

        System.out.println(result);
    }

    @Test
    public void everyCountryMinMaxTeeingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Tuple2<String, Transaction>>> collect = dataSet.stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.teeing(
                                        Collectors.minBy(Comparator.comparing(txn -> txn.amount())),
                                        Collectors.minBy(Comparator.comparing(txn -> txn.amount())),
                                        (min, max) -> List.of(new Tuple2<>("MIN", min.get()),
                                                new Tuple2<>("MAX", max.get())))));

        collect.forEach((k, v) -> System.out.println(
                k + " " + v.stream().map(tuple2 -> tuple2._1 + " " + tuple2._2.amount()).collect(Collectors.toList())));
    }

}
