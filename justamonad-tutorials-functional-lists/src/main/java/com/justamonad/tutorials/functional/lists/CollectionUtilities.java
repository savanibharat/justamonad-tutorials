package com.justamonad.tutorials.functional.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

public final class CollectionUtilities {

	public static <T> List<T> list() {
		return Collections.emptyList();
	}

	public static <T> List<T> list(T t) {
		return Collections.singletonList(t);
	}

	public static <T> List<T> list(List<T> ts) {
		return Collections.unmodifiableList(new ArrayList<>(ts));
	}

	@SafeVarargs
	public static <T> List<T> list(T... t) {
		return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
	}

	public static <T> T head(List<T> list) {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("head of empty list");
		}
		return list.get(0);
	}

	/**
	 * Returns mutable copy hence it is private. The public counter part if
	 * {@link CollectionUtilities#list(List)}
	 */
	private static <T> List<T> copy(List<T> list) {
		return new ArrayList<T>(list);
	}

	/**
	 * Returns List<T> after removing first element.
	 */
	public static <T> List<T> tail(List<T> list) {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("tail of empty list");
		}
		List<T> workCopy = copy(list);
		workCopy.remove(0);
		return Collections.unmodifiableList(workCopy);
	}

	public static <T> List<T> append(List<T> list, T element) {
		List<T> workCopy = copy(list);
		workCopy.add(element);
		return Collections.unmodifiableList(workCopy);
	}

	public static <T, U> U foldLeft(List<T> list, U identity, Function<U, Function<T, U>> f) {
		U result = identity;
		for (T t : list) {
			result = f.apply(result).apply(t);
		}
		return result;
	}

	public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f) {
		ListIterator<T> iter = ts.listIterator(ts.size());
		U result = identity;
		while (iter.hasPrevious()) {
			result = f.apply(iter.previous()).apply(result);
		}
		return result;
	}

	public static <T, U> U foldRightRec(List<T> ts, U identity, Function<T, Function<U, U>> f) {
		return ts.isEmpty() ? identity : f.apply(head(ts)).apply(foldRightRec(tail(ts), identity, f));
	}

	public static <T> List<T> prepend(T t, List<T> list) {
		return foldLeft(list, list(t), a -> b -> append(a, b));
	}

	public static <T> List<T> reverse(List<T> list) {
		return foldLeft(list, list(), a -> b -> prepend(b, a));
	}

	public static <T, R> List<R> mapViaFoldLeft(List<T> list, Function<T, R> f) {
		return foldLeft(list, list(), x -> y -> append(x, f.apply(y)));
	}

	public static List<Integer> range(int start, int end) {
		List<Integer> result = new ArrayList<>();
		int temp = start;
		while (temp < end) {
			result = append(result, temp);
			temp = temp + 1;
		}
		return Collections.unmodifiableList(result);
	}

	public static <T> List<T> unfold(T seed, Function<T, T> f, Function<T, Boolean> p) {
		List<T> result = new ArrayList<>();
		T temp = seed;
		while (p.apply(temp)) {
			result = append(result, temp);
			temp = f.apply(temp);
		}
		return Collections.unmodifiableList(result);
	}

}
