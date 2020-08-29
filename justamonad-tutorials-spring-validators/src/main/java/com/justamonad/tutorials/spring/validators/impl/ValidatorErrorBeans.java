package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * {@link ValidatorErrorBeans} is used to return ErrorData for specific
 * validation failures.
 * </p>
 * 
 * <p>
 * Why do we exactly need this class? Can't we just create it when the
 * validation fails? Yes, you can but for every failure you will create new
 * objects with same data. Seems like a waste.
 * </p>
 * 
 * <p>
 * We need not create this failures on the fly. We can just create them as Beans
 * and let Spring IOC Container take care of it.
 * </p>
 * 
 * <p>
 * When the Spring Boot server boots up this Beans are initialized and managed
 * by {@link ApplicationContext}. We just need to use them using injection.
 * </p>
 */
@Configuration
public class ValidatorErrorBeans {

	@Bean("emptyInvoice")
	public List<ErrorData> noInvoice() {
		return Collections.singletonList(ErrorData.of("Invoice not specified", "invoice"));
	}

	@Bean("emptyItems")
	public List<ErrorData> noItems() {
		return Collections.singletonList(ErrorData.of("Items not specified.", "items"));
	}

	@Bean("emptyAmount")
	public List<ErrorData> noAmount() {
		return Collections.singletonList(ErrorData.of("Amount not specified", "amount"));
	}

	@Bean("emptyTransaction")
	public List<ErrorData> noTransaction() {
		return Collections.singletonList(ErrorData.of("Transaction not specified.", "transaction"));
	}
	
}
