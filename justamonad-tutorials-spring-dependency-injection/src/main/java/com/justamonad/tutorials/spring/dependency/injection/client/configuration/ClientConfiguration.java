package com.justamonad.tutorials.spring.dependency.injection.client.configuration;

import java.util.function.Function;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ClientConfiguration {

	@Bean
	public Function<String, WebTarget> beanFactory() {
		return name -> client(name);
	}

	@Bean
	@Scope("prototype")
	public WebTarget client(String path) {
		Client client = ClientBuilder.newBuilder().build();
		return client.target(path);
	}

}
