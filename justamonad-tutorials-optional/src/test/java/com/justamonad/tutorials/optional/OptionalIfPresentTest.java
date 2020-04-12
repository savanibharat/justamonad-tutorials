package com.justamonad.tutorials.optional;

import org.junit.Test;

public class OptionalIfPresentTest {

	@Test
	public void test() {
		me();
	}

	void me() {
		int[][] a = fill();
		int[][] b = fill();
		int[][] c = new int[3][3];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				c[i][j] = Math.addExact(a[i][j], b[i][j]);
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}

	}

	private int[][] fill() {
		int[][] temp = new int[3][3];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[i][j] = 1;
			}
		}
		return temp;
	}
}
