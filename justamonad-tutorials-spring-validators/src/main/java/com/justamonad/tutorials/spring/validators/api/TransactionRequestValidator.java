package com.justamonad.tutorials.spring.validators.api;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.impl.ErrorData;
import com.justamonad.tutorials.spring.validators.impl.RequestValidationException;
import com.justamonad.tutorials.spring.validators.impl.ValidatorFunction;

@Named
public final class TransactionRequestValidator {

	@Inject
	private List<ValidatorFunction> validatorFunctions;

	public void validate(Transaction transaction) {

		List<ErrorData> errorDatas = validatorFunctions
				.stream()
				.map(validatorFunction -> validatorFunction.apply(transaction))
				.flatMap(Collection::stream)
				.collect(collectingAndThen(toList(), Collections::unmodifiableList));
		
		if (!errorDatas.isEmpty()) {
			throw new RequestValidationException(errorDatas.toString());
		}

	}

}
