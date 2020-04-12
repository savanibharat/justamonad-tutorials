package org.justamonad.tutorials.optional.functional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Option<A> {

	@SuppressWarnings("rawtypes")
	private static final Option NONE = new None();

	public abstract A getOrThrow();

	public abstract A getOrElse(Supplier<A> defaultSupplier);

	public abstract <B> Option<B> map(Function<A, B> f);

	public <B> Option<B> flatMap(Function<A, Option<B>> f) {
		return map(f).getOrElse(Option::none);
	}

	public Option<A> orElse(Supplier<Option<A>> s) {
		return map(x -> this).getOrElse(s);
	}

	// In the None subclass, you simply return none(). In the Some class, you
	// return the original Option if the condition holds, and none() otherwise.
	// But try to devise a smarter implementation that fits in the Option parent
	// class.
	public Option<A> filter(Function<A, Boolean> f) {
		return flatMap(x -> f.apply(x) ? this : none());
	}

	public static <A, B> Function<Option<A>, Option<B>> lift(Function<A, B> f) {
		return x -> x.map(f);
	}

	private static class None<A> extends Option<A> {

		@Override
		public A getOrThrow() {
			throw new IllegalStateException("get called on none.");
		}

		@Override
		public String toString() {
			return "None";
		}

		@Override
		public A getOrElse(Supplier<A> defaultSupplier) {
			return defaultSupplier.get();
		}

		@Override
		public <B> Option<B> map(Function<A, B> f) {
			return none();
		}

	}

	private static class Some<A> extends Option<A> {
		private final A value;

		private Some(A value) {
			this.value = Objects.requireNonNull(value);
		}

		@Override
		public A getOrThrow() {
			return value;
		}

		@Override
		public String toString() {
			return String.format("Some (%s)", value);
		}

		@Override
		public A getOrElse(Supplier<A> defaultSupplier) {
			return value;
		}

		@Override
		public <B> Option<B> map(Function<A, B> f) {
			return new Some<>(f.apply(this.value));
		}

	}

	public static <A> Option<A> some(A value) {
		return new Some<A>(value);
	}

	@SuppressWarnings("unchecked")
	public static <A> Option<A> none() {
		return NONE;
	}

	static Function<List<Integer>, Option<Integer>> max() {
		return xs -> xs.isEmpty() ? none() : Option.some(Collections.max(xs));
	}

	public static void main(String[] args) {

	}

}
