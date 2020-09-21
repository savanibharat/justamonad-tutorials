package com.justamonad.tutorials.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.justamonad.tutorials.aop.model.ShapeConfiguration;
import com.justamonad.tutorials.aop.model.extra.Name;
import com.justamonad.tutorials.aop.service.ShapeService;

public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ShapeConfiguration.class, AOPConfig.class);
		ShapeService shapeService = ctx.getBean(ShapeService.class);
		System.out.println(shapeService.getCircle().getName());
		shapeService.getCircle().getName();
		System.out.println(shapeService.getTriangle().getName());
		
		Name name = ctx.getBean(Name.class);
		name.setName("John");
		
		((ConfigurableApplicationContext) ctx).close();
		
//		EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
//		System.out.println("\n\n");
//		System.out.println(employeeService.getEmployee().getName());
//		employeeService.getEmployee().setName("John");
//		employeeService.getEmployee().throwException();


	}

}
