package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class GroupingByConcurrentTest {

    @Test
    public void groupingByConcurrentCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(Transaction::country));

        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByConcurrentCountryAndCountTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::country,
                        Collectors.counting()));

        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndTotalValuedTransactionsByCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Double> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::country,
                        Collectors.summingDouble(txn -> txn.amount().doubleValue())));

        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndAverageValuedTransactionsByCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Double> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::country,
                        Collectors.averagingDouble(txn -> txn.amount().doubleValue())));

        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndCountUsingSupplierTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::country,
                        ConcurrentSkipListMap::new,
                        Collectors.counting()));

        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndCountUsingSupplierAndMappingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Long>> collect = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::country,
                        ConcurrentSkipListMap::new,
                        Collectors.mapping(Transaction::transactionId,
                                Collectors.toList())));

        System.out.println(collect);
    }

}
