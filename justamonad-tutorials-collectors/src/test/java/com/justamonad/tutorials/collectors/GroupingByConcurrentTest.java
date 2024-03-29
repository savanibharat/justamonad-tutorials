package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class GroupingByConcurrentTest {

    @Test
    public void groupingByConcurrentCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(Transaction::country));

        System.out.println("\nOutput::");
        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByConcurrentCountryAndCountTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                            Transaction::country,
                            Collectors.counting()));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndTotalValuedTransactionsTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Double> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                            Transaction::country,
                            Collectors.summingDouble(txn -> txn.amount().doubleValue())));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByConcurrentCountryAndMaxInvoiceTransactionTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Optional<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Transaction::country,
                                Collectors.reducing(
                                        BinaryOperator.maxBy(
                                                Comparator.comparing(
                                                        txn -> txn.invoice().invoiceTotal().getAmount())))));

        System.out.println("\nOutput::");
        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByConcurrentCountryAndCountUsingSupplierTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        ConcurrentMap<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingByConcurrent(
                            Transaction::country,
                            ConcurrentSkipListMap::new,
                            Collectors.counting()));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

}
