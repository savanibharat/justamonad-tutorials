package com.justamonad.tutorials.object.mapping;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConstructorInjection {

	private final Transaction transaction;
	private final Invoice invoice;

	@Inject
	public ConstructorInjection(Transaction transaction, Invoice invoice) {
		this.transaction = transaction;
		this.invoice = invoice;
	}

	public void me() {
		transaction.txn();
		invoice.invoiceId();
	}

}
