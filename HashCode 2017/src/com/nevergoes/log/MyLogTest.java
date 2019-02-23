package com.nevergoes.log;

import java.io.OutputStream;
import java.io.PrintStream;

import com.nevergoes.assertions.MyAsserts;

public class MyLogTest {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		System.out.println("Running test: " + MyLogTest.class.getSimpleName());

		PrintStream errorStream = System.err;
		PrintStream outStream = System.out;

		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {
			}
		}));

		System.setErr(new PrintStream(new OutputStream() {
			public void write(int b) {
			}
		}));

		try {

			MyLog.logLevel = 2;
			MyAsserts.isTrue(MyLog.debug("debug") == 1, "La traza de debug ha fallado para mensajes debug.");
			MyAsserts.isTrue(MyLog.info("info") == 1, "La traza de debug ha fallado para mensajes info.");
			MyAsserts.isTrue(MyLog.error("error") == 1, "La traza de debug ha fallado para mensajes error.");

			MyLog.logLevel = 1;
			MyAsserts.isTrue(MyLog.debug("debug") == 0, "La traza de info ha fallado para mensajes debug.");
			MyAsserts.isTrue(MyLog.info("info") == 1, "La traza de info ha fallado para mensajes info.");
			MyAsserts.isTrue(MyLog.error("error") == 1, "La traza de info ha fallado para mensajes error.");

			MyLog.logLevel = 0;
			MyAsserts.isTrue(MyLog.debug("debug") == 0, "La traza de error ha fallado para mensajes debug.");
			MyAsserts.isTrue(MyLog.info("info") == 0, "La traza de error ha fallado para mensajes info.");
			MyAsserts.isTrue(MyLog.error("error") == 1, "La traza de error ha fallado para mensajes error.");

		} finally {
			System.setOut(outStream);
			System.setErr(errorStream);
		}
	}
}
