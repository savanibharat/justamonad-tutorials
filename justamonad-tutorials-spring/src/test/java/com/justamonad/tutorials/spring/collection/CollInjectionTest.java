package com.justamonad.tutorials.spring.collection;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CollInjectionTest {

	@Inject
	private CollConstructorInjection collConstructorInjection;
	
	@Inject
	private CollFieldInjection collFieldInjection;
	
	@Inject
	private CollSetterInjection collSetterInjection;

	@Test
	public void collConstructorInjectionTest() {
		collConstructorInjection.printCountries();
		collConstructorInjection.printNames();
		collConstructorInjection.printSymbols();
	}
	
	@Test
	public void collFieldInjectionTest() {
		collFieldInjection.printCountries();
		collFieldInjection.printNames();
		collFieldInjection.printSymbols();
	}
	
	@Test
	public void collSetterInjectionTest() {
		collSetterInjection.printCountries();
		collSetterInjection.printNames();
		collSetterInjection.printSymbols();
	}

}
