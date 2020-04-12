package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public class InvoiceValidator implements ValidatorFunction {

	@Inject
	private ValidatorErrorBeans validatorErrorBeans;

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		if (transaction != null && transaction.invoice() == null) {
			return validatorErrorBeans.noInvoice();
		}
		if (transaction != null && transaction.invoice() != null 
				&& transaction.invoice().invoiceTotal() == null) {
			return validatorErrorBeans.noAmount();
		}
		return Collections.emptyList();
	}

}
