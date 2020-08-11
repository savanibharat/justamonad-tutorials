package com.justamonad.tutorials.spring.dependency.injection.api;

import java.util.UUID;

public class TransactionRequest {

	public String invoiceId() {
		return UUID.randomUUID().toString();
	}
	
}
