package com.justamonad.tutorials.misc.validators;

import javax.inject.Named;

import com.justamonad.tutorials.misc.TransactionRequest;

@Named
public class FundingMethodValidator implements ReqValidator {

	public boolean validate(TransactionRequest transactionRequest) {
		// Make out-bound call to funding instrument service and get response.
		if (transactionRequest.getTransaction() == null) {
			return false;
		}
		return true;
	}

}
