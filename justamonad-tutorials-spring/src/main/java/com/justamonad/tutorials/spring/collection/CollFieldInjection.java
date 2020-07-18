package com.justamonad.tutorials.spring.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CollFieldInjection {

	@Inject
	@Named("allCountries")
	private Set<String> countries;

	@Inject
	@Named("names")
	private List<String> names;

	@Inject
	@Named("specialSymbols")
	private Map<String, String> symbols;

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
