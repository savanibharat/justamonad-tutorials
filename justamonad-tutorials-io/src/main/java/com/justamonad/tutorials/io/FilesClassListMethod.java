package com.justamonad.tutorials.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FilesClassListMethod {

	public static void main(String[] args) throws Exception {
		FilesClassListMethod f = new FilesClassListMethod();
		f.getTextFiles();
		f.listFilePaths();
	}

	/**
	 * list method returns the content of directory passed in parameter. The
	 * contents are returned lazily in Stream object that contains Path to all
	 * the contents in directory.
	 * 
	 * The listing returned in Stream is not recursive. Which means that the
	 * listing provided will not navigate directory/directories.
	 * 
	 * To walk into multiple directories from this directory you will need to
	 * use Files.walk method. That will be covered in different article.
	 * 
	 */
	private void listFilePaths() throws Exception {
		String path = FilesClassListMethod.class.getResource("/Text-Files").toURI().getPath();
		Files.list(Paths.get(path)).filter(Files::isRegularFile).forEach(System.out::println);
	}

	static void isRegularFile() throws Exception {
		Files.list(Paths.get(".")).filter(Files::isRegularFile).forEach(System.out::println);
	}
	
	void getTextFiles() throws Exception {
		String resourcesPath = FilesClassListMethod.class.getResource("/Text-Files").toURI().getPath();
		Files.list(Paths.get(resourcesPath))
			.filter(predicate("txt"))
			.flatMap(path -> uncheckedReading(path).stream())
			.forEach(System.out::println);
	}

	private List<String> uncheckedReading(Path path) {
		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	Predicate<Path> predicate(String extension) {
		return path -> path.getFileName().toString().endsWith(extension);
	}

}
