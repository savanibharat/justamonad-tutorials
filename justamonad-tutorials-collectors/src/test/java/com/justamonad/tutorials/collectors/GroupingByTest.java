package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class GroupingByTest {

    @Test
    public void groupingByMaxCurrencyTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Optional<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.reducing(
                                        BinaryOperator.maxBy(
                                                Comparator.comparing(txn -> txn.invoice().invoiceTotal().getAmount())))));

        System.out.println("\nOutput::");
        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByMinCurrencyTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Optional<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.reducing(
                                        BinaryOperator.maxBy(
                                                Comparator.comparing(txn -> txn.invoice().invoiceTotal().getAmount())))));

        System.out.println("\nOutput::");
        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Transaction>> txnsByCountry = dataSet
                .stream()
                .collect(Collectors.groupingBy(Transaction::country));

        System.out.println("\nOutput::");
        txnsByCountry.forEach((k, v) -> System.out.println(k + "" +
                v.stream().map(Transaction::transactionId).collect(Collectors.toList())));
    }

    @Test
    public void groupingByCountryAndCountTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                            Transaction::country,
                            Collectors.counting()));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByCountryAndTotalValuedTransactionsByCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Double> totalTxnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                            Transaction::country,
                            Collectors.summingDouble(txn -> txn.amount().doubleValue())));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByCountryAndCountUsingSupplierTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Long> totalTxnsByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                            Transaction::country,
                            TreeMap::new,
                            Collectors.counting()));

        System.out.println("\nOutput::");
        System.out.println(totalTxnsByCountry);
    }

    @Test
    public void groupingByCountryAndCountUsingSupplierAndMappingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Long>> collect = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                            Transaction::country,
                            TreeMap::new,
                            Collectors.mapping(
                                    Transaction::transactionId,
                                    Collectors.toList())));

        System.out.println("\nOutput::");
        System.out.println(collect);
    }

}
