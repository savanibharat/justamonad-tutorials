package com.justamonad.tutorials.spring.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CollConstructorInjection {

	private final Set<String> countries;
	private final List<String> names;
	private final Map<String, String> symbols;

	@Inject
	public CollConstructorInjection(
			@Named("allCountries") Set<String> countries, 
			@Named("names") List<String> names,
			@Named("specialSymbols") Map<String, String> symbols) {
		this.countries = countries;
		this.names = names;
		this.symbols = symbols;
	}

	public void printCountries() {
		System.out.println(countries);
	}

	public void printNames() {
		System.out.println(names);
	}

	public void printSymbols() {
		System.out.println(symbols);
	}

}
