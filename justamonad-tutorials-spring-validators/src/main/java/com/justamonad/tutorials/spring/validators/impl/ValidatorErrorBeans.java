package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorErrorBeans {

	@Bean
	public List<ErrorData> noInvoice() {
		return Collections.singletonList(ErrorData.of("Invoice is empty", "invoice"));
	}
	
	@Bean
	public List<ErrorData> noItems() {
		return Collections.singletonList(ErrorData.of("Items is empty", "items"));
	}
	
	@Bean
	public List<ErrorData> noAmount() {
		return Collections.singletonList(ErrorData.of("Amount not specified", "amount"));
	}

	@Bean
	public List<ErrorData> noTransaction() {
		return Collections.singletonList(ErrorData.of("Transaction not specified", "transaction"));
	}
	
}
