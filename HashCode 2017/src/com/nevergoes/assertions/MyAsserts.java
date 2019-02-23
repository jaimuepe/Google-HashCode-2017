package com.nevergoes.assertions;

public class MyAsserts {

	public static void isTrue(boolean condition, String errorMsg) {
		if (!condition) {
			throw new AssertionError(errorMsg);
		}
	}
}
