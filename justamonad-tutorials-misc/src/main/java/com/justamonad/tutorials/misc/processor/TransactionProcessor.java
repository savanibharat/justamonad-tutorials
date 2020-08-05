package com.justamonad.tutorials.misc.processor;

import javax.inject.Named;

import com.justamonad.tutorials.misc.TransactionRequest;
import com.justamonad.tutorials.misc.TransactionResponse;

@Named
public class TransactionProcessor {

	public TransactionResponse processPayment(TransactionRequest transactionRequest) {
		return new TransactionResponse();
	}

}
