package com.justamonad.tutorials.misc.validators;

import javax.inject.Named;

import com.justamonad.tutorials.misc.TransactionRequest;

@Named
public class TransactionItemsAmountValidator {

	public boolean validate(TransactionRequest transactionRequest) {
		if (transactionRequest.getTransaction() == null) {
			return false;
		}
		return transactionRequest.getTransaction().amount() != null;
	}

}
