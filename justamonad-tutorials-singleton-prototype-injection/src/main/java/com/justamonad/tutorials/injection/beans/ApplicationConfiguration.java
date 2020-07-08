package com.justamonad.tutorials.injection.beans;

import java.util.function.Supplier;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PrototypeBean prototypeBean() {
		return new PrototypeBean();
	}

	@Bean
	public Supplier<PrototypeBean> prototypeBeanUsingSupplier() {
		return PrototypeBean::new;
	}

}
