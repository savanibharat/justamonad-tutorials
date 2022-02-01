package com.justamonad.tutorials.collectors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Transaction;
import com.justamonad.tutorials.common.Transactions;

public class JoiningTest {

	@Test
	public void joiningWithoutStream() {

		List<Transaction> txns = Transactions.getDataSet();

		StringBuilder sb = new StringBuilder();

		Set<String> itemsNames = new HashSet<>();

		for (Transaction transaction : txns) {
			Invoice invoice = transaction.invoice();
			for (Item item : invoice.items()) {
				itemsNames.add(item.itemName());
			}
		}

		for (String itemName : itemsNames) {
			sb.append(itemName);
		}

		System.out.println(sb.toString());
	}

	@Test
	public void joining() {
		List<Transaction> txns = Transactions.getDataSet();

		String items = txns
				.stream()
				.map(Transaction::invoice)
				.flatMap(inv -> inv.items().stream())
				.map(Item::itemName)
				.distinct()
				.collect(Collectors.joining());

		System.out.println(items);

	}

	@Test
	public void joiningWithDelimiterWithoutStream() {
		List<Transaction> txns = Transactions.getDataSet();

		final String delimiter = ", ";

		StringBuilder sb = new StringBuilder();

		Set<String> itemsNames = new HashSet<>();

		for (Transaction transaction : txns) {
			Invoice invoice = transaction.invoice();
			for (Item item : invoice.items()) {
				itemsNames.add(item.itemName());
			}
		}

		Iterator<String> iterator = itemsNames.iterator();
		if (iterator.hasNext()) {
			sb.append(iterator.next());
			while (iterator.hasNext()) {
				sb.append(delimiter);
				sb.append(iterator.next());
			}
		}

		System.out.println(sb.toString());

	}

	@Test
	public void joiningWithDelimiter() {
		List<Transaction> txns = Transactions.getDataSet();

		String items = txns
				.stream()
				.map(Transaction::invoice)
				.flatMap(inv -> inv.items().stream())
				.map(Item::itemName)
				.distinct()
				.collect(Collectors.joining(", "));

		System.out.println(items);

	}

	@Test
	public void joiningWithPrefixDelimiterSuffixWithoutStream() {
		List<Transaction> txns = Transactions.getDataSet();

		final String delimiter = ", ";
		final String prefix = "{";
		final String suffix = "}";

		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		Set<String> itemsNames = new HashSet<>();

		for (Transaction transaction : txns) {
			Invoice invoice = transaction.invoice();
			for (Item item : invoice.items()) {
				itemsNames.add(item.itemName());
			}
		}

		Iterator<String> iterator = itemsNames.iterator();
		if (iterator.hasNext()) {
			sb.append(iterator.next());
			while (iterator.hasNext()) {
				sb.append(delimiter);
				sb.append(iterator.next());
			}
		}
		sb.append(suffix);
		System.out.println(sb.toString());

	}

	@Test
	public void joiningWithPrefixDelimiterSuffix() {
		List<Transaction> txns = Transactions.getDataSet();

		String items = txns
				.stream()
				.map(Transaction::invoice)
				.flatMap(inv -> inv.items().stream())
				.map(Item::itemName)
				.distinct()
				.collect(Collectors.joining(", ", "{", "}"));

		System.out.println(items);
	}

}
