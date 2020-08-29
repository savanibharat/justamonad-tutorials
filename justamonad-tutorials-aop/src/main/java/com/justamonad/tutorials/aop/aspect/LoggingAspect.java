package com.justamonad.tutorials.aop.aspect;

import javax.inject.Named;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Named
public class LoggingAspect {

	/**
	 * @Before advice is to execute any code before a method call. method can be of
	 *         any class. If we want to restrict to method of particular class we
	 *         need to provide fully qualified name of class.
	 */
//	@Before("execution(public String getName())")
//	public void loggingAdvice() {
//		System.out.println("Advice run. Get method called.");
//	}

	/**
	 * If we want to restrict to method of particular class we need to provide fully
	 * qualified name of class.
	 */
//	@Before("execution(public String com.justamonad.tutorials.aop.model.Circle.getName())")
//	public void loggingAdviceCircleClass() {
//		System.out.println("Advice run. Advice called for Circle class");
//	}

	/**
	 * Execute this advice for any method whose return type is not our concern and
	 * method name prefix must be "get". The get*() method must have no arguments
	 * then only it will be executed.
	 */
	@Before("execution(public * get*())")
	public void loggingAdviceGetWildCard1() {
		System.out.println("Advice run. Get method called 1.");
	}

	/**
	 * Execute this advice for any method whose return type is not our concern and
	 * method name prefix must be "get". The get*(..) method must have 0 or more
	 * arguments then only it will be executed.
	 */
	@Before("execution(public * get*(..))")
	public void loggingAdviceGetWildCard2() {
		System.out.println("Advice run. Get method called 2.");
	}

	/**
	 * Execute this advice for any method whose return type is not our concern and
	 * method name prefix must be "get".The get*(*) method must have 1 or more
	 * arguments then only it will be executed.
	 */
	@Before("execution(public * get*(*))")
	public void loggingAdviceGetWildCard3() {
		System.out.println("Advice run. Get method called 3.");
	}

	/**
	 * Execute this advice for any method in certain package whose return type is
	 * not our concern and method name prefix must be "get".The get*(*) method must
	 * have 1 or more arguments then only it will be executed.
	 */
	@Before("execution(public * com.justamonad.tutorials.aop.model.*.get*(..))")
	public void loggingAdviceGetWildCard4() {
		System.out.println("Advice run. Get method called 4.");
	}

	/**
	 * This method is same as loggingAdviceGetWildCard1.
	 * <p>
	 * The problem here is we are repeating the annotations. Better to use
	 * pointcuts.
	 * </p>
	 */
	@Before("execution(public * get*())")
	public void secondAdvice() {
		System.out.println("Second advice.");
	}

}
