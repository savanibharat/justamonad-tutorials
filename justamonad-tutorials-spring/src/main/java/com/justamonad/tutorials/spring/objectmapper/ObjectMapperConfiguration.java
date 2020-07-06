package com.justamonad.tutorials.spring.objectmapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperConfiguration {

	@Bean(value = "readOM")
	@Primary
	public ObjectMapper readObjectMapper() {
		return new ObjectMapper();
	}

	@Bean(value = "writeOM")
	public ObjectMapper writeObjectMapper() {
		return new ObjectMapper();
	}
	
	
	// With below code same objectmapper is returned from application context.
//	@Bean//(value = "readOM")
//	@Primary
//	public ObjectMapper readObjectMapper() {
//		return new ObjectMapper();
//	}
//
//	@Bean//(value = "writeOM")
//	public ObjectMapper writeObjectMapper() {
//		return new ObjectMapper();
//	}

}
