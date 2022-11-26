package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MappingTest {

    @Test
    public void groupingByCountryAndMappingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, Set<Long>> collect = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.mapping(
                                        Transaction::transactionId,
                                        Collectors.toSet())));

        System.out.println("\nOutput::");
        System.out.println(collect);
    }

}
