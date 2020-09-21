package com.justamonad.tutorials.aop.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.justamonad.tutorials.aop.model.extra.Name;
import com.justamonad.tutorials.aop.service.ShapeService;

@Configuration
public class ShapeConfiguration {

	@Bean
	public ShapeService shapeService() {
		ShapeService shapeService = new ShapeService();
		shapeService.setCircle(circle());
		shapeService.setTriangle(triangle());
		return shapeService;
	}
	
	@Bean
	public Triangle triangle() {
		Triangle triangle = new Triangle();
		triangle.setName("Triangle Name");
		return triangle;
	}

	@Bean
	public Circle circle() {
		Circle circle = new Circle();
		circle.setName("Circle Name");
		return circle;
	}
	
	@Bean
	public Name name() {
		Name name = new Name();
		name.setName("John");
		return name;
	}

}
