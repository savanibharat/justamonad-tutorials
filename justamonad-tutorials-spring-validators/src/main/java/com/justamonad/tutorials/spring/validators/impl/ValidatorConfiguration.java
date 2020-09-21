package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Sequence all the validators.
 * */

@Configuration
public class ValidatorConfiguration {

	private final List<ErrorData> emptyTransaction;
	private final List<ErrorData> emptyInvoice;
	private final List<ErrorData> emptyItems;
	private final List<ErrorData> emptyAmount;

	@Inject
	public ValidatorConfiguration(
			@Named("emptyTransaction") List<ErrorData> emptyTransaction,
			@Named("emptyInvoice") List<ErrorData> emptyInvoice,
			@Named("emptyItems") List<ErrorData> emptyItems,
			@Named("emptyAmount") List<ErrorData> emptyAmount) {
		this.emptyTransaction = emptyTransaction;
		this.emptyInvoice = emptyInvoice;
		this.emptyItems = emptyItems;
		this.emptyAmount = emptyAmount;
	}

	@Bean
	@Order(1)
	public ValidatorFunction transactionValidator() {
		return new TransactionValidator(emptyTransaction);
	}

	@Bean
	@Order(2)
	public ValidatorFunction invoiceValidator() {
		return new InvoiceValidator(emptyInvoice);
	}
	
	@Bean
	@Order(3)
	public ValidatorFunction itemValidator() {
		return new ItemValidator(emptyItems, emptyAmount);
	}

}