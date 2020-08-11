package com.justamonad.tutorials.annotation;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testStudent() throws JsonSerializeException {
		JsonSerializer jsonSerializer = new JsonSerializer();
		Student student = new Student().withFirstName("John").withLastName("Doe")
				.withMiddleName("Client");
		String str = jsonSerializer.serialize(student);
		System.out.println(str);
	}

}
