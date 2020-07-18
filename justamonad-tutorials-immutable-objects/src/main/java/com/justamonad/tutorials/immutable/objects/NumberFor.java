package com.justamonad.tutorials.immutable.objects;

import java.util.function.Supplier;

public abstract class NumberFor extends Number {

	private static final long serialVersionUID = -2523192959722500692L;

	private final Supplier<Integer> supplier;

	public NumberFor(Supplier<Integer> supplier) {
		this.supplier = supplier;
	}

	@Override
	public int intValue() {
		return supplier.get().intValue();
	}

	@Override
	public long longValue() {
		return 0l;
	}

	@Override
	public float floatValue() {
		return 0f;
	}

	@Override
	public double doubleValue() {
		return 0d;
	}

}
