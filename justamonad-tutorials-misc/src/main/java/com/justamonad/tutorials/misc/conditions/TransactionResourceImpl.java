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
public class TransactionResourceImpl {

	private final TransactionRequestValidator requestValidator;
	private final TransactionItemsAmountValidator itemsAmountValidator;
	private final FundingMethodValidator fundingMethodValidator;
	private final TransactionProcessor transactionProcessor;

	@Inject
	public TransactionResourceImpl(
			TransactionRequestValidator requestValidator,
			TransactionItemsAmountValidator itemsAmountValidator, 
			FundingMethodValidator fundingMethodValidator,
			TransactionProcessor transactionProcessor) {
		this.requestValidator = requestValidator;
		this.itemsAmountValidator = itemsAmountValidator;
		this.fundingMethodValidator = fundingMethodValidator;
		this.transactionProcessor = transactionProcessor;
	}

	public TransactionResponse process(TransactionRequest transactionRequest) {
		TransactionResponse transactionResponse = null;
		if (requestValidator.validate(transactionRequest)) {
			if (itemsAmountValidator.validate(transactionRequest)) {
				if (fundingMethodValidator.validate(transactionRequest)) {
					transactionResponse = transactionProcessor.processPayment(transactionRequest);
				}
			}
		}
		return transactionResponse;
	}

}
