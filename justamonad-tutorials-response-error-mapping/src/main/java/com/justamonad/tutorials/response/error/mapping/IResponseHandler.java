package com.justamonad.tutorials.response.error.mapping;

public interface IResponseHandler {

	public static final String TRANSACTION_RESPONSE_MAPPER = "transactionResponseMapper";
	public static final String INVOICE_RESPONSE_MAPPER = "invoiceResponseMapper";

	Error mapError(Error error);

}
