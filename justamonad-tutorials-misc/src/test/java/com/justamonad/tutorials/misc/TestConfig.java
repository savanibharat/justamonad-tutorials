package com.justamonad.tutorials.misc;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.justamonad.tutorials.misc.conditions.TransactionResourceImpl;
import com.justamonad.tutorials.misc.conditions.TransactionResourceImplWithFluentAPI;
import com.justamonad.tutorials.misc.conditions.TransactionResourceImplWithLazyEval;
import com.justamonad.tutorials.misc.processor.TransactionProcessor;
import com.justamonad.tutorials.misc.validators.FundingMethodValidator;
import com.justamonad.tutorials.misc.validators.TransactionItemsAmountValidator;
import com.justamonad.tutorials.misc.validators.TransactionRequestValidator;
import com.justamonad.tutorials.misc.validators.TransactionValidation;

@Configuration
@ComponentScan(basePackages = "com.justamonad.tutorials.misc")
public class TestConfig {

	@Inject
	private ApplicationContext applicationContext;

	@Bean
	public TransactionResourceImplWithFluentAPI transactionResourceImplWithFluentAPI() {
		return new TransactionResourceImplWithFluentAPI(
				applicationContext.getBean(TransactionProcessor.class),
				applicationContext.getBean(TransactionValidation.class));
	}

	@Bean
    public TransactionResourceImpl transactionResourceImpl() {
        return new TransactionResourceImpl(
                applicationContext.getBean(TransactionRequestValidator.class),
                applicationContext.getBean(TransactionItemsAmountValidator.class),
                applicationContext.getBean(FundingMethodValidator.class),
                applicationContext.getBean(TransactionProcessor.class));
    }

	@Bean
	public TransactionResourceImplWithLazyEval transactionResourceImplWithLazyEval() {
		return new TransactionResourceImplWithLazyEval(
                applicationContext.getBean(TransactionRequestValidator.class),
                applicationContext.getBean(TransactionItemsAmountValidator.class),
                applicationContext.getBean(FundingMethodValidator.class),
                applicationContext.getBean(TransactionProcessor.class));
	}

}