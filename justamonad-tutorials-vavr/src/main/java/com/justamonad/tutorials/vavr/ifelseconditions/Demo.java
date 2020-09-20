package com.justamonad.tutorials.vavr.ifelseconditions;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;

import java.util.HashMap;


public class Demo {

	public static void main(String[] args) {
		
		int i = 1;
		
		HashMap<String, String> map = Match(i).of(
			    Case($(is(1)), () -> new HashMap<>()),
			    Case($(is(2)), () -> new HashMap<>()),
			    Case($(), () -> new HashMap<>())
			);
		System.out.println(map);
	}

	static void methodOne(String str) {
		System.out.println(str);
	}

	static void methodTwo(String str) {

	}

	static void methodThree(String str) {

	}
}
