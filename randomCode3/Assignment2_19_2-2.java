package main.java;

public class Assignment2_19_2 {

	public int array [];
	public int size;
	
	private String[] values = new String[3];

	public String[] arrayInfo(int size1) {
		int maxSum, thisSum = maxSum = 0;
		array = new int[size1];
		this.size = size1;
		for(int i = 0; i < size; i++) { array[i] = (int)(Math.random()*100); }	
		for(int j = 0; j < array.length; j++) {
			thisSum += array[j];
			if(thisSum > maxSum) maxSum = thisSum;
			else if(thisSum < 0) thisSum = 0;
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



