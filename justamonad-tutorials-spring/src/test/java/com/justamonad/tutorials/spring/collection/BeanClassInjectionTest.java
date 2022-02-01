package com.justamonad.tutorials.spring.collection;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class BeanClassInjectionTest {

	@Inject
	private BeanClassInjection beanClassInjection;
	
	@Test
	public void test() {
		beanClassInjection.printBeans();
	}
	
}
