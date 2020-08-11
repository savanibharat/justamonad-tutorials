package com.justamonad.tutorials.aop.all;

import javax.inject.Named;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
//@Named
public class EmployeeAspectPointcut {

	@Before("getNamePointcut()")
	public void loggingAdvice() {
		System.out.println(getClass().getSimpleName() + " " + "Executing loggingAdvice on getName()");
	}

	@Before("getNamePointcut()")
	public void secondAdvice() {
		System.out.println(getClass().getSimpleName() + " " + "Executing secondAdvice on getName()");
	}

	@Pointcut("execution(public String getName())")
	public void getNamePointcut() {
	}

	@Before("allMethodsPointcut()")
	public void allServiceMethodsAdvice() {
		System.out.println(getClass().getSimpleName() + " " + "Before executing service method");
	}

	// Pointcut to execute on all the methods of classes in a package
	@Pointcut("within(com.justamonad.tutorials.aop.*)")
	public void allMethodsPointcut() {
	}

}