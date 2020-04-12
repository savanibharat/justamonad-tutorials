package com.justamonad.tutorials.functional.lists;

import static com.justamonad.tutorials.functional.lists.CollectionUtilities.*;

import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class CollectionUtilitiesTest {

	@Test
	public void foldLeftTest() {
		List<Integer> list = list(1, 2, 3, 4, 5);
		Integer result = foldLeft(list, 0, x -> y -> x + y);
		Assert.assertEquals(Integer.valueOf(15), result);
	}

	@Test
	public void foldLeftString() {
		List<Integer> list = list(1, 2, 3, 4, 5);
		String identity = "0";
		Function<String, Function<Integer, String>> f = x -> y -> "(" + x + " + " + y + ")";
		String res = foldLeft(list, identity, f);
		System.out.println(res);
	}
	
	@Test
	public void foldRightString() {
		List<Integer> list = list(1, 2, 3, 4, 5);
		String identity = "0";
		Function<Integer, Function<String, String>> f = x -> y -> "(" + x + " + " + y + ")";
		String res = foldRight(list, identity, f);
		String resRec = foldRightRec(list, identity, f);
		System.out.println(res);
		System.out.println(resRec);
	}

	@Test
	public void prependTest() {
		List<Integer> result = prepend(Integer.valueOf(6), list(1, 2, 3, 4, 5));
		System.out.println(result);
	}
	
	@Test
	public void reverseTest() {
		List<Integer> result = reverse(list(1, 2, 3, 4, 5));
		System.out.println(result);
	}
	
	@Test
	public void mapViaFoldLeftTest() {
		List<Integer> result = mapViaFoldLeft(list(1,2,3,4,5), x->2*x);
		System.out.println(result);
	}
	
}
