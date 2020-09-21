package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.core.annotation.Order;

import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
@Order(2)
public class InvoiceValidator implements ValidatorFunction {

	private final List<ErrorData> noInvoice;

	@Inject
	public InvoiceValidator(@Named("emptyInvoice") List<ErrorData> noInvoice) {
		this.noInvoice = noInvoice;
	}

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		return transaction.invoice() == null ? noInvoice : Collections.emptyList();
	}

}
