package com.justamonad.tutorials.functional.lists;

import static com.justamonad.tutorials.functional.lists.List.list;

import org.junit.Test;

public class ListTest {

	@Test
	public void test() {
		
		List<Integer> list = list(1,2,3,4,5);
		System.out.println(list);
	}

}
