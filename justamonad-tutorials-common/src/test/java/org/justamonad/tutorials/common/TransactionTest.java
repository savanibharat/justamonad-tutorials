package org.justamonad.tutorials.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void test() {
		Transaction s = Transaction.of(
				Customer.of(EmailAddress.of("abc@gmail.com"), Name.of("John", "Doe")), 
				new Merchant(), 
				Invoice.of(LocalDate.now(), Arrays.asList(Item.of("Toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN), "12345"))));
		System.out.println(s);
	}

}
