package com.justamonad.tutorials.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.neovisionaries.i18n.CountryCode;

public class Transactions {

	public static List<Transaction> getDataSet() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(getUS1Txn());
		transactions.add(getUS2Txn());
		transactions.add(getCAD1Txn());
		transactions.add(getCAD2Txn());
		return transactions;

	}

	private static Transaction getUS1Txn() {
		return Transaction.of(CountryCode.US.getAlpha3(),
				Invoice.of(LocalDate.now().minusDays(1),
								Arrays.asList(
										Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.valueOf(10)), "12345"),
								Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.valueOf(15)), "12345"))));
	}

	private static Transaction getUS2Txn() {
		return Transaction.of(CountryCode.US.getAlpha3(),
				Invoice.of(LocalDate.now().minusDays(5),
						Arrays.asList(Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN), "12345"),
								Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN), "12345"))));
	}

	private static Transaction getCAD1Txn() {
		return Transaction.of(CountryCode.CA.getAlpha3(),
				Invoice.of(LocalDate.now().minusDays(1),
						Arrays.asList(Item.of("Toy", Money.of(CurrencyUnit.CAD, BigDecimal.ONE), "12345"),
								Item.of("Toy", Money.of(CurrencyUnit.CAD, BigDecimal.TEN), "12345"))));
	}

	private static Transaction getCAD2Txn() {
		return Transaction.of(CountryCode.AU.getAlpha3(),
				Invoice.of(LocalDate.now().plusDays(4),
						Arrays.asList(Item.of("Toy", Money.of(CurrencyUnit.AUD, BigDecimal.TEN), "12345"),
								Item.of("Toy", Money.of(CurrencyUnit.AUD, BigDecimal.valueOf(6)), "12345"))));
	}

}
