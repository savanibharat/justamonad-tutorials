package com.justamonad.tutorials.spring.validators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import javax.inject.Inject;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.spring.validators.api.Invoice;
import com.justamonad.tutorials.spring.validators.api.Item;
import com.justamonad.tutorials.spring.validators.api.RequestValidationException;
import com.justamonad.tutorials.spring.validators.api.Transaction;
import com.justamonad.tutorials.spring.validators.config.TestConfig;
import com.justamonad.tutorials.spring.validators.impl.TransactionRequestValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TransactionTest {

	@Inject
	private TransactionRequestValidator transactionRequestValidator;

	@Test(expected = RequestValidationException.class)
	public void testNullTransaction() {
		Transaction transaction = null;
		transactionRequestValidator.validate(transaction);
	}

	@Test(expected = RequestValidationException.class)
	public void testNullInvoice() {
		Invoice invoice = null;
		Transaction transaction = Transaction.of(null, null, invoice);
		transactionRequestValidator.validate(transaction);
	}

	@Test
	public void testValidItem() {
		Item item = Item.of("toy", Money.of(CurrencyUnit.USD, BigDecimal.TEN));
		Invoice invoice = Invoice.of(LocalDate.now(), Collections.singletonList(item));
		Transaction transaction = Transaction.of(null, null, invoice);
		transactionRequestValidator.validate(transaction);
	}
	
	@Test(expected = RequestValidationException.class)
	public void testItemPriceNull() {
		Item item = Item.of("toy", null);
		Invoice invoice = Invoice.of(LocalDate.now(), Collections.singletonList(item));
		Transaction transaction = Transaction.of(null, null, invoice);
		transactionRequestValidator.validate(transaction);
	}

}