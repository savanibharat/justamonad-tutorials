package com.justamonad.tutorials.stream;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class StreamIterateTest {

	@Test
	public void testForEach() {
		List<Transaction> transactions = Transactions.getDataSet();
		for (Transaction transaction : transactions) {
			System.out.println(transaction.country().getAlpha3());
		}
		transactions.forEach(txn -> System.out.println(txn.country().getAlpha3()));
	}

	@Test
	public void testForEachConsumer() {
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.forEach(new Consumer<Transaction>() {
			@Override
			public void accept(Transaction transaction) {
				System.out.println(transaction.country().getAlpha3());
			}
		});
	}

	@Test
	public void testForEachLambda() {
		List<Transaction> transactions = Transactions.getDataSet();
		transactions.forEach(txn -> System.out.println(txn.country().getAlpha3()));
	}
	
	@Test
	public void testParallelStream() {
		List<Transaction> transactions = Transactions.getDataSet();
		BigDecimal total = 
				transactions
				.parallelStream()
				.map(Transaction::amount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total);
	}

}
