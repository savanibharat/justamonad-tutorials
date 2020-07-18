package com.justamonad.tutorials.immutable.objects;

public class AverageFor extends NumberFor {

	private static final long serialVersionUID = -5450081814022250292L;

	public AverageFor(Integer... vals) {
		super(() -> {
			int avg = 0;
			for (Integer val : vals) {
				avg = avg + val;
			}
			return avg / vals.length;
		});
	}

	@Override
	public int intValue() {
		return super.intValue();
	}
	
	public static void main(String[] args) {
		
	}

}
