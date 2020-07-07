package com.justamonad.tutorials.injection.beans;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public Supplier<PrototypeBean> prototypeBean() {
		return PrototypeBean::new;
	}
	
//	@Bean
//	public Function<String, PrototypeBean> prototypeBean() {
//		return PrototypeBean::new;
//	}
	
//	@Bean
//	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	public PrototypeBean prototypeBean() {
//		return new PrototypeBean();
//	}
	
}
