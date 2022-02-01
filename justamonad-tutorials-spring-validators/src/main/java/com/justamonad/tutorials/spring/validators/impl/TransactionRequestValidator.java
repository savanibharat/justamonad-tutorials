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
	
	private final List<ValidatorFunction> validatorFunctions;
	
	@Inject
	public TransactionRequestValidator(List<ValidatorFunction> validatorFunctions) {
		this.validatorFunctions = validatorFunctions;
	}
	
	public void validate(Transaction transaction) {
		
		List<ErrorData> errorDatas = validatorFunctions
			.stream()
			.map(validatorFunc -> validatorFunc.apply(transaction))
			.flatMap(Collection::stream)
			.collect(Collectors.toUnmodifiableList());
		
		if(!errorDatas.isEmpty()) {
			throw new RequestValidationException(errorDatas.toString());
		}
		
	}

}
