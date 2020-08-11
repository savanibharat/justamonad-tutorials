package com.justamonad.tutorials.aop;

import javax.inject.Named;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Named
public class EmployeeAspect {

	@Before("execution(public String getName())")
	public void getNameAdvice() {
		System.out.println(getClass().getSimpleName() + " " + "Executing Advice on getName()");
	}

	@Before("execution(* com.justamonad.tutorials.aop.*.get*())")
	public void getAllAdvice() {
		System.out.println(getClass().getSimpleName() + " " + "Service method getter called");
	}

}