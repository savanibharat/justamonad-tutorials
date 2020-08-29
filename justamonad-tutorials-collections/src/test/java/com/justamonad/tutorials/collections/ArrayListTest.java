package com.justamonad.tutorials.collections;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

	@Test
	public void arrayListNoArgs() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
	}

	@Test
	public void arrayListInitialCapacity() {
		List<String> names = new ArrayList<>(20);
		names.add("John");
		names.add("Jane");
	}

	@Test
	public void arrayListCollection() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Arya");

		List<String> moreNames = new ArrayList<>(names);
		moreNames.add("Ned");
		moreNames.add("Catelyn");

		Assert.assertEquals(4, moreNames.size());
	}

}
