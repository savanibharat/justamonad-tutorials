package com.justamonad.tutorials.spring.rest.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	private final LoggingInterceptor loggingInterceptor;
	private final AnotherInterceptor anotherInterceptor;
	
	@Autowired
	public InterceptorConfiguration(LoggingInterceptor loggingInterceptor,
			AnotherInterceptor anotherInterceptor) {
		this.loggingInterceptor = loggingInterceptor;
		this.anotherInterceptor = anotherInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor);
		registry.addInterceptor(anotherInterceptor);
	}
	
}
