package com.justamonad.tutorials.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);
		EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
		
		System.out.println("\n\n");

		System.out.println(employeeService.getEmployee().getName());

		employeeService.getEmployee().setName("John");

		employeeService.getEmployee().throwException();

		((ConfigurableApplicationContext) ctx).close();

	}

}
