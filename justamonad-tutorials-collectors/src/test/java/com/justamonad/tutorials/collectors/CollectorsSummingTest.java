package com.justamonad.tutorials.collectors;

import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class CollectorsSummingTest {

	@Test
	public void summingInt() {
		
		Integer sum = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.summingInt(Money::getAmountMajorInt));
		
		System.out.println(sum);
		
	}
	
	@Test
	public void summingLong() {
		
		Long sum = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.summingLong(Money::getAmountMajorLong));
		
		System.out.println(sum);
		
	}
	
	@Test
	public void summingDouble() {
		
		Double sum = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.summingDouble(money -> money.getAmountMajor().doubleValue()));
		
		System.out.println(sum);

	}

}
