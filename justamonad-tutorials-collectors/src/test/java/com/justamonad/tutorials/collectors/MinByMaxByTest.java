package com.justamonad.tutorials.collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

import java.math.BigDecimal;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;
import com.neovisionaries.i18n.CountryCode;

//https://www.javabrahman.com/java-8/java-8-how-to-use-collectors-collectingandthen-method-with-examples/
public class MinByMaxByTest {

	@Test
	public void minByTest() {
		List<Transaction> dataSet = Transactions.getDataSet();
		System.out.println("Input::");
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

		Money money = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(Transaction::invoice)
				.map(Invoice::invoiceTotal)
				.collect(collectingAndThen(
						minBy(Money::compareTo), 
						val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

		System.out.println("\nOutput::");
		System.out.println(money);
	}
	
	@Test
	public void maxByTest() {
		System.out.println("Input::");
		List<Transaction> dataSet = Transactions.getDataSet();
		dataSet.forEach(val -> System.out.println(val.transactionId() + " :: " + val.country() + " :: " + val.amount()));

		Money money = dataSet
				.stream()
				.filter(txn -> txn.country() == CountryCode.US)
				.map(Transaction::invoice)
				.map(Invoice::invoiceTotal)
				.collect(collectingAndThen(
						maxBy(Money::compareTo), 
						val -> val.orElseGet(() -> Money.of(CurrencyUnit.USD, BigDecimal.ZERO))));

		System.out.println("\nOutput::");
		System.out.println(money);
	}
	
}
