package com.justamonad.tutorials.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class FilesClassFindMethod {

	public static void main(String[] args) throws Exception {
		FilesClassFindMethod f = new FilesClassFindMethod();
		f.find();
	}

	private void find() throws Exception {
		String resourcesPath = getClass().getResource("/Text-Files").toURI().getPath();
		BiPredicate<Path, BasicFileAttributes> matcher = (path, fileAtt) -> (fileAtt.isRegularFile()
				|| fileAtt.isDirectory());
		Files.find(Paths.get(resourcesPath), 1, matcher).collect(Collectors.toList());
	}


}
