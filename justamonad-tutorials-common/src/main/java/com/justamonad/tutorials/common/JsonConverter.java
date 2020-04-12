package com.justamonad.tutorials.common;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static String toJsonString(Map<String, Object> map) {
		try {
			return OBJECT_MAPPER.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

}
