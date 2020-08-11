package com.justamonad.tutorials.spring.dependency.injection;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.justamonad.tutorials.spring.dependency.injection");
	}
}
