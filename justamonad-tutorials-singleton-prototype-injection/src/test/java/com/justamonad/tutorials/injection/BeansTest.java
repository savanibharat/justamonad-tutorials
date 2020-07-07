package com.justamonad.tutorials.injection;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justamonad.tutorials.injection.beans.SingletonBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class BeansTest {

	@Inject
	private SingletonBean singletonBean;

	@Test
	public void beanTest() {
		singletonBean.singletonBeanMethod();
		singletonBean.singletonBeanMethod();
	}
	
}
