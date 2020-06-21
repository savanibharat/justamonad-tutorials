package com.justamonad.tutorials.object.mapping;

import java.io.File;

public class FileUtils {

	public static void main(String[] args) {

		File file = new File("/tempfolder1/merchantLocationFiles");
		if (!file.exists()) {
			System.out.println("Creating folder now");
			if (file.mkdir()) {
				System.out.println("Crated folders");
			} else {
				System.out.println("some problem here.");
			}
		}
		
	}

}
