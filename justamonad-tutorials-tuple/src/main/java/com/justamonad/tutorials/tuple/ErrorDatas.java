package com.justamonad.tutorials.tuple;

import static java.util.Collections.singletonList;

import java.util.List;

public class ErrorDatas {

	private ErrorDatas() {
	}

	public static final List<ErrorData> NO_INVOICE = singletonList(ErrorData.of("Invoice not specified", "invoice"));
	public static final List<ErrorData> NO_ITEM = singletonList(ErrorData.of("Items not specified.", "items"));
	public static final List<ErrorData> NO_AMOUNT = singletonList(ErrorData.of("Amount not specified", "amount"));
	public static final List<ErrorData> NO_TRANSACTION = singletonList(
			ErrorData.of("Transaction not specified.", "transaction"));

}
