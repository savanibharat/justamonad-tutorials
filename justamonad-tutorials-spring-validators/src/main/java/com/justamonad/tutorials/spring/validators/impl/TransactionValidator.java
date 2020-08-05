package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.justamonad.tutorials.spring.validators.api.Transaction;

final class TransactionValidator implements ValidatorFunction {

	@Inject
	private ValidatorErrorBeans validatorErrorBeans;

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		return transaction == null 
				? validatorErrorBeans.noTransaction() 
				: Collections.emptyList();
	}

}
