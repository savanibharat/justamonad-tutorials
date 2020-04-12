package com.justamonad.tutorials.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.justamonad.tutorials.common.Customer;
import com.justamonad.tutorials.common.EmailAddress;
import com.justamonad.tutorials.common.Invoice;
import com.justamonad.tutorials.common.Item;
import com.justamonad.tutorials.common.Merchant;
import com.justamonad.tutorials.common.Name;
import com.justamonad.tutorials.common.Transaction;

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
