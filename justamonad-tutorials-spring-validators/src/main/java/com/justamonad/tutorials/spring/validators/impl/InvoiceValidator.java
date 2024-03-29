package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public final class InvoiceValidator implements ValidatorFunction {

	private final List<ErrorData> noInvoice;

	@Inject
	public InvoiceValidator(@Named("emptyInvoice") List<ErrorData> noInvoice) {
		this.noInvoice = noInvoice;
	}

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		if (transaction != null && transaction.invoice() == null) {
            return noInvoice;
        }
		return Collections.emptyList();
	}

}
