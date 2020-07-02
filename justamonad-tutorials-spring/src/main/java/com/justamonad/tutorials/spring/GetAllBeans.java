package com.justamonad.tutorials.spring;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

@Named
public class GetAllBeans implements CommandLineRunner {

	private final ApplicationContext applicationContext;

	@Inject
	public GetAllBeans(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		Arrays.stream(beans).sorted().forEach(System.out::println);
	}

}
