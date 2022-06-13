package com.justamonad.tutorials.spring.rest.library;

public final class Book {

	private final String bookName;
	private final String author;

	public Book(String bookName, String author) {
		this.bookName = bookName;
		this.author = author;
	}

	public String getBookName() {
		return bookName;
	}

	public String getAuthor() {
		return author;
	}

}
