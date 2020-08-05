package com.justamonad.tutorials.misc.conditions;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.misc.TransactionRequest;
import com.justamonad.tutorials.misc.TransactionResponse;
import com.justamonad.tutorials.misc.processor.TransactionProcessor;
import com.justamonad.tutorials.misc.validators.FundingMethodValidator;
import com.justamonad.tutorials.misc.validators.TransactionItemsAmountValidator;
import com.justamonad.tutorials.misc.validators.TransactionRequestValidator;

@Named
@Scope("request")
public class TransactionResourceImplFlatIfCondition {

	private final TransactionRequestValidator requestValidator;
	private final TransactionItemsAmountValidator itemsAmountValidator;
	private final FundingMethodValidator fundingMethodValidator;
	private final TransactionProcessor transactionProcessor;

	@Inject
	public TransactionResourceImplFlatIfCondition(TransactionRequestValidator requestValidator,
			TransactionItemsAmountValidator itemsAmountValidator, FundingMethodValidator fundingMethodValidator,
			TransactionProcessor transactionProcessor) {
		this.requestValidator = requestValidator;
		this.itemsAmountValidator = itemsAmountValidator;
		this.fundingMethodValidator = fundingMethodValidator;
		this.transactionProcessor = transactionProcessor;
	}

	public TransactionResponse process(TransactionRequest transactionRequest) {
		boolean validate = requestValidator.validate(transactionRequest);
		if (!validate) {
			throw new IllegalArgumentException();
		}
		boolean validate2 = itemsAmountValidator.validate(transactionRequest);
		if (!validate2) {
			throw new IllegalArgumentException();
		}
		boolean validate3 = fundingMethodValidator.validate(transactionRequest);
		if (!validate3) {
			throw new IllegalArgumentException();
		}
		return transactionProcessor.processPayment(transactionRequest);
	}

}
