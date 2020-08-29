package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ValidatorConfiguration {

	private final List<ErrorData> noTxn;
	private final List<ErrorData> noInv;
	private final List<ErrorData> noItems;
	private final List<ErrorData> noAmount;

	@Inject
	public ValidatorConfiguration(
			@Named("emptyTransaction") List<ErrorData> noTxn,
			@Named("emptyInvoice") List<ErrorData> noInv,
			@Named("emptyItems") List<ErrorData> noItems,
			@Named("emptyAmount") List<ErrorData> noAmount) {
		this.noTxn = noTxn;
		this.noInv = noInv;
		this.noItems = noItems;
		this.noAmount = noAmount;
	}

	@Bean
	@Order(1)
	public ValidatorFunction transactionValidator() {
		return new TransactionValidator(noTxn);
	}

	@Bean
	@Order(2)
	public ValidatorFunction invoiceValidator() {
		return new InvoiceValidator(noInv);
	}
	
	@Bean
	@Order(3)
	public ValidatorFunction itemValidator() {
		return new ItemValidator(noItems, noAmount);
	}

}