package com.justamonad.tutorials.functions;

import java.util.function.Function;

public class HigherOrderFunction {

	Function<Integer, Integer> triple = x -> x * 3;
	Function<Integer, Integer> square = x -> x * x;

	Function<Integer, Function<Integer, Integer>> add = v1 -> v2 -> v1 + v2;

	Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> composeAnonymous = new Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>>() {
		@Override
		public Function<Function<Integer, Integer>, Function<Integer, Integer>> apply(Function<Integer, Integer> f1) {
			return new Function<Function<Integer, Integer>, Function<Integer, Integer>>() {
				@Override
				public Function<Integer, Integer> apply(Function<Integer, Integer> f2) {
					return new Function<Integer, Integer>() {
						@Override
						public Integer apply(Integer arg) {
							return f1.apply(f2.apply(arg));
						}
					};
				}
			};
		}
	};

	Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> composeLambda = 
			x -> y -> z -> x.apply(y.apply(z));

	<T, U, V> 
	Function<Function<U, V>, 
		Function<
			Function<T, U>, 
			Function<T, V>>> higherCompose() {
//		return (Function<U, V> tcFunc) -> (Function<T, U> vtFunct) -> (T arg) -> tcFunc.apply(vtFunct.apply(arg));
		return f -> g -> arg -> f.apply(g.apply(arg));
	}

	public static void main(String[] args) {
		HigherOrderFunction h = new HigherOrderFunction();
//		int val = h.composeAnonymous.apply(h.triple).apply(h.square).apply(10);
		int res1 = h.<Integer, Integer, Integer>higherCompose().apply(h.square).apply(h.triple).apply(10);
		int res2 = h.<Integer, Integer, Integer>higherCompose().apply(h.triple).apply(h.square).apply(10);
		System.out.println(res1);
		System.out.println(res2);
	}

}
