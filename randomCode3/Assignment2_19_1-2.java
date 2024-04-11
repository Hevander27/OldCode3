package main.java;

public class Assignment2_19_1 {

	public int array [];
	public int size;
	
	private String[] values = new String[3];
	
	public String[] arrayInfo(int size1) {
		int maxSum = 0;
		array = new int[size1];
		this.size = size1;
		for(int i = 0; i < size; i++) { array[i] = (int)(Math.random()*100); }
		for(int i = 0; i < array.length; i++) {
			int thisSum = 0;
			for(int j = i; j < array.length; j++) {
				thisSum += array[j];
				if(thisSum > maxSum) maxSum = thisSum;
			}
		}
		
		String x = "Sum: " + maxSum;
		values[0] = x;
		String y = " Start Index: " + array[1]; 
		values[1] = y;
		String z = " End Index: " + array[size-1];
		values[2] = z;
		
		return values;
	}
}
