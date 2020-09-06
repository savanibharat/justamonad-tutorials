package com.justamonad.tutorials.common;

import java.util.Random;

import org.junit.Test;

public class TwoDArrayTraversal {

	//create table and teach about index.
	// teach how to print.
	// search for first element in 2d array from 0,0 to m,n and return index 
	// search for all occurrences for element in 2d array and return all indexes.
	public int[][] randomArr() {

		Random r = new Random();
		int low = 1;
		int high = 9;

		int[][] arr = new int[3][3];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				arr[row][col] = r.nextInt(high - low) + low;
			}
		}
		return arr;
	}

	@Test
	public void tablesRowOrdering() {

		int[][] arr = randomArr();
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				//String index = "(" + row + ", " + col + ")";
				//System.out.printf("%3s", arr[row][col] + "" + index + "  ");
				System.out.printf("%3s ", arr[row][col]);
			}
			System.out.println();
		}
	}

}
