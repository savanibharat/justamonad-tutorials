package com.justamonad.tutorials.spring.validators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import javax.inject.Inject;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
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
@ContextConfiguration(classes = { TestConfig.class })
public class TransactionTest {

	@Inject
	private TransactionRequestValidator transactionReqValidator;

	@Test
	public void testNullTransaction() {
		try {
			Transaction txn = Transaction.of(null, null, null);
			transactionReqValidator.validate(txn);
			// If below line is executed means validator didn't throw an
			// exception. It must throw an exception
			Assert.fail();
		} catch (RequestValidationException ex) {
			System.out.print("111 " + ex.getMessage());
			// If exception is thrown means validator threw exception which is
			// expected output.
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testValidTransaction() {
		try {
			Item item = Item.of("1", Money.of(CurrencyUnit.USD, BigDecimal.ONE));
			Invoice invoice = Invoice.of(LocalDate.now(), Collections.singletonList(item));
			Transaction txn = Transaction.of(null, null, invoice);
			transactionReqValidator.validate(txn);
			Assert.assertTrue(true);
		} catch (RequestValidationException ex) {
			System.out.print("111 " + ex.getMessage());
			Assert.fail();
		}
	}

	@Test
	public void testNullInvoice() {
		try {
			// Invoice is null
			Transaction txn = Transaction.of(null, null, null);
			transactionReqValidator.validate(txn);
			// If below line is executed means validator didn't throw an
			// exception. It must throw an exception
			Assert.fail();
		} catch (RequestValidationException ex) {
			// If exception is thrown means validator threw exception which is
			// expected output.
			Assert.assertTrue(true);
		}
	}

}