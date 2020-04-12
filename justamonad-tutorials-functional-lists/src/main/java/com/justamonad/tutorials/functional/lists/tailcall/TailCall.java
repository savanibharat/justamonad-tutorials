package com.justamonad.tutorials.functional.lists.tailcall;

import java.util.function.Supplier;

public abstract class TailCall<T> {

	public abstract TailCall<T> resume();

	public abstract T eval();

	public abstract boolean isSuspend();

	public static <T> Return<T> ret(T t) {
		return new Return<T>(t);
	}

	public static <T> Suspend<T> suspend(Supplier<TailCall<T>> supplier) {
		return new Suspend<T>(supplier);
	}

	public static class Return<T> extends TailCall<T> {

		private final T t;

		public Return(T t) {
			this.t = t;
		}

		@Override
		public TailCall<T> resume() {
			throw new IllegalArgumentException("Return has no resume.");
		}

		@Override
		public T eval() {
			return t;
		}

		@Override
		public boolean isSuspend() {
			return false;
		}

	}

	public static class Suspend<T> extends TailCall<T> {

		private final Supplier<TailCall<T>> resume;

		public Suspend(Supplier<TailCall<T>> resume) {
			this.resume = resume;
		}

		@Override
		public TailCall<T> resume() {
			return resume.get();
		}

		@Override
		public T eval() {
			TailCall<T> tailRec = this;
			while (tailRec.isSuspend()) {
				tailRec = tailRec.resume();
			}
			return tailRec.eval();
		}

		@Override
		public boolean isSuspend() {
			return true;
		}
		
	}

}
