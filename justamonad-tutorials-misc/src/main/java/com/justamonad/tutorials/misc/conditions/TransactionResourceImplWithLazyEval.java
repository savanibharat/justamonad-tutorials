package com.justamonad.tutorials.misc.conditions;

import java.util.function.Predicate;

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
public class TransactionResourceImplWithLazyEval {

	private final TransactionRequestValidator requestValidator;
	private final TransactionItemsAmountValidator itemsAmountValidator;
	private final FundingMethodValidator fundingMethodValidator;
	private final TransactionProcessor transactionProcessor;

	@Inject
	public TransactionResourceImplWithLazyEval(
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
		
		Predicate<TransactionRequest> p1 = (tr) -> requestValidator.validate(tr);
		Predicate<TransactionRequest> p2 = (tr) -> itemsAmountValidator.validate(tr);
		Predicate<TransactionRequest> p3 = (tr) -> fundingMethodValidator.validate(tr);
		
		boolean shouldProcess = p1.test(transactionRequest) 
							&& p2.test(transactionRequest) 
							&& p3.test(transactionRequest);
		
		if (shouldProcess) {
			transactionResponse = transactionProcessor.processPayment(transactionRequest);
		} else {
			throw new IllegalArgumentException();
		}
		return transactionResponse;
	}
}
