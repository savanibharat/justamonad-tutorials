package com.justamonad.tutorials.injection.beans;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SingletonBean {

	private final Supplier<PrototypeBean> prototypeBean;

	@Inject
	public SingletonBean(Supplier<PrototypeBean> prototypeBean) {
		this.prototypeBean = prototypeBean;
	}

	public void singletonBeanMethod() {
		System.out.println("Called singletonBeanMethod.");
		System.out.println("SingletonBean hashCode is " + this.hashCode());
		prototypeBean.get().prototypeBeanMethod();
		prototypeBean.get().prototypeBeanMethod();
		prototypeBean.get().prototypeBeanMethod();
	}
//	public void singletonBeanMethod() {
//		System.out.println("Called singletonBeanMethod.");
//		System.out.println("SingletonBean hashCode is " + this.hashCode());
//		prototypeBean.getObject().prototypeBeanMethod();
//		prototypeBean.getObject().prototypeBeanMethod();
//		prototypeBean.getObject().prototypeBeanMethod();
//	}

//	public void singletonBeanMethod() {
//		System.out.println("Called singletonBeanMethod.");
//		System.out.println("SingletonBean hashCode is " + this.hashCode());
//		prototypeBeanProvider.prototypeBeanMethod();
//		prototypeBeanProvider.prototypeBeanMethod();
//		prototypeBeanProvider.prototypeBeanMethod();
//		prototypeBeanProvider.get().prototypeBeanMethod();
//		prototypeBeanProvider.get().prototypeBeanMethod();
//		prototypeBeanProvider.get().prototypeBeanMethod();
//		System.out.println(provider.get().hashCode());
//		System.out.println(provider.get().hashCode());
//		System.out.println(provider.get().hashCode());
//		System.out.println(provider.get().hashCode());
//	}

}
