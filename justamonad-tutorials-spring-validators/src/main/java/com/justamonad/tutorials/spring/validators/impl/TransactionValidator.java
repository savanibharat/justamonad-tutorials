package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.core.annotation.Order;

import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
@Order(1)
public final class TransactionValidator implements ValidatorFunction {

	private final List<ErrorData> noTransaction;

	@Inject
	public TransactionValidator(@Named("emptyTransaction") List<ErrorData> noTransaction) {
		this.noTransaction = noTransaction;
	}

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		return transaction == null ? noTransaction : Collections.emptyList();
	}

}
