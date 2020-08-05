package com.justamonad.tutorials.misc;

import com.justamonad.tutorials.common.Transaction;

public class TransactionRequest {

	private Transaction transaction;
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
}
