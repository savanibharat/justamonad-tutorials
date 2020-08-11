package com.justamonad.tutorials.misc.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.misc.TransactionRequest;

@Named
@Scope("prototype")
public class TransactionValidationLazy {

	private List<ReqValidator> reqValidators = new ArrayList<>();
	private TransactionRequest transactionRequest;

	public TransactionValidationLazy setRequest(TransactionRequest transactionRequest) {
		this.transactionRequest = transactionRequest;
		return this;
	}

	public TransactionValidationLazy validateRequest(ReqValidator reqValidator) {
		reqValidators.add(reqValidator);
		return this;
	}

	public <X extends Throwable> void throwIfFailure(Supplier<? extends X> exceptionSupplier) throws X {

		boolean isSuccess = true;

		for (ReqValidator reqValidator : reqValidators) {
			if (isSuccess) {
				isSuccess = reqValidator.validate(transactionRequest);
			} else {
				throw exceptionSupplier.get();
			}
		}

	}

}
