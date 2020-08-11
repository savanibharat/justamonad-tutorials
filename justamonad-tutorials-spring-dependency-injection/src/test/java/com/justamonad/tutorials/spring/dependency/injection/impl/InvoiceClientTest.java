package com.justamonad.tutorials.spring.dependency.injection.impl;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.IInvoiceClient;
import com.justamonad.tutorials.spring.dependency.injection.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class InvoiceClientTest {

	@Inject
	private IInvoiceClient invoiceClient;

	@Test
	public void testGetInvoiceSuccess() {
		InvoiceResponse invoice = invoiceClient.getInvoice("200");
		Assert.assertEquals("200", invoice.getInvoiceId());
		Assert.assertEquals(new BigDecimal("200.00"), invoice.getInvoiceTotal().getAmount());
	}

	@Test
	public void testGetInvoiceClientError() {
		try {
			invoiceClient.getInvoice("400");
			Assert.fail();
		} catch (WebApplicationException e) {
			Assert.assertEquals(400, e.getResponse().getStatus());
		}
	}

	@Test
	public void testGetInvoiceServerError() {
		try {
			invoiceClient.getInvoice("500");
			Assert.fail();
		} catch (WebApplicationException e) {
			Assert.assertEquals(500, e.getResponse().getStatus());
		}
	}

}
