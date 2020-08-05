package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.RequestValidationException;
import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public final class TransactionRequestValidator {

	@Inject
	private List<ValidatorFunction> validatorFunctions;

	public void validate(Transaction transaction) {

		List<ErrorData> errorDatas = validatorFunctions
				.stream()
				.map(validatorFunction -> validatorFunction.apply(transaction))
				.filter(l -> l.size() > 0)
				.findFirst()
				.get();
		
		if (!errorDatas.isEmpty()) {
			throw new RequestValidationException(errorDatas.toString());
		}

	}

}
