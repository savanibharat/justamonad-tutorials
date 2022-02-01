package com.justamonad.tutorials.spring.collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class BeanClassConfiguration {

	@Bean
	@Order(1)
	public BeanClass beanClass1() {
		return new BeanClass("Jon Snow");
	}

	@Bean
	@Order(3)
	public BeanClass beanClass2() {
		return new BeanClass("Sansa Stark");
	}

	@Bean
	@Order(2)
	public BeanClass beanClass3() {
		return new BeanClass("Arya Stark");
	}

}
