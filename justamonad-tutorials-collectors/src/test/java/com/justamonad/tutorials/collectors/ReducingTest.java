package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors.reducing() was created to be used with groupingBy() and partitioningBy() for multi-layered grouping.
 * Instead of a Collector, we could also use Stream#reduce(...) to achieve similar results. The difference between
 * the two is more subtle.
 * A reduce operation creates a new value by combining two values immutably.
 * A collect operation, however, is working with accumulated objects in a mutable way and uses a finisher
 * to get the result.
 * Which one you should prefer depends on your requirements - considering the actual
 * intended purpose, performance considerations, etc.
 *
 * Reference: https://belief-driven-design.com/java-stream-collectors-explained-42f69943c64/
 */
public class ReducingTest {

    @Test
    public void factorialTest() {
        int result = Stream
                        .of(1, 2, 3, 4, 5)
                        .reduce(1, (a, b) -> a * b);
        System.out.println(result);
    }

    @Test
    public void reducingByGettingLowestValueTxnTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        // Map<CountryCode, Optional<Money>>
        Money money = dataSet
                        .stream()
                        .filter(txn -> txn.country() == CountryCode.US)
                        .map(Transaction::invoice)
                        .map(Invoice::invoiceTotal)
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.reducing(BinaryOperator.minBy(Money::compareTo)),
                                        val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

        System.out.println("\nOutput::");
        System.out.println(money);
    }

    @Test
    public void reducingByGettingHighestValueTxnTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Money money = dataSet
                        .stream()
                        .filter(txn -> txn.country() == CountryCode.US)
                        .map(Transaction::invoice)
                        .map(Invoice::invoiceTotal)
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.reducing(BinaryOperator.maxBy(Money::compareTo)),
                                        val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

        System.out.println("\nOutput::");
        System.out.println(money);
    }

}
