package com.justamonad.tutorials.spring.di;

import javax.inject.Named;

@Named
public class BeanOne {

	public void callMe() {
		System.out.println("BeanOne callMe() called.");
	}

}
