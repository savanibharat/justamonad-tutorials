package com.justamonad.tutorials.jackson.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonConverter {

	ObjectMapper mapper = new ObjectMapper();

//	public DomainPerson convert(com.justamonad.tutorials.jackson.APIPerson person) {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		return mapper.convertValue(person, DomainPerson.class);
//	}

	public <T, R> R convert(T inputType, Class<R> outputType) {
		return mapper.convertValue(inputType, outputType);
	}
	
	public static void main(String[] args) {
		PersonConverter converter = new PersonConverter();
		com.justamonad.tutorials.jackson.APIPerson person = new com.justamonad.tutorials.jackson.APIPerson();
		person.setFirstName("John");
		person.setLastName("Doe");
		DomainPerson converted = converter.convert(person, DomainPerson.class);
		System.out.println(converted.getClass());
		System.out.println(converted);
	}

}
