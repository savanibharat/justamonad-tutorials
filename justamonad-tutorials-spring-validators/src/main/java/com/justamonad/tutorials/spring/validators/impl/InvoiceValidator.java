package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.justamonad.tutorials.spring.validators.api.Transaction;

final class InvoiceValidator implements ValidatorFunction {

	@Inject
	private ValidatorErrorBeans validatorErrorBeans;

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		if (transaction.invoice() == null) {
			return validatorErrorBeans.noInvoice();
		}
		return Collections.emptyList();
	}

}
