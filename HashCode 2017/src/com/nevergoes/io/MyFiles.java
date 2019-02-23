package com.nevergoes.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.nevergoes.log.MyLog;

public class MyFiles {

	public static List<String> readFile(String fileName) {

		final Path fileResource = Paths.get("resources/" + fileName);

		if (fileResource == null) {
			MyLog.error("File " + "\"" + fileName + "\"" + " not found!");
			return null;
		}

		try {
			return Files.readAllLines(fileResource);
		} catch (IOException e) {
			MyLog.error(e);
		}

		return null;
	}

	public static void writeFile(String fileName, String text) {
		if (fileName == null) {
			MyLog.error("You must provide a fileName");
		} else if (text == null) {
			MyLog.error("You must provide a text to write the new file.");
		}

		try {
			Files.write(Paths.get("resources/" + fileName), text.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}