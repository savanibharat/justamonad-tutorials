package com.justamonad.tutorials.spring.collection;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BeanClassInjection {

	private final List<BeanClass> beanClasses;

	@Inject
	public BeanClassInjection(List<BeanClass> beanClasses) {
		this.beanClasses = beanClasses;
	}

	public void printBeans() {
		beanClasses.forEach(e -> System.out.println(e.getStr()));
	}

}
