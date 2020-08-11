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
import com.justamonad.tutorials.misc.validators.TransactionValidationLazy;

@Named
@Scope("request")
public class TransactionResourceImplWithLazyEvalBetter {

	private final TransactionProcessor transactionProcessor;
	private final TransactionValidationLazy transactionValidationLazy;
	private final TransactionRequestValidator requestValidator;
	private final TransactionItemsAmountValidator itemsAmountValidator; 
	private final FundingMethodValidator fundingMethodValidator;

	@Inject
	public TransactionResourceImplWithLazyEvalBetter(
			TransactionProcessor transactionProcessor,
			TransactionRequestValidator requestValidator,
			TransactionItemsAmountValidator itemsAmountValidator, 
			FundingMethodValidator fundingMethodValidator,
			TransactionValidationLazy transactionValidationLazy) {
		this.transactionProcessor = transactionProcessor;
		this.requestValidator = requestValidator;
		this.itemsAmountValidator = itemsAmountValidator;
		this.fundingMethodValidator = fundingMethodValidator;
		this.transactionValidationLazy = transactionValidationLazy;
	}

	public TransactionResponse process(TransactionRequest transactionRequest) {
		transactionValidationLazy
			.setRequest(transactionRequest)
			.validateRequest(requestValidator)
			.validateRequest(itemsAmountValidator)
			.validateRequest(fundingMethodValidator)
			.throwIfFailure(IllegalArgumentException::new);
		return transactionProcessor.processPayment(transactionRequest);
	}

}
