package com.justamonad.tutorials.aop.all;

import java.util.Arrays;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Named
public class EmployeeAspectJoinPoint {

	@Before("execution(public void com.justamonad.tutorials.aop..set*(*))")
	public void loggingAdvice(JoinPoint joinPoint) {
		System.out.println(getClass().getSimpleName() +" "+ "Before running loggingAdvice on method=" + joinPoint.toString());

		System.out.println(getClass().getSimpleName() +" "+ "Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

	}

	// Advice arguments, will be applied to bean methods with single String argument
	@Before("args(name)")
	public void logStringArguments(String name) {
		System.out.println(getClass().getSimpleName() +" "+ "String argument passed=" + name);
	}
}