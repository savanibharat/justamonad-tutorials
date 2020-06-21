package com.justamonad.tutorials.response.error.mapping;

import org.springframework.stereotype.Component;

@Component(IResponseHandler.TRANSACTION_RESPONSE_MAPPER)
public class TransactionResponseHandler implements IResponseHandler {

	@Override
	public Error mapError(Error error) {
		// TODO Auto-generated method stub
		return null;
	}

}
