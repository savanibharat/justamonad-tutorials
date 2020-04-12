package com.justamonad.tutorials.functional.lists;

import static com.justamonad.tutorials.functional.lists.tailcall.TailCall.ret;
import static com.justamonad.tutorials.functional.lists.tailcall.TailCall.suspend;

import com.justamonad.tutorials.functional.lists.tailcall.TailCall;

public abstract class List<A> {

	public abstract A head();

	public abstract List<A> tail();

	public abstract boolean isEmpty();

	public abstract List<A> setHead(A a);

	@SuppressWarnings("rawtypes")
	public static final List NIL = new Nil<>();

	private List() {
	}

	public static final class Nil<A> extends List<A> {

		@Override
		public A head() {
			throw new IllegalStateException("head of NIL.");
		}

		@Override
		public List<A> tail() {
			throw new IllegalStateException("tail of NIL.");
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public List<A> setHead(A a) {
			throw new IllegalStateException("setHead of NIL.");
		}

		public String toString() {
			return "[NIL]";
		}

	}

	public static final class Cons<A> extends List<A> {

		private final A head;
		private final List<A> tail;

		private Cons(A head, List<A> tail) {
			this.head = head;
			this.tail = tail;
		}

		@Override
		public A head() {
			return head;
		}

		@Override
		public List<A> tail() {
			return tail;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public List<A> setHead(A a) {
			if (isEmpty()) {
				throw new IllegalStateException("setHead called in empty list.");
			}
			return new Cons<A>(a, tail());
		}

		/**
		 * add element at beginning of the List.
		 */
		public List<A> cons(A a) {
			return new Cons<A>(a, this);
		}
		
		public String toString() {
			return String.format("[%sNIL]", toString(new StringBuilder(), this).eval());
		}

		private TailCall<StringBuilder> toString(StringBuilder accumulator, List<A> list) {
			return list.isEmpty()
					? ret(accumulator)
					: suspend(() -> toString(accumulator.append(list.head()).append(", "), list.tail()));
		}

	}

	@SuppressWarnings("unchecked")
	public static <A> List<A> list() {
		return NIL;
	}

	@SafeVarargs
	public static <A> List<A> list(A... a) {
		List<A> list = list();
		for (int i = a.length - 1; i >= 0; i--) {
			list = new Cons<A>(a[i], list);
		}
		return list;
	}

}
