package com.justamonad.tutorials.spring.dependency.injection.config;

import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.spring.dependency.injection.api.impl.ErrorHandler;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.IInvoiceClient;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.InvoiceClientImpl;
import com.justamonad.tutorials.spring.dependency.injection.fakes.FakeInvoiceWebTarget;

@Configuration
@ComponentScan("com.justamonad.tutorials.spring.dependency.injection")
public class TestConfig {

	@Inject
	private ApplicationContext applicationContext;

	@Bean
	public IInvoiceClient invoiceClient() {
		@SuppressWarnings("unchecked")
		Function<String, WebTarget> clientFactory = (Function<String, WebTarget>) applicationContext
				.getBean("clientFactory");
		ErrorHandler errorHandler = applicationContext.getBean(ErrorHandler.class);
		return new InvoiceClientImpl(clientFactory, errorHandler);
	}

	@Bean
	@Scope("prototype")
	public WebTarget client(String path) {
		return new FakeInvoiceWebTarget(path);
	}

}
