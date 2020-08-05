package com.justamonad.tutorials.misc.conditions;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.misc.TransactionRequest;
import com.justamonad.tutorials.misc.TransactionResponse;
import com.justamonad.tutorials.misc.processor.TransactionProcessor;
import com.justamonad.tutorials.misc.validators.TransactionValidation;

@Named
@Scope("request")
public class TransactionResourceImplWithFluentAPI {

	private final TransactionProcessor transactionProcessor;
	private final TransactionValidation transactionValidation;

	@Inject
	public TransactionResourceImplWithFluentAPI(
			TransactionProcessor transactionProcessor,
			TransactionValidation transactionValidation) {
		this.transactionProcessor = transactionProcessor;
		this.transactionValidation = transactionValidation;
	}

	public TransactionResponse process(TransactionRequest transactionRequest) {
		transactionValidation
			.validateRequest(transactionRequest)
			.validateTxnItems(transactionRequest)
			.validateFundingMethod(transactionRequest)
			.throwIfFailure(IllegalArgumentException::new);
		return transactionProcessor.processPayment(transactionRequest);
	}

}
