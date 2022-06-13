package com.justamonad.tutorials.spring.rest.library;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryResourceImpl implements LibraryResource {

	@Override
	public List<Book> getBooks() {
		System.out.println("/v1/library resource called. This will be called once");
		return List.of(new Book("The Lord of the Rings", "J. R. R. Tolkien"));
	}

	@Override
	public void me() {
		
	}

}
