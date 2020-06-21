package com.justamonad.tutorials.object.mapping.main;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

import com.justamonad.tutorials.object.mapping.SingletonBean;

@Named
public class GetAllBeans implements CommandLineRunner {

	@Inject
	private ApplicationContext applicationContext;

	@Inject
	@Qualifier("singleton_bean_1")
	private SingletonBean s1;
	
	@Inject
	@Qualifier("singleton_bean_2")
	private SingletonBean s2;
	
	@Override
	public void run(String... args) throws Exception {
		String[] beans = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}

}
