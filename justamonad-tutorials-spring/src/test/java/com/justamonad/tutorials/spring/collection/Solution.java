package com.justamonad.tutorials.spring.collection;

import java.util.Arrays;
import java.util.List;

public class Solution {

	public interface Shape {
		public int shape();
	}

	public static abstract class AbstractShape implements Shape {

	}

	public static class Rectangle extends AbstractShape {
		int a;
		int b;

		public Rectangle(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int shape() {
			return a * b;
		}

	}

	public static class Circle extends AbstractShape {
		int a;
		int b;

		public Circle(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int shape() {
			return a * b;
		}

	}

	public static void main(String[] args) {
		List<Shape> list = Arrays.asList(new Rectangle(5, 2), new Circle(4, 5));
		System.out.println(list.get(0).shape() + list.get(1).shape());
	}

}
