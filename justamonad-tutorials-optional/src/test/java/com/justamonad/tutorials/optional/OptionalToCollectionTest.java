package com.justamonad.tutorials.optional;

import static java.util.Collections.singletonMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;
import org.justamonad.tutorials.optional.functional.Option;

public class OptionalToCollectionTest {

	private static class Party {
		private PartyEmailAddress address;

		public Party(PartyEmailAddress address) {
			this.address = address;
		}

		public PartyEmailAddress getAddress() {
			return address;
		}
	}

	private static class PartyEmailAddress {
		private String address;

		public PartyEmailAddress(String address) {
			this.address = address;
		}

		public String getEmailAddress() {
			return address;
		}
	}

	@Test
	public void optionMap() {
		Party party = new Party(new PartyEmailAddress("email"));

		System.out.println(
				Option.some(party).map(Party::getAddress).map(PartyEmailAddress::getEmailAddress).getOrThrow());

		party = new Party(null);
		System.out.println(
				Optional.of(party).map(Party::getAddress).map(PartyEmailAddress::getEmailAddress).orElse("empty"));

	}

	@Test
	public void testMap() {

		collectToList(Optional.of("abc"));
		collectToList(Optional.ofNullable(null));

		collectToMap(Optional.of("abc"));
		collectToMap(Optional.ofNullable(null));

		Optional<String> optional = Optional.of("abc");

		collect(optional, Collectors.toList());
		collect(optional, collectingAndThen(toList(), Collections::unmodifiableList));

		collect(optional, Collectors.toSet());
		collect(optional, collectingAndThen(toSet(), Collections::unmodifiableSet));

		collect(optional, Collectors.toMap(identity(), identity()));
		collect(optional, collectingAndThen(toMap(identity(), identity()), Collections::unmodifiableMap));

	}

	/**
	 * If the value is present in Optional then we create a SingletonList with
	 * that value else we return emptyList.
	 */
	static List<String> collectToList(Optional<String> optString) {
		return optString.map(Collections::singletonList).orElseGet(Collections::emptyList);
	}

	/**
	 * If the value is present in Optional then we create a SingletonMap with
	 * that value else we return emptyMap. The returned Map will have same key
	 * and value.
	 */
	static Map<String, String> collectToMap(Optional<String> optString) {
		return optString.map(s -> singletonMap(s, s)).orElseGet(Collections::emptyMap);
	}

	/**
	 * In order to leverage existing interface for Collecting data into
	 * container we can use Collector interface.
	 * 
	 * It is great way to collect data as we can pass a behavior of our
	 * container instead of writing toList, toSet, toMap, etc methods of our
	 * own.
	 *
	 * Get the mutable result container from the Supplier. A a =
	 * collector.supplier().get()
	 * 
	 * If value is present in Optional then just insert that value in mutable
	 * result container. optional.ifPresent(value ->
	 * collector.accumulator().accept(a, value));
	 * 
	 * Convert intermediate result container into result container and return
	 * it. collector.finisher().apply(a)
	 * 
	 */
	static <A, T, R> R collect(Optional<T> optional, Collector<T, A, R> collector) {
		A a = collector.supplier().get();
		optional.ifPresent(value -> collector.accumulator().accept(a, value));
		return collector.finisher().apply(a);
	}

}
