package com.justamonad.tutorials.object.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SingletonBean class has two bean ids which means two different instances of
 * this class is created. Example of two singleton beans of same class.
 */
@Configuration
public class BeanConfig {

	@Bean("singleton_bean_1")
	public SingletonBean singletonBean1() {
		return new SingletonBean();
	}

	@Bean("singleton_bean_2")
	public SingletonBean singletonBean2() {
		return new SingletonBean();
	}

}
