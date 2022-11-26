package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringTest {

    @Test
    public void groupingByAndFilteringCountryAndAmountTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Predicate<Transaction> p = txn -> txn.country() == CountryCode.US
                && txn.amount().compareTo(new BigDecimal(15)) > 0;

        Map<CountryCode, Long> totalTxnsOfCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.filtering(
                                        p,
                                        Collectors.counting())));

        System.out.println("\nOutput::");
        totalTxnsOfCountry.forEach((k, v) -> System.out.println(k + " " + v));
    }

}
