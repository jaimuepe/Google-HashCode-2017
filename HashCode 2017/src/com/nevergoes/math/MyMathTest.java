package com.nevergoes.math;

import java.awt.Point;

import com.nevergoes.assertions.MyAsserts;

public class MyMathTest {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		System.out.println("Running test: " + MyMathTest.class.getSimpleName());

		MyAsserts.isTrue(MyMath.manhattanDistance(0, 0, 0, 0) == 0, "Manhattan distance test failed!");
		MyAsserts.isTrue(MyMath.manhattanDistance(new Point(0, 0), new Point(0, 0)) == 0,
				"Manhattan distance test failed!");

		// - - - - -
		// - - - - -
		// - - - - -
		// 1 2 - - -
		// - - - - -
		MyAsserts.isTrue(MyMath.manhattanDistance(0, 1, 1, 1) == 1, "Manhattan distance test failed!");
		MyAsserts.isTrue(MyMath.manhattanDistance(new Point(0, 1), new Point(1, 1)) == 1,
				"Manhattan distance test failed!");

		// - - - 2 -
		// - - - - -
		// - - - - -
		// - 1 - - -
		// - - - - -
		MyAsserts.isTrue(MyMath.manhattanDistance(1, 1, 3, 4) == 5, "Manhattan distance test failed!");
		MyAsserts.isTrue(MyMath.manhattanDistance(new Point(1, 1), new Point(3, 4)) == 5,
				"Manhattan distance test failed!");

		// 1 - - - -
		// - - - - -
		// - - - - -
		// - - - - -
		// - - - - 2
		MyAsserts.isTrue(MyMath.manhattanDistance(0, 4, 4, 0) == 8, "Manhattan distance test failed!");
		MyAsserts.isTrue(MyMath.manhattanDistance(new Point(0, 4), new Point(4, 0)) == 8,
				"Manhattan distance test failed!");

		MyAsserts.isTrue(MyMath.clamp(3.0f, 0.0f, 5.0f) == 3.0f, "Clamp test failed!");
		MyAsserts.isTrue(MyMath.clamp(3.0f, 0.0f, 1.0f) == 1.0f, "Clamp test failed!");
		MyAsserts.isTrue(MyMath.clamp(3.0f, 4.0f, 8.0f) == 4.0f, "Clamp test failed!");
	}
}
