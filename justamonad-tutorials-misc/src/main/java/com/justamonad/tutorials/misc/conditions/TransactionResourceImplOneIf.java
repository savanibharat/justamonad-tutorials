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
public class TransactionResourceImplOneIf {

	private final TransactionRequestValidator requestValidator;
	private final TransactionItemsAmountValidator itemsAmountValidator;
	private final FundingMethodValidator fundingMethodValidator;
	private final TransactionProcessor transactionProcessor;

	@Inject
	public TransactionResourceImplOneIf(TransactionRequestValidator requestValidator,
			TransactionItemsAmountValidator itemsAmountValidator, FundingMethodValidator fundingMethodValidator,
			TransactionProcessor transactionProcessor) {
		this.requestValidator = requestValidator;
		this.itemsAmountValidator = itemsAmountValidator;
		this.fundingMethodValidator = fundingMethodValidator;
		this.transactionProcessor = transactionProcessor;
	}

	public TransactionResponse process(TransactionRequest transactionRequest) {
		boolean isTransactionRequestValid = requestValidator.validate(transactionRequest);
		boolean isTransactionItemsValid = itemsAmountValidator.validate(transactionRequest);
		boolean isFundingMethodValid = fundingMethodValidator.validate(transactionRequest);
		boolean shouldProcessTransaction = isTransactionRequestValid 
												&& isTransactionItemsValid 
												&& isFundingMethodValid;
		
		TransactionResponse transactionResponse = null;
		if (shouldProcessTransaction) {
			transactionResponse = transactionProcessor.processPayment(transactionRequest);
		}
		return transactionResponse;
	}

}
