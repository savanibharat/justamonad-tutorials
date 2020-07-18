package com.justamonad.tutorials.spring.di;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SetterInjection {

	private BeanOne beanOne;

	@Inject
	public void setBeanOne(BeanOne beanOne) {
		this.beanOne = beanOne;
	}

	public void callMe() {
		System.out.println("In SetterInjection.");
		beanOne.callMe();
	}

}
