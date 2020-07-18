package com.justamonad.tutorials.immutable.objects;

public class MultiplyFor extends NumberFor {

	private static final long serialVersionUID = -3865932648999744758L;

	public MultiplyFor(Integer val, NumberFor numberFor) {
		super(() -> {
			return val * numberFor.intValue();
		});
	}

	@Override
	public int intValue() {
		return super.intValue();
	}

	public static void main(String[] args) {

		NumberFor maxOf = new MaxFor(1, 2, 3, 4, 5);
		MultiplyFor multiplyFor = new MultiplyFor(4, maxOf);
		int intValue = multiplyFor.intValue();
		System.out.println(intValue);
	}

}
