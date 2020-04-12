package com.justamonad.tutorials.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class FilesClassWalkMethod {

	public static void main(String[] args) throws Exception {
		FilesClassWalkMethod f = new FilesClassWalkMethod();
		f.find();
		f.findAndRead();
	}

	private void find() throws Exception {
		String resourcesPath = getClass().getResource("/Text-Files").toURI().getPath();
		BiPredicate<Path, BasicFileAttributes> matcher = (path, fileAtt) -> (fileAtt.isRegularFile());
		Files.find(Paths.get(resourcesPath), 10, matcher).forEach(System.out::println);
		
	}

	void findAndRead() throws Exception {
		String resourcesPath = getClass().getResource("/Text-Files").toURI().getPath();
		BiPredicate<Path, BasicFileAttributes> matcher = (path, fileAtt) -> (fileAtt.isRegularFile()
				|| fileAtt.isDirectory());
		Files.find(Paths.get(resourcesPath), 10, matcher)
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
