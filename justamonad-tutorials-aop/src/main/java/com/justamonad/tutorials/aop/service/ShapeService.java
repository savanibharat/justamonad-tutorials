package com.justamonad.tutorials.aop.service;

import javax.inject.Named;

import com.justamonad.tutorials.aop.model.Circle;
import com.justamonad.tutorials.aop.model.Triangle;

@Named
public class ShapeService {

	private Circle circle;
	private Triangle triangle;

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}

	public Circle getCircle() {
		return circle;
	}

	public Triangle getTriangle() {
		return triangle;
	}

}
