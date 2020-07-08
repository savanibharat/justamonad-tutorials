package com.justamonad.tutorials.injection.beans;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

@Named
public class SingletonBean {

	private final Provider<PrototypeBean> prototypeBean;

	@Inject
	public SingletonBean(Provider<PrototypeBean> prototypeBean) {
		this.prototypeBean = prototypeBean;
	}

	public void singletonBeanMethod() {
		System.out.println("Called singletonBeanMethod.");
		System.out.println("SingletonBean hashCode is " + this.hashCode());
		prototypeBean.get().prototypeBeanMethod();
		prototypeBean.get().prototypeBeanMethod();
	}

}
