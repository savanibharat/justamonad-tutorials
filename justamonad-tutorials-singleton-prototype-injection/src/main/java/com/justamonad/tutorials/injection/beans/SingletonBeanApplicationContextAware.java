package com.justamonad.tutorials.injection.beans;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Named
public class SingletonBeanApplicationContextAware implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public PrototypeBean getPrototypeBean() {
		return applicationContext.getBean(PrototypeBean.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
