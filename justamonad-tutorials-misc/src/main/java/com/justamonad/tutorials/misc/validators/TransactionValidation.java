package com.justamonad.tutorials.misc.validators;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.misc.TransactionRequest;

@Named
@Scope("prototype")
public class TransactionValidation {

	private final TransactionRequestValidator transactionRequestValidator;
	private final TransactionItemsAmountValidator transactionItemsAmountValidator;
	private final FundingMethodValidator fundingMethodValidator;
	private boolean validationState;

	@Inject
	public TransactionValidation(
			TransactionRequestValidator transactionRequestValidator,
			TransactionItemsAmountValidator transactionItemsAmountValidator,
			FundingMethodValidator fundingMethodValidator) {
		this.transactionRequestValidator = transactionRequestValidator;
		this.transactionItemsAmountValidator = transactionItemsAmountValidator;
		this.fundingMethodValidator = fundingMethodValidator;
	}

	public TransactionValidation validateRequest(TransactionRequest transactionRequest) {
		validationState = transactionRequestValidator.validate(transactionRequest);
		return this;
	}

	public TransactionValidation validateTxnItems(TransactionRequest transactionRequest) {
		if (validationState) {
			validationState = transactionItemsAmountValidator.validate(transactionRequest);
		}
		return this;
	}

	public TransactionValidation validateFundingMethod(TransactionRequest transactionRequest) {
		if (validationState) {
			validationState = fundingMethodValidator.validate(transactionRequest);
		}
		return this;
	}

	public <X extends Throwable> void throwIfFailure(Supplier<? extends X> exceptionSupplier) throws X {
		if (!validationState) {
			throw exceptionSupplier.get();
		}
	}

}
