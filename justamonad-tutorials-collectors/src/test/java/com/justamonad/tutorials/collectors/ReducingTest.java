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

//https://stackabuse.com/guide-to-java-8-collectors-reducing/
//https://www.logicbig.com/how-to/code-snippets/jcode-java-8-streams-collectors-reducing.html
//Collectors.reducing() was created to be used with groupingBy() and partitioningBy() for multilayered grouping.
//https://nicksamoylov.com/java/java-streams-38-collect-14-collectors-reducing-collector/
public class ReducingTest {

    @Test
    public void reducingByGettingLowestValueTxnTest() {
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

    @Test
    public void reducingByGettingLowestValueUsingIdentityTxnTest() {
        int result = Stream.of(1, 2, 3, 4, 5)
//                .reduce(1, (val1, val2) -> val1 * val2);
		.collect(Collectors.reducing(1, (val1, val2) -> val1 * val2));

        System.out.println(result);
    }

}
