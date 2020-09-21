//package com.justamonad.tutorials.aop.all;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//
////@Aspect
////@Named
//public class EmployeeAroundAspect {
//
//	@Around("execution(* com.justamonad.tutorials.aop.getName())")
//	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
//		System.out.println(getClass().getSimpleName() +" "+ "Before invoking getName() method");
//		Object value = null;
//		try {
//			value = proceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		System.out.println(getClass().getSimpleName() +" "+ "After invoking getName() method. Return value="+value);
//		return value;
//	}
//}
