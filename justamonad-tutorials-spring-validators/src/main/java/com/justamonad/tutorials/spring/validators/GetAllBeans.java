package com.justamonad.tutorials.spring.validators;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

@Named
public class GetAllBeans implements CommandLineRunner {

	@Inject
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}
	}

}
