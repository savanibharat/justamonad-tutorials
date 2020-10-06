package com.justamonad.tutorials.collectors;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class CollectorsAveragingTest {

	@Test
	public void summingInt() {
		
		Double average = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.averagingInt(Money::getAmountMajorInt));
		
		System.out.println(average);
		System.out.println();
	}
	
	@Test
	public void summingLong() {
		
		Double average = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.averagingLong(Money::getAmountMajorLong));
		
		System.out.println(average);
		System.out.println();
	}
	
	@Test
	public void summingDouble() {
		
		List<Transaction> txns = Transactions.getDataSet();
		Double average = txns
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.averagingDouble(money -> money.getAmountMajor().doubleValue()));
		
		System.out.println(average);
		System.out.println();

	}

}
