package com.justamonad.tutorials.spring.validators.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ValidatorConfiguration {

	@Bean
	@Order(1)
	public ValidatorFunction transactionValidator() {
		return new TransactionValidator();
	}
	
	@Bean
	@Order(2)
	public ValidatorFunction invoiceValidator() {
		return new InvoiceValidator();
	}
	
}
