package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
				.filter(errors -> !errors.isEmpty())
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		
		if (!errorDatas.isEmpty()) {
			throw new RequestValidationException(errorDatas.toString());
		}

	}

}
