package com.justamonad.tutorials.vavr.ifelseconditions;

import io.vavr.control.Option;

public class AddOneDemo {

	public Option<Integer> addOne(Integer value) {
		if(value == null) return Option.none();
		return Option.some(value + 1);
	}
	
	public static void main(String[] args) {
		
	}
	
}
