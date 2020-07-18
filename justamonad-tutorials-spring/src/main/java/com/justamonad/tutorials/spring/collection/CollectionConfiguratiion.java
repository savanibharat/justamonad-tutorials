package com.justamonad.tutorials.spring.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectionConfiguratiion {

	@Bean("allCountries")
	public Set<String> countries() {
		Set<String> countries = new HashSet<>();
		countries.add("Netherlands");
		countries.add("France");
		countries.add("United States");
		countries.add("Canada");
		return Collections.unmodifiableSet(countries);
	}

	@Bean("names")
	public List<String> names() {
		List<String> names = new ArrayList<>();
		names.add("Ava");
		names.add("Emma");
		names.add("Benjamin");
		names.add("Bruce");
		return Collections.unmodifiableList(names);
	}

	@Bean("specialSymbols")
	public Map<String, String> symbols() {
		Map<String, String> symbols = new HashMap<>();
		symbols.put("%", "Percent");
		symbols.put("*", "Asterisk");
		symbols.put("~", "Tilde");
		symbols.put("$", "Dollar");
		symbols.put("#", "Octothorpe");
		return Collections.unmodifiableMap(symbols);
	}

}
