package com.justamonad.tutorials.spring.validators;

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

	/**
	 * get few data elements in json object and then pretty print it.
	 */
	@Override
	public void run(String... args) throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean + " : " + applicationContext.getType(bean));
		}
//		String[] beans = applicationContext.getBeanDefinitionNames();
//		Arrays.stream(beans)
//		.sorted().forEach(bean -> System.out.println(bean));

//		String[] beans = applicationContext.getBeanDefinitionNames();
//		Arrays.stream(beans)
//		.sorted().forEach(System.out::println);

	}

}
