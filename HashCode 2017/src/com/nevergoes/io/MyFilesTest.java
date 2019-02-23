package com.nevergoes.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.nevergoes.assertions.MyAsserts;

public class MyFilesTest {

	public static void main(String[] args) {
		test();
	}

	public static boolean test() {

		System.out.println("Running test: " + MyFilesTest.class.getSimpleName());

		// Test readFile
		List<String> lines = MyFiles.readFile("testFile.txt");
		MyAsserts.isTrue(lines.size() == 3, "Fileread test failed!");

		// Test writeFile
		MyFiles.writeFile("test.txt", "Testing");
		Path path = Paths.get("resources/test.txt");
		MyAsserts.isTrue(path.toFile().isFile(), "File writing test failed!");

		return true;
	}
}
