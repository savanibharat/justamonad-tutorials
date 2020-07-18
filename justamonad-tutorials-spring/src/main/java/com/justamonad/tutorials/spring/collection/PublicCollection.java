package com.justamonad.tutorials.spring.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PublicCollection {

	private PublicCollection() {
	}

	public static final Set<String> COUNTRIES;
	static {
		Set<String> countries = new HashSet<>();
		countries.add("Netherlands");
		countries.add("France");
		countries.add("United States");
		countries.add("Canada");
		COUNTRIES = Collections.unmodifiableSet(countries);
	}

	public static final List<String> NAMES;
	static {
		List<String> names = new ArrayList<>();
		names.add("Ava");
		names.add("Emma");
		names.add("Benjamin");
		names.add("Bruce");
		NAMES = Collections.unmodifiableList(names);
	}

	public static final Map<String, String> SYMBOLS;
	static {
		Map<String, String> symbols = new HashMap<>();
		symbols.put("%", "Percent");
		symbols.put("*", "Asterisk");
		symbols.put("~", "Tilde");
		symbols.put("$", "Dollar");
		symbols.put("#", "Octothorpe");
		SYMBOLS = Collections.unmodifiableMap(symbols);
	}

}
