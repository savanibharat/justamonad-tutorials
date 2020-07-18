package com.justamonad.tutorials.spring.di;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.spring.collection.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class SetterInjectionTest {

	@Inject
	private SetterInjection setterInjection;
	
	@Test
	public void testSetterInjection() {
		setterInjection.callMe();
	}
	
}
