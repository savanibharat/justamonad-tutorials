package com.justamonad.tutorials.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.neovisionaries.i18n.CountryCode;

public class TransactionTest {

	@Test
	public void test() {
		Transaction txn = Transaction.of(CountryCode.US.getAlpha3(),
				Invoice.of(LocalDate.now(),
						Arrays.asList(Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN), "12345"),
								Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN), "12345"))));
		System.out.println(txn.amount());
		System.out.println(txn.currency());
		System.out.println(txn.invoice().date());
		System.out.println(txn.invoice().invoiceId().invoiceId());
	}

}
