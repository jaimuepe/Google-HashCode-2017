package com.nevergoes.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.nevergoes.log.MyLog;

public class MyFiles {

	public static List<String> read(String fileName) {

		final URL fileResource = MyFiles.class.getClassLoader().getResource(fileName);

		if (fileResource == null) {
			MyLog.error("File " + "\"" + fileName + "\"" + " not found!");
			return null;
		}

		try {
			return Files.readAllLines(Paths.get(fileResource.toURI()));
		} catch (IOException | URISyntaxException e) {
			MyLog.error(e);
		}

		return null;
	}
}
