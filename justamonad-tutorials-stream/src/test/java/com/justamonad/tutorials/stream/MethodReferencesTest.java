package com.justamonad.tutorials.stream;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class MethodReferencesTest {

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
	public void lambdaExpr() {
		List<Transaction> txns = Transactions.getDataSet();
		Comparator<Transaction> TXN_TIME = (t1, t2) -> TransactionComparators.compareByTime(t1, t2);
		txns.sort((t1, t2) -> TransactionComparators.compareByAmount(t1, t2));
	}

	@Test
	public void lambdaExprReversed() {
		List<Transaction> txns = Transactions.getDataSet();
		Comparator<Transaction> TXN_TIME = (t1, t2) -> TransactionComparators.compareByTime(t1, t2);
		Comparator<Transaction> TXN_AMOUNT = (t1, t2) -> TransactionComparators.compareByTime(t1, t2);
		txns.sort(TXN_TIME.reversed());
		txns.sort((t1, t2) -> TransactionComparators.compareByAmount(t1, t2));
	}

	@Test
	public void methodReferences() {
		List<Transaction> txns = Transactions.getDataSet();
		txns.sort(TransactionComparators::compareByTime);
		txns.sort(TransactionComparators::compareByAmount);
	}

	private static class TransactionComparators {

		public static int compareByTime(Transaction t1, Transaction t2) {
			return t1.date().compareTo(t2.date());
		}

		public static int compareByAmount(Transaction t1, Transaction t2) {
			return t1.amount().compareTo(t2.amount());
		}
	}

}
