package org.justamonad.tutorials.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FilesList {

	public static void main(String[] args) throws Exception {
		Files.list(Paths.get(FilesList.class.getResource("/Text-Files").toURI().getPath()))
		.forEach(System.out::println);
//		displayAll(FilesList.class.getResource("/Text-Files").toURI().getPath(),
//				FilesList.class.getResource("/Text-Files-Copy").toURI().getPath());
	}

	public static void displayAll(String path, String destination) throws Exception {
		//
		List<Path> paths = getAllFilePaths(path);
		for (Path path2 : paths) {
			if (path2.getFileName().toString().endsWith("txt")) {
				System.out.println(destination + "/" + path2.getFileName());
				Files.write(Paths.get(destination + "/" + path2.getFileName()), Files.readAllLines(path2),
						StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			}
		}
	}

	private static List<Path> getAllFilePaths(String path) throws IOException {
		return Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
	}

}
