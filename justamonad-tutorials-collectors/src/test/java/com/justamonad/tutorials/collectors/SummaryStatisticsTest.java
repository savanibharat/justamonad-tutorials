package com.justamonad.tutorials.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class SummaryStatisticsTest {

	@Test
	public void summarizingIntTest() {
		IntSummaryStatistics stats = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.summarizingInt(Money::getAmountMajorInt));

		System.out.println("summingInt Output::" + stats);
		System.out.println();
	}
	
	@Test
	public void summarizingLongTest() {
		LongSummaryStatistics stats = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.CAD)
			.collect(Collectors.summarizingLong(Money::getAmountMajorLong));

		System.out.println("summingLong Output::" + stats);
		System.out.println();
	}
	
	@Test
	public void summarizingDoubleTest() {
		DoubleSummaryStatistics stats = Transactions.getDataSet()
			.stream()
			.map(Transaction::invoice)
			.flatMap(inv -> inv.items().stream())
			.map(Item::price)
			.peek(System.out::println)
			.filter(money -> money.getCurrencyUnit() == CurrencyUnit.USD)
			.collect(Collectors.summarizingDouble(money -> money.getAmountMajor().doubleValue()));

		System.out.println("summingDouble Output::" + stats);
		System.out.println();
	}

}
