package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilteringTest {

    @Test
    public void filteringCountryTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        List<Transaction> countryTransactions = dataSet
                .stream()
                .collect(
                        Collectors.filtering(
                                txn -> txn.country() == CountryCode.US,
                                Collectors.toList()));

        countryTransactions.forEach(txn -> System.out.println(txn.country() + " " + txn.transactionId()));
    }

    @Test
    public void groupingByAndFilteringCountryAndAmountTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<Transaction, Long> totalTxnsOfCountry = dataSet
                .stream()
                .collect(Collectors.groupingBy(
                            txn -> txn,
                            Collectors.filtering(
                                    txn -> txn.country() == CountryCode.US
                                            && txn.amount().compareTo(new BigDecimal(15)) > 0,
                                    Collectors.counting())));

        totalTxnsOfCountry.forEach((k, v) -> System.out.println(k.transactionId() + " " + v));
    }

}
