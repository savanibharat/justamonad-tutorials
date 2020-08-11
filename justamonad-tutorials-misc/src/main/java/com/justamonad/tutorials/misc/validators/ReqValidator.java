package com.justamonad.tutorials.misc.validators;

import com.justamonad.tutorials.misc.TransactionRequest;

public interface ReqValidator {

	boolean validate(TransactionRequest transactionRequest);

}
