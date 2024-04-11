package main.java;

import main.java.Assignment2_19_1;
import main.java.Assignment2_19_2;

public class Assignment2_19_Driver {

	public static void main(String[] args) {
		assignment2_19(1);
		assignment2_19(2);
	}

	public static void assignment2_19(int part) {
		//Scanner scan = new Scanner(System.in);
		long startTime = 0;
		long endTime = 0;
		String[] values = null;
		//System.out.println("Enter array size: ");
		int size = 10; //scan.nextInt();
		//scan.close();
		switch (part) {
			case 1:
				Assignment2_19_1 obj1 = new Assignment2_19_1();
				startTime = System.nanoTime();
				values = obj1.arrayInfo(size);
				endTime = System.nanoTime();
				break;
			case 2:
				Assignment2_19_2 obj2 = new Assignment2_19_2();
				startTime = System.nanoTime();
				values = obj2.arrayInfo(size);
				endTime = System.nanoTime();
				break;
			default: return;
	    }
		System.out.printf("%-10s%-10s%-10s\n", values[0], values[1], values[2]);
		System.out.printf("Runtime: %5.5f secs\n", ((endTime - startTime) / Math.pow(10, 9)));
	}
}
