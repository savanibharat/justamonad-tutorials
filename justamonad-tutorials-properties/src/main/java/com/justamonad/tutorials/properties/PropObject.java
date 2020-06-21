package com.justamonad.tutorials.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PropObject {

	private final String key;
	private final String value;
	private final List<String> values;

	public PropObject(String key, String value, List<String> values) {
		this.key = key;
		this.value = value;
		this.values = Collections.unmodifiableList(new ArrayList<String>(values));
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public List<String> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "PropObject [key=" + key + ", value=" + value + ", values=" + values + "]";
	}

}
