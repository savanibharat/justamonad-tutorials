package com.justamonad.tutorials.spring.objectmapper;

import javax.inject.Inject;
import javax.inject.Named;

import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class ReadMultipleObjectMapperUsingnNamed {

	private final ObjectMapper readObjectMapper;
	private final ObjectMapper writeObjectMapper;

	@Inject
	public ReadMultipleObjectMapperUsingnNamed(
			@Named("readOM") ObjectMapper readObjectMapper,
			@Named("writeOM") ObjectMapper writeObjectMapper) {
		this.readObjectMapper = readObjectMapper;
		this.writeObjectMapper = writeObjectMapper;
	}

	public void me() {
		System.out.println(readObjectMapper.hashCode());
		System.out.println(writeObjectMapper.hashCode());
	}

}
