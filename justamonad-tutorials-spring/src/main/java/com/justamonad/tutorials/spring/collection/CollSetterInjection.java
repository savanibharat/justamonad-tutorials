package com.justamonad.tutorials.spring.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CollSetterInjection {

	private Set<String> countries;
	private List<String> names;
	private Map<String, String> symbols;

	@Inject
	@Named("allCountries")
	public void setCountries(Set<String> countries) {
		this.countries = countries;
	}

	@Inject
	@Named("names")
	public void setCountries(List<String> names) {
		this.names = names;
	}

	@Inject
	@Named("specialSymbols")
	public void setSymbols(Map<String, String> symbols) {
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
