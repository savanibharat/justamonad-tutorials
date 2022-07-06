package com.justamonad.tutorials.collectors.list;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToSetTest {

    //	toSet() method is used to collect the data from Stream into the Set. As of today the toSet() method returns
//	a Collector that uses HashSet. That means the data will be collected into an HashSet.
//	If you want provide your own implementation of Set then you need to use toCollection method.
//	Remember Set will filter out duplicate eleemnts.

    //	The result returned is mutable HashSet. Which means that the returned HashSet can be modified.
//	Below example collects the US country's transactions into the Set.
    @Test
    public void toSet() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toSet());

        System.out.println();
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());

    }

//	toUnmodifiableSet() method is was added to Collectors class in Java 10.
//	toUnmodifiableSet() method is used to return data in Set which cannot be modified. Or the returned Set
//	is immutable.
//	The result is NOT wrapped in Collections.unmodifiableSet(set). A little bit of history. As of Java 9 Set
//	interface was augmented with several static factory that returns immutable Set. [provide article line] Read about it here.
//	The result is first collected into HashSet. And the finisher function of Collector is defined as
//	set -> (Set<T>)Set.of(set.toArray())
//	Set.of methods copies all the elements from this set into new Set and new immutable Set is returned.

    //	If you are using Java 8 and want to convert to an unmodfiableSet then you need to use collectingAndThen method
//	collect(Collectors.toSet(), Collections::unmodfiableSet);
//	Below example collects the US country's transactions into the immutable Set.
    @Test
    public void toUnmodifiableSet() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableSet());

        System.out.println();
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());

    }

}
