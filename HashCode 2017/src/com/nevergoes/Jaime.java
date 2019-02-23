package com.nevergoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.nevergoes.objects.Video;

public class Jaime {

	public static void main(String[] args) {

		Video v1 = new Video();
		v1.id = 1;
		v1.size = 30;

		Video v2 = new Video();
		v2.id = 2;
		v2.size = 30;

		Video v3 = new Video();
		v3.id = 3;
		v3.size = 100;

		Video v4 = new Video();
		v4.id = 4;
		v4.size = 40;

		Video v5 = new Video();
		v5.id = 5;
		v5.size = 50;

		int target = 100;

		List<Video> values = Arrays.asList(v1, v2, v3, v4, v5);

//		printStack(getCombinations(Arrays.asList(v1, v2, v3, v4, v5), target));

//		values.sort((v11, v22) -> Integer.compare(v11.size, v22.size));

		List<List<Video>> stacks = new ArrayList<>();

		for (int i = 1; i < (int) Math.pow(2, values.size()); i++) {

			boolean[] bitmap = createBitmap(i, values.size());
			List<Video> combination = calculateResult(values, bitmap);
			if (combination != null) {
				stacks.add(combination);
			}
		}

		printStack(stacks);
	}

	// TODO size

	public static List<List<Video>> getCombinations(List<Video> videos, int size) {

		List<List<Video>> stacks = new ArrayList<>();

		for (int i = 1; i < (int) Math.pow(2, videos.size()); i++) {

			boolean[] bitmap = createBitmap(i, videos.size());
			List<Video> combination = calculateResult(videos, bitmap);
			if (combination != null) {
				stacks.add(combination);
			}
		}

		return stacks;
	}

	private static void printStack(List<List<Video>> stacks) {
		for (List<Video> stack : stacks) {
			System.out.println(stack.stream().map(v -> v.id + "(" + v.size + ")").collect(Collectors.joining(", ")));
		}
	}

	private static boolean[] createBitmap(int value, int arrayLength) {
		boolean[] bits = new boolean[arrayLength];

		int actual;
		for (int i = bits.length - 1; i >= 0; i--) {
			actual = getValue(bits);
			if (actual == value)
				break;
			else if (actual + (int) Math.pow(2, i) <= value)
				bits[i] = true;
		}

		return bits;
	}

	private static int getValue(boolean[] bits) {
		int value = 0;
		for (int i = bits.length - 1; i >= 0; i--)
			if (bits[i])
				value += (int) Math.pow(2, i);

		return value;
	}

	private static List<Video> calculateResult(final List<Video> values, boolean[] used) {

		int total = 0;
		List<Video> combination = new ArrayList<>();
		for (int i = 0; i < used.length; i++) {
			if (used[i]) {
				if (total + values.get(i).size > 100) {
					return null;
				}
				total += values.get(i).size;
				combination.add(values.get(i));
			}
		}
		return combination;
	}
}
