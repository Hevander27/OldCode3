package main.java;

import java.util.*;

public class Assignment2_8_2 {

	public static int[] alg1Array;
	public static int[] alg2Array;
	public static int[] alg3Array;

	public static int[] alg1InputSet = {250, 500, 1000, 2000};
    public static int[] alg2InputSet = {25000, 50000, 100000, 200000, 400000, 800000};
	public static int[] alg3InputSet = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000};

	public static void main(String[] args) {
		System.out.println(runInput(alg1InputSet));
		System.out.println(runInput(alg2InputSet));
		System.out.println(runInput(alg3InputSet));
	}

	public static String runInput(int[] input) {
		String result = "";
		double[] sums = new double[input.length];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < input.length; j++) {
				if (i == 0) {
				    result += String.format("N: %-12d\t\t", input[j]);
				}
				else if (i == 11) {
					double avg = (sums[j] / 10);
					result += String.format("Avg: %12.10fs\t", avg);
				}
				else {
					int n = input[j];
					long startTime, endTime;
					if (input.equals(alg1InputSet)) {
						startTime = System.nanoTime();
						alg1(n);
						endTime = System.nanoTime();
					}
					else if (input.equals(alg2InputSet)) {
						startTime = System.nanoTime();
						alg2(n);
						endTime = System.nanoTime();
					}
					else if (input.equals(alg3InputSet)) {
						startTime = System.nanoTime();
						alg3(n);
						endTime = System.nanoTime();
					}
					else return "Invalid input";

					double time = (endTime - startTime) / Math.pow(10, 9);
					sums[j] += time;
					result += String.format("%12.10fs\t\t", time);
				}
			}
			result = result.trim() + "\n";
		}
		return result;
	}

	private static void alg1(int N) {
		int[] array = new int[N];
		Random rand = new Random();
		int value = 0, low = 1, high = N + 1;
		for (int i = 0; i < N; i++) {
			int j = i;
			do {
				if (array[j] == value) {
					value = rand.nextInt(high - low) + low;
					j = i;
					continue;
				}
				j--;
			} while (0 < j);
			array[i] = value;
		}
		alg1Array = array;
	}

	private static void alg2(int N) {
		int[] array = new int[N];
		boolean[] used = new boolean[N + 1];
		Random rand = new Random();
		for (int i = 0; i < N; i++) {
			int value = 0;
			do {
				value = rand.nextInt(N) + 1;
			} while (used[value]);
			array[i] = value;
			used[value] = true;
		}
		alg2Array = array;
	}	
	
	private static void alg3(int N) {
		int[] array = new int[N + 1];
		Random rand = new Random();
		for (int i = 0; i < N; i++) array[i] = i + 1;
		for (int i = 1; i <= N; i++) {
			int temp = array[i];
			int ri = rand.nextInt(i);
			array[i] = array[ri];
			array[ri] = temp;
		}
		alg3Array = array;
	}

	public static void displayArray(int[] array) {
		String result = "";
		for (int i = 0; i < array.length; i++) {
		    if (i == array.length - 1)        result += array[i] + "\n";
			else if ((i + 1) % 10 == 0)       result += array[i] + "\n";
			else if (array.equals(alg1Array)) result += String.format("%-6d", array[i]);
			else if (array.equals(alg2Array)) result += String.format("%-8d", array[i]);
			else if (array.equals(alg3Array)) result += String.format("%-9d", array[i]);
		} 
		System.out.println(result);
	}
}	
