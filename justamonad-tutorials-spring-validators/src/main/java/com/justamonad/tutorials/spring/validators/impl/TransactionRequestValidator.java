package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.RequestValidationException;
import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public final class TransactionRequestValidator {

	private final List<ValidatorFunction> validatorFunctions;
	
	@Inject
	public TransactionRequestValidator(List<ValidatorFunction> validatorFunctions) {
		this.validatorFunctions = validatorFunctions;
	}

	public void validate(Transaction transaction) {

		List<ErrorData> errorDatas = validatorFunctions
				.stream()
				.map(validatorFunction -> validatorFunction.apply(transaction))
				.filter(errors -> !errors.isEmpty())
				.findFirst()
				.get();
		
		if (!errorDatas.isEmpty()) {
			throw new RequestValidationException(errorDatas.toString());
		}

	}

}
