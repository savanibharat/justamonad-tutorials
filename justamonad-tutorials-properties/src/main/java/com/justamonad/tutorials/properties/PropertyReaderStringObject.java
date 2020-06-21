package com.justamonad.tutorials.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Read key values from properties file and return unmodifibale properties. key
 * value format is string=string.
 * 
 * <pre>
 * key1=value1
 * key2=value2
 * </pre>
 */
public final class PropertyReaderStringObject {
	private static final String PROPERTY_FILE = "config-qa-string-object.properties";

	public Map<String, PropObject> readProperties() throws IOException {
		String s = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE), "UTF-8");
		return parseProperties(s);
	}

	private Map<String, PropObject> parseProperties(String json) {

		Map<String, PropObject> propObjects = new HashMap<String, PropObject>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			Iterator iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				String key = ((String) iterator.next()).trim();
				JSONObject values = jsonObject.getJSONObject(key);
				String jsonObjectKey = values.getString("key");
				String jsonObjectValue = values.getString("value");
				List<String> valuesList = getValues(values);
				PropObject propObject = new PropObject(jsonObjectKey, jsonObjectValue, valuesList);
				propObjects.put(key, propObject);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableMap(propObjects);
	}

	private List<String> getValues(JSONObject values) throws JSONException {
		JSONArray jsonArray = values.optJSONArray("value_array");
		List<String> valuesList = new ArrayList<>();
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				valuesList.add(jsonArray.getString(i));
			}
		}
		return valuesList;
	}

	public static void main(String[] args) {
		PropertyReaderStringObject p = new PropertyReaderStringObject();
		try {
			Map<String, PropObject> properties = p.readProperties();
			properties.forEach((k, v) -> System.out.println(k + " = " + v));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
