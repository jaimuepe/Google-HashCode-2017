package com.nevergoes;

import com.nevergoes.io.MyFilesTest;
import com.nevergoes.log.MyLog;
import com.nevergoes.log.MyLogTest;
import com.nevergoes.math.MyMathTest;

public class FullTest {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		try {

			MyFilesTest.test();
			MyMathTest.test();
			MyLogTest.test();

			System.out.println("All tests executed");

		} catch (Exception e) {
			MyLog.error(e);
			MyLog.error("A test failed!");
		}
	}
}
