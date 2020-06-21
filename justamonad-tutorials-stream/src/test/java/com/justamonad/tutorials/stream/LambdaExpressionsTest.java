package com.justamonad.tutorials.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;

public class LambdaExpressionsTest {

	@Test
	public void test() {

		/**
		 * 
		 * () -> {} // No parameters.
		 * 
		 * () -> 42; // No parameters. 42 is expression body.
		 * 
		 * () -> {return 42;} // No parameters. Block body with return.
		 * 
		 * (int x) -> { // Single declared parameter with body. if(x % 2 == 0) {
		 * return true; } return false; }
		 * 
		 * (x) -> { // Single inferred parameter with body. if(x % 2 == 0) {
		 * return true; } return false; }
		 * 
		 * (int x) -> x + 1; // Single declared type. (x) -> x + 1; // Single
		 * inferred type.
		 * 
		 * (int x, int y) -> x + y; // Multiple inferred parameters with body.
		 * 
		 * (int x, y) -> x + y; // Error. Cannot mix inferred and declarede
		 * type.
		 * 
		 * 
		 */
	}

	/**
	 * abc
	 */
	@Test
	public void sortTxnsBeforeJava8() {
		List<Transaction> txns = Transactions.getDataSet();
		// Before Java 8
		Comparator<Transaction> TXN_TIME = new Comparator<Transaction>() {
			@Override
			public int compare(Transaction t1, Transaction t2) {
				return t1.date().compareTo(t2.date());
			}
		};
		txns.sort(TXN_TIME);
	}

	@Test
	public void sortTxnsLambdaExpr1() {
		List<Transaction> txns = Transactions.getDataSet();
		// Lambda expression with declared type
		Comparator<Transaction> TXN_TIME = (Transaction t1, Transaction t2) -> t1.date().compareTo(t2.date());
		txns.sort(TXN_TIME);
	}

	@Test
	public void sortTxnsLambdaExpr2() {
		List<Transaction> txns = Transactions.getDataSet();
		// Lambda expression with inferred type
		Comparator<Transaction> TXN_TIME = (t1, t2) -> t1.date().compareTo(t2.date());
		txns.sort(TXN_TIME);
	}

	/**
	 * Get all US transactions sorted by time.
	 */
	List<Transaction> preJava8(List<Transaction> transactions) {
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.country().equals(CountryCode.US)) {
				result.add(transaction);
			}
		}
		Comparator<Transaction> TXN_TIME = new Comparator<Transaction>() {
			@Override
			public int compare(Transaction t1, Transaction t2) {
				return t1.date().compareTo(t2.date());
			}
		};
		Collections.sort(transactions, TXN_TIME);
		return result;
	}

	List<Transaction> afterJava8(List<Transaction> transactions) {
		List<Transaction> result = transactions.stream().filter(txn -> txn.country().equals(CountryCode.US))
				.sorted((t1, t2) -> t1.date().compareTo(t2.date())).collect(Collectors.toList());
		return result;
	}

}
