package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ToCollectionTest {

    @Test
    public void getAllCollectorsMethods() {
        try {
            Class<Collectors> thisClass = Collectors.class;
            Method[] methods = thisClass.getDeclaredMethods();
            Set<String> set = new TreeSet<>();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].toString().startsWith("public static")) {

                    String[] split = methods[i].toString().split(" ");
                    set.add(split[split.length - 1].replace("java.util.stream.", ""));
                    //System.out.println(split[split.length - 1]);
                }
            }
            set.forEach(System.out::println);
        } catch (Throwable e) {
            System.err.println(e);
        }

    }

    @Test
    public void toCollectionLinkedHashSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertTrue(result instanceof LinkedHashSet);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionTreeSet() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Long> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .map(Transaction::transactionId)
                .collect(Collectors.toCollection(TreeSet::new));

//		Set<Transaction> result = dataSet
//				.stream()
//				.filter(txn -> txn.country() == CountryCode.US)
//				.collect(Collectors.toCollection(TreeSet::new));

        System.out.println("\nOutput::");
        result.forEach(System.out::println);

        Assert.assertTrue(result instanceof TreeSet);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionTreeSetReverseOrder() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Set<Long> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .map(Transaction::transactionId)
                .collect(
                        Collectors.toCollection(() -> new TreeSet<Long>(Comparator.reverseOrder())));

        System.out.println("\nOutput::");
        result.forEach(System.out::println);

        Assert.assertTrue(result instanceof TreeSet);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionPriorityQueue() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        PriorityQueue<Long> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .map(Transaction::transactionId)
                .collect(Collectors.toCollection(PriorityQueue::new));

        System.out.println("\nOutput::");
        result.forEach(System.out::println);

        Assert.assertTrue(result instanceof PriorityQueue);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionPriorityQueueReverseOrder() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        PriorityQueue<Long> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .map(Transaction::transactionId)
                .collect(
                        Collectors.toCollection(() -> new PriorityQueue<Long>(Comparator.reverseOrder())));
        System.out.println("\nOutput::");
        result.forEach(System.out::println);

        Assert.assertTrue(result instanceof PriorityQueue);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void toCollectionArrayListWithCapacity() {
        List<Transaction> dataSet = Transactions.getDataSet();
        System.out.println("Input::");
        dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        List<Transaction> result = dataSet
                .stream()
                .filter(txn -> txn.country() == CountryCode.US)
                .collect(
                        Collectors.toCollection(() -> new ArrayList<>(20)));

        System.out.println("\nOutput::");
        result.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country()));

        Assert.assertTrue(result instanceof ArrayList);
        Assert.assertEquals(2, result.size());
    }

}
