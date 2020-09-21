//package com.justamonad.tutorials.aop.all;
//
//import javax.inject.Named;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//
//@Aspect
//@Named
//public class EmployeeAfterAspect {
//
//	@After("args(name)")
//	public void logStringArguments(String name) {
//		System.out.println(getClass().getSimpleName() +" "+ "Running After Advice. String argument passed=" + name);
//	}
//
//	@AfterThrowing("within(com.justamonad.tutorials.aop.Employee)")
//	public void logExceptions(JoinPoint joinPoint) {
//		System.out.println(getClass().getSimpleName() +" "+ "Exception thrown in Employee Method=" + joinPoint.toString());
//	}
//
//	@AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
//	public void getNameReturningAdvice(String returnString) {
//		System.out.println(getClass().getSimpleName() +" "+ "getNameReturningAdvice executed. Returned String=" + returnString);
//	}
//
//}