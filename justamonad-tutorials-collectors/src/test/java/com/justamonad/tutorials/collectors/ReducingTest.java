package com.justamonad.tutorials.collectors;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.reducing;

//https://www.javabrahman.com/java-8/java-8-how-to-use-collectors-collectingandthen-method-with-examples/
public class ReducingTest {

	@Test
	public void reducingByGettingLowestValueTxnTest() {
		List<Transaction> dataSet = Transactions.getDataSet();
		System.out.println("Input::");
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

		Money money = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(Transaction::invoice)
				.map(Invoice::invoiceTotal)
				.collect(collectingAndThen(
						reducing(BinaryOperator.minBy(Money::compareTo)),
						val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

		System.out.println("\nOutput::");
		System.out.println(money);
	}
	
	@Test
	public void reducingByGettingHighestValueTxnTest() {
		List<Transaction> dataSet = Transactions.getDataSet();
		System.out.println("Input::");
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

		Money money = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(Transaction::invoice)
				.map(Invoice::invoiceTotal)
				.collect(collectingAndThen(
						reducing(BinaryOperator.maxBy(Money::compareTo)),
						val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

		System.out.println("\nOutput::");
		System.out.println(money);
	}
	
}
