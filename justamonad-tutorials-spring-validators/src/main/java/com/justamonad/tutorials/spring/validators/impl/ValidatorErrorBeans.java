package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link ValidatorErrorBeans} is used to return ErrorData for specific
 * validation failures.
 * 
 * We need not create this failures on the fly. We can just create them as Beans
 * and let Spring IOC Container take care of it.
 * 
 * When the Spring Boot server boots up this Beans are initialized and managed
 * by {@link ApplicationContext}. We just need to use them using injection.
 * 
 */
@Configuration
class ValidatorErrorBeans {

	@Bean
	List<ErrorData> noInvoice() {
		return Collections.singletonList(ErrorData.of("Invoice is empty", "invoice"));
	}

	@Bean
	List<ErrorData> noItems() {
		return Collections.singletonList(ErrorData.of("Items is empty", "items"));
	}

	@Bean
	List<ErrorData> noAmount() {
		return Collections.singletonList(ErrorData.of("Amount not specified", "amount"));
	}

	@Bean
	List<ErrorData> noTransaction() {
		return Collections.singletonList(ErrorData.of("Transaction not specified", "transaction"));
	}

}
