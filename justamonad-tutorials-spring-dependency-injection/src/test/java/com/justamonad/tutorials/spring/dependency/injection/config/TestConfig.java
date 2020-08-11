package com.justamonad.tutorials.spring.dependency.injection.config;

import java.util.function.Function;

import javax.ws.rs.client.WebTarget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.justamonad.tutorials.spring.dependency.injection.api.impl.IInvoiceClient;
import com.justamonad.tutorials.spring.dependency.injection.api.impl.InvoiceClientImpl;
import com.justamonad.tutorials.spring.dependency.injection.fakes.FakeInvoiceWebTarget;

@Configuration
@ComponentScan("com.justamonad.tutorials.spring.dependency.injection")
public class TestConfig {

	@Bean
	public IInvoiceClient invoiceClient() {
		return new InvoiceClientImpl();
	}

	@Bean
	public Function<String, WebTarget> beanFactory() {
		return name -> client(name);
	}

	@Bean
	@Scope("prototype")
	public WebTarget client(String path) {
		return new FakeInvoiceWebTarget(path);
	}

}
