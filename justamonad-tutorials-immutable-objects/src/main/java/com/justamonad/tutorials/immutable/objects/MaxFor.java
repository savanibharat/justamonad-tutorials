package com.justamonad.tutorials.immutable.objects;

public class MaxFor extends NumberFor {

	private static final long serialVersionUID = 3019949829915034904L;

	public MaxFor(Integer... vals) {
		super(() -> {
			int max = vals[0];
			for (Integer val : vals) {
				max = Math.max(val, max);
			}
			return max;
		});
	}

	@Override
	public int intValue() {
		return super.intValue();
	}

}
