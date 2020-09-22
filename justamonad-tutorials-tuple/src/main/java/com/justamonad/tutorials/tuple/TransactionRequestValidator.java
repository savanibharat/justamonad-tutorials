package com.justamonad.tutorials.tuple;

import java.util.List;
import java.util.function.Supplier;

import com.justamonad.tutorials.common.Transaction;

public class TransactionRequestValidator {

	public void validate(Transaction transaction) {

		List<Tuple2<Supplier<Boolean>, Supplier<List<ErrorData>>>> cases = 
		List.of(Tuple2.of(() -> transaction == null, () -> ErrorDatas.NO_TRANSACTION),
				Tuple2.of(() -> transaction.invoice() == null, () -> ErrorDatas.NO_INVOICE),
				Tuple2.of(() -> transaction.invoice().items() == null, () -> ErrorDatas.NO_ITEM),
				Tuple2.of(() -> transaction.invoice().items().isEmpty(), () -> ErrorDatas.NO_ITEM));

		Case.of(cases).match();
	}

	public static void main(String[] args) {

		Transaction txn = Transaction.of("US", null);
		new TransactionRequestValidator().validate(txn);

	}

}
