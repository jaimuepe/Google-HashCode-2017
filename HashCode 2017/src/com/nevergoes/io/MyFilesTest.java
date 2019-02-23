package com.nevergoes.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.nevergoes.assertions.MyAsserts;
import com.nevergoes.log.MyLog;

public class MyFilesTest {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		System.out.println("Running test: " + MyFilesTest.class.getSimpleName());

		try {

			String fileContent = "texto de prueba\r\n" + "segunda fila\r\n" + "tercera fila";

			MyFiles.writeFile("testfile.txt", fileContent);

			List<String> lines = MyFiles.readFile("testfile.txt");
			MyAsserts.isTrue(fileContent.equals(lines.stream().collect(Collectors.joining("\r\n"))),
					"Fileread test failed!");

		} finally {

			try {
				Files.deleteIfExists(Paths.get("resources/testfile.txt"));
			} catch (IOException e) {
				MyLog.error(e);
			}
		}
	}
}
