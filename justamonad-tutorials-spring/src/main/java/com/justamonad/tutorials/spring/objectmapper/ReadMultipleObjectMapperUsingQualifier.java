package com.justamonad.tutorials.spring.objectmapper;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class ReadMultipleObjectMapperUsingQualifier {

	private final ObjectMapper readObjectMapper;
	private final ObjectMapper writeObjectMapper;

	@Inject
	public ReadMultipleObjectMapperUsingQualifier(
			@Qualifier("readOM") ObjectMapper readObjectMapper,
			@Qualifier("writeOM") ObjectMapper writeObjectMapper) {
		this.readObjectMapper = readObjectMapper;
		this.writeObjectMapper = writeObjectMapper;
	}

	public void me() {
		System.out.println(readObjectMapper.hashCode());
		System.out.println(writeObjectMapper.hashCode());
	}

}
