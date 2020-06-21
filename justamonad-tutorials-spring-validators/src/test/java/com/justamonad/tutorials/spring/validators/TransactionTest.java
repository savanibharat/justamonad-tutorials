package com.justamonad.tutorials.spring.validators;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.spring.validators.api.RequestValidationException;
import com.justamonad.tutorials.spring.validators.api.Transaction;
//import com.justamonad.tutorials.spring.validators.config.TestConfig;
//import com.justamonad.tutorials.spring.validators.impl.TransactionRequestValidator;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestConfig.class })
public class TransactionTest {

//	@Inject
//	private TransactionRequestValidator transactionReqValidator;
//
//	@Test
//	public void testNullTransaction() {
//		try {
//			Transaction txn = null;
//			transactionReqValidator.validate(txn);
//			// If below line is executed means validator didn't throw an
//			// exception. It must throw an exception
//			Assert.fail();
//		} catch (RequestValidationException ex) {
//			System.out.print("111 "+ex.getMessage());
//			// If exception is thrown means validator threw exception which is
//			// expected output.
//			Assert.assertTrue(true);
//		}
//	}
//
//	@Test
//	public void testNullInvoice() {
//		try {
//			// Invoice is null
//			Transaction txn = Transaction.of(null, null, null);
//			transactionReqValidator.validate(txn);
//			// If below line is executed means validator didn't throw an
//			// exception. It must throw an exception
//			Assert.fail();
//		} catch (RequestValidationException ex) {
//			// If exception is thrown means validator threw exception which is
//			// expected output.
//			Assert.assertTrue(true);
//		}
//	}

}