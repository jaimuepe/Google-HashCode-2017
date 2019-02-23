package com.nevergoes.math;

import java.awt.Point;

public class MyMath {

	public static int manhattanDistance(Point p1, Point p2) {
		return manhattanDistance(p1.x, p1.y, p2.x, p2.y);
	}

	public static int manhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static float clamp(float value, float min, float max) {
		return Math.min(max, Math.max(value, min));
	}
}
