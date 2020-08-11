package com.justamonad.tutorials.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSerializer {

	public String serialize(Object obj) throws JsonSerializeException {

		try {
			Class<?> clazz = obj.getClass();
			Map<String, String> map = new HashMap<>();

			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(JsonField.class)) {
					map.put(getSerializedKey(field), (field.get(obj)).toString());
				}
			}
			return toJsonString(map);
		} catch (IllegalAccessException e) {
			throw new JsonSerializeException(e.getMessage());
		}
	}

	private static String getSerializedKey(Field field) {
		String annotationValue = field.getAnnotation(JsonField.class).value();
		if (annotationValue.isEmpty()) {
			return field.getName();
		} else {
			return annotationValue;
		}
	}

	private String toJsonString(Map<String, String> jsonMap) {
		String elementsString = jsonMap.entrySet().stream()
				.map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(","));
		return "{" + elementsString + "}";
	}

}
