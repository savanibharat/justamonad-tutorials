package com.justamonad.tutorials.properties;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Read key values from properties file and return unmodifibale properties. key
 * value format is string=string.
 * 
 * <pre>
 * key1=value1
 * key2=value2
 * </pre>
 */
public final class PropertyReaderStringString {

	private static final String PROPERTY_FILE = "config-qa-string-string.properties";

	public Map<String, String> readProperties() throws IOException {

		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE));
		Map<String, String> map = new HashMap<>();

		for (final String name : properties.stringPropertyNames())
			map.put(name, properties.getProperty(name));

		return Collections.unmodifiableMap(map);
	}

	public static void main(String[] args) {
		PropertyReaderStringString p = new PropertyReaderStringString();
		try {
			Map<String, String> properties = p.readProperties();
			System.out.println(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
