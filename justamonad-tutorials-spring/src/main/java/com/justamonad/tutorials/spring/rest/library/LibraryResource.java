package com.justamonad.tutorials.spring.rest.library;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/v1/library")
public interface LibraryResource {

	@GetMapping
	List<Book> getBooks();
	
	@PostMapping
	void me();
	
}
