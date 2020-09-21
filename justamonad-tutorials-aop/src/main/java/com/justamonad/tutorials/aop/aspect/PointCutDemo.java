package com.justamonad.tutorials.aop.aspect;

import java.util.UUID;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Named
public class PointCutDemo {

	/**
	 * Execute this advice for any method whose return type is not our concern and
	 * method name prefix must be "get". The get*() method must have no arguments
	 * then only it will be executed.
	 */
	// @Before("execution(public * get*())")
	/**
	 * <p>
	 * You can merge the advice like this
	 * </p>
	 * @Before("allGetters() && allCircleMethods()")
	 */
	// @Before("allGetters()")
	@Before("allGetters() && allCircleMethods()")
	public void loggingAdviceGetWildCard1() {
		System.out.println("Pointcut Advice run. Get method called 1.");
	}

	/**
	 * This method is same as loggingAdviceGetWildCard1.
	 * <p>
	 * The problem here is we are repeating the annotations. Better to use
	 * pointcuts.
	 * </p>
	 */
	// @Before("execution(public * get*())")
	@Before("allGetters()")
	public void secondAdvice() {
		System.out.println("Pointcut Advice run. Second advice.");
	}

	@Pointcut("execution(public * get*())")
	public void allGetters() {
	}

	/**
	 * <p>
	 * Pointcut for all methods of a class. Pointcut needs implementation like
	 * loggingAdviceGetWildCard1() and allGetters().
	 * </p>
	 * 
	 * <p>
	 * There is too much clutter in regex better would be to use within parameter in
	 * pointcut.
	 * </p>
	 */
	@Pointcut("execution(* *com.justamonad.tutorials.aop.model.Circle.*(..))")
	public void allCircleMethods() {
	}

	@Before("allCircleMethods()")
	public void allCircleMethodsImpl(JoinPoint joinPoint) {
		System.out.println(joinPoint.toShortString() + " : " + joinPoint.toString() + " : " + joinPoint.toLongString());
		System.out.println(joinPoint.getTarget());
	}
	
	@Before("args(name)")
	public void stringArgumentMethods(String name) {
		System.out.println("String args : " + name);
	}

	/**
	 * <p>
	 * Apply this pointcut to this class.
	 * </p>
	 * 
	 * This pointcut applies to all classes inside model
	 * package. @Pointcut("within(com.justamonad.tutorials.aop.model.*)")
	 * 
	 */
	@Pointcut("within(com.justamonad.tutorials.aop.model.Circle)")
	public void allCircleMethodsWithWithin() {
	}

	@Pointcut("within(com.justamonad.tutorials.aop.model.*)")
	public void allMethodsInPackageWithWithin() {
	}

	@Pointcut("within(com.justamonad.tutorials.aop.model..*)")
	public void allMethodsInPackageAndSubPackagesWithWithin() {
	}

	@Before("allMethodsInPackageWithWithin()")
	public void allMethodsInPackageWithWithinImpl() {
		System.out.println("allMethodsInPackageWithWithin " + UUID.randomUUID().toString());
	}

	@Before("allMethodsInPackageAndSubPackagesWithWithin()")
	public void allMethodsInPackageAndSubPackagesWithWithinImpl() {
		System.out.println("allMethodsInPackageAndSubPackagesWithWithin " + UUID.randomUUID().toString());
	}

}
