package com.justamonad.tutorials.misc;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.misc.conditions.TransactionResourceImplWithFluentAPI;
import com.justamonad.tutorials.misc.conditions.TransactionResourceImplWithLazyEval;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class TransactionProcessorTest {

	@Inject
	private TransactionResourceImplWithLazyEval transactionResourceImpl;

	@Inject
	private TransactionResourceImplWithFluentAPI transactionResourceImplWithFluentAPI;

	@Test
	public void txnProcessorTest1() {
		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.setTransaction(null);
		try {
			transactionResourceImpl.process(transactionRequest);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void txnProcessorTest2() {
		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.setTransaction(null);
		try {
			transactionResourceImplWithFluentAPI.process(transactionRequest);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

	}

}