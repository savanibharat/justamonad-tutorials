package com.justamonad.tutorials.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SetInterfaceAdditions {

	@Test
	void testRemoveIf() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		System.out.println(names);
		names.removeIf(name -> name.startsWith("W"));

		System.out.println(names);

	}

	@Test
	void testRemoveIfPreJava8() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		for (Iterator<String> iter = names.iterator(); iter.hasNext();) {
			if (iter.next().startsWith("W")) {
				iter.remove();
			}
		}

		System.out.println(names);

	}

	@Test
	void testForEachLambda() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		names.forEach(name -> System.out.println(name));
	}

	@Test
	void testForEachMethodReference() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		names.forEach(System.out::println);
	}

	@Test
	void testForEachPreJava8() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		for (String name : names) {
			System.out.println(name);
		}
	}

	@Test
	void testStream() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		// you are streaming on Set
		names.stream().filter(name -> !name.startsWith("W")).forEach(System.out::println);

	}

	@Test
	void testStreamWithCollectorsLambdaExpressions() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		Map<String, Integer> nameWithLengths = names.stream()
				.collect(Collectors.toMap(name -> name, name -> name.length()));
		System.out.println(nameWithLengths);
	}

	@Test
	void testStreamWithCollectorsMethodReferences() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		// name -> name is an identity function.
		// name -> name.length() can be replaced by method references
		Map<String, Integer> nameWithLengths = names.stream()
				.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(nameWithLengths);
	}

	@Test
	void testParallelStream() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		// you are streaming on Set
		names.parallelStream().filter(name -> name.startsWith("W")).forEach(System.out::println);

	}

	@Test
	void testParallelStreamWithCollectorsLambdaExpressions() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		Map<String, Integer> nameWithLengths = names.parallelStream()
				.collect(Collectors.toMap(name -> name, name -> name.length()));
		System.out.println(nameWithLengths);
	}

	@Test
	void testParallelStreamWithCollectorsMethodReferences() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		// name -> name is an identity function.
		// name -> name.length() can be replaced by method references
		Map<String, Integer> nameWithLengths = names.parallelStream()
				.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(nameWithLengths);
	}

	@Test
	void testSpliterator() {

		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		Spliterator<String> spliterator = names.spliterator();
		Map<String, Integer> nameWithLengths = StreamSupport.stream(spliterator, false)
				.collect(Collectors.toMap(name -> name, name -> name.length()));
		System.out.println(nameWithLengths);
	}

	@Test
	void testToArray() {

		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		String[] usingLambda = names.toArray(val -> new String[val]);
		System.out.println(Arrays.toString(usingLambda));

		// Use method reference instead of lambda.
		// Intension of behavior is clearer than lambda expression.
		String[] usingMethodRefs = names.toArray(String[]::new);
		System.out.println(Arrays.toString(usingMethodRefs));
	}

	@Test
	void testToArrayPreJava8() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");

		String[] namesArr1 = names.toArray(new String[0]);
		System.out.println(Arrays.toString(namesArr1));

		Object[] namesArr2 = names.toArray();
		System.out.println(Arrays.toString(namesArr2));
	}

	@Test
	void testCopyOfStaticFactory() {
		Set<String> names = new HashSet<>();
		names.add("Woolfield");
		names.add("Locke");
		names.add("Waterman");
		names.add("Lightfoot");
		names.add("Moss");
		names.add("Wells");
		
		Set<String> copyOf = Set.copyOf(names);
		
		Assert.assertEquals(names, copyOf);
		
	}

	@Test
	void testStaticFactories() {
		Set.of();

		Set.of("Liam");

		Set.of("Liam", "Emma");

		Set.of("Liam", "Emma", "Noah");

		Set.of("Liam", "Emma", "Noah", "Olivia");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava",
				"James");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava",
				"James", "Isabella");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava",
				"James", "Isabella", "Oliver");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava",
				"James", "Isabella", "Oliver", "Sophia");

		Set.of("Liam", "Emma", "Noah", "Olivia", "William", "Ava",
				"James", "Isabella", "Oliver", "Sophia", "Benjamin");
	}

}
