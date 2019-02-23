package com.nevergoes.log;

public class MyLog {

	// 0 = error, 1 = info, 2 = debug
	public static int logLevel = 1;

	public static int debug(String msg) {
		if (logLevel == 2) {
			System.out.println("[DEBUG] " + msg);
			return 1;
		}
		return 0;
	}

	public static int info(String msg) {
		if (logLevel == 1 || logLevel == 2) {
			System.out.println("[INFO] " + msg);
			return 1;
		}
		return 0;
	}

	public static int error(Throwable t) {
		t.printStackTrace(System.err);
		return 1;
	}

	public static int error(String msg) {
		System.err.println("[ERROR] " + msg);
		return 1;
	}
}
