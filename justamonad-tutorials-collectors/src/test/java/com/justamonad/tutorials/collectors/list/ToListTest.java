package com.justamonad.tutorials.collectors.list;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ToListTest {

    //	toList() method is used to collect the data from Stream into the List. As of today the toList() method returns
//	a Collector that uses ArrayList. That means the data will be collected into an ArrayList.
//	If you want provide your own implementation of List then you need to use toCollection method. We will come to
//	that later in this article.

    //
//	One more thing, the result returned is mutable ArrayList. Which means that the returned ArrayList can be modified.
//	Below example collects the US country's transactions into the List.
    @Test
    public void toList() {

        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toList());

        System.out.println();
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());

    }

//	toUnmodifiableList() method is was added to Collectors class in Java 10.
//	toUnmodifiableList() method is used to return data in List which cannot be modified. Or the returned List
//	is immutable.
//	The result is NOT wrapped in Collections.unmodifiableList(list). A little bit of history. As of Java 9 List
//	interface was augmented with several static factory that returns immutable List. [provide article line] Read about it here.
//	The result is first collected into ArrayList. And the finisher function of Collector is defined as
//	list -> (List<T>)List.of(list.toArray())
//	List.of methods copies all the elements from this list into new List and new immutable List is returned.

    //	If you are using Java 8 and want to convert to an unmodfiableList then you need to use collectingAndThen method
//	collect(Collectors.toList(), Collections::unmodfiableList);
//	Below example collects the US country's transactions into the immutable List.
    @Test
    public void toUnmodifiableList() {
        List<Transaction> dataSet = Transactions.getDataSet();
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toUnmodifiableList());
        System.out.println();
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertEquals(2, result.size());
    }

}
