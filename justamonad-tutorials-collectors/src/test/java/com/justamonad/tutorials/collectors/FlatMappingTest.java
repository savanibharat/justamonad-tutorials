package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlatMappingTest {

    @Test
    public void groupingByCountryAndMappingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<List<Item>>> itemsSoldByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                            Transaction::country,
                            Collectors.mapping(
                                    txn -> txn.invoice().items(),
                                    Collectors.toList())));

        System.out.println("\nOutput::");
        itemsSoldByCountry.forEach((k, v) -> System.out.println(
                k + " " + v.stream().flatMap(Collection::stream).map(Item::itemName).collect(Collectors.toSet())
        ));
    }

    @Test
    public void groupingByCountryAndFlatMappingTest() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

        Map<CountryCode, List<Item>> itemsSoldByCountry = dataSet
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::country,
                                Collectors.flatMapping(
                                        txn -> txn.invoice().items().stream(),
                                        Collectors.toList())));

        System.out.println("\nOutput::");
        itemsSoldByCountry.forEach((k, v) -> System.out.println(
                k + " " + v.stream().map(Item::itemName).collect(Collectors.toSet())
        ));
    }
}
