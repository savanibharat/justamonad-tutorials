package com.justamonad.tutorials.injection.beans;

import javax.inject.Named;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Named
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {

	public void prototypeBeanMethod() {
		System.out.println("Called prototypeBeanMethod.");
		System.out.println("PrototypeBean hashCode is " + this.hashCode());
	}

}
