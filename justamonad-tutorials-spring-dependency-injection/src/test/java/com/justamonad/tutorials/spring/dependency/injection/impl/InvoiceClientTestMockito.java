package com.justamonad.tutorials.spring.dependency.injection.impl;

import java.math.BigDecimal;
import java.util.function.Function;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.ErrorHandler;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.InvoiceClientImpl;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceClientTestMockito {

	@InjectMocks
	private InvoiceClientImpl invoiceClientImpl;

	@Mock
	private WebTarget webTarget;

	@Mock
	private ErrorHandler errorHandler;

	@Mock
	private Function<String, WebTarget> clientFactory;

	@Mock
	private Invocation.Builder builder;

	@Mock
	private Response response;

	@Mock
	private Response.StatusType statusType;

	@Before
	public void setup() {
		Mockito.when(clientFactory.apply(ArgumentMatchers.anyString())).thenReturn(webTarget);
		Mockito.when(webTarget.request()).thenReturn(builder);
	}

	@Test
	public void testGetInvoiceSuccess() {
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.readEntity(InvoiceResponse.class)).thenReturn(get200Response());

		InvoiceResponse invoice = invoiceClientImpl.getInvoice("200");
		Assert.assertEquals("200", invoice.getInvoiceId());
		Assert.assertEquals(new BigDecimal("200.00"), invoice.getInvoiceTotal().getAmount());
	}

	private InvoiceResponse get200Response() {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		invoiceResponse.setInvoiceId("200");
		invoiceResponse.setInvoiceTotal(Money.of(CurrencyUnit.USD, new BigDecimal("200.00")));
		return invoiceResponse;
	}

}
