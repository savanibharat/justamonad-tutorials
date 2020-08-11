package com.justamonad.tutorials.spring.dependency.injection.api;

import org.joda.money.Money;

public class InvoiceResponse {

	private String invoiceId;

	private Money invoiceTotal;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Money getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(Money invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

}
