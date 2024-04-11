import java.util.*;

public class Assignment2_19_2 {
	ArrayList<String> values = new ArrayList<>();

	int maxSum, thisSum;
	public int array [];
	public int size;

	
	public ArrayList ArrayInfo(int size1) {
		
	maxSum = 0;
	
	array = new int[size1];
	this.size = size1;
	for(int i = 0; i < size; i++) {
		
		array[i] = (int)(Math.random()*100);
	}
	
		for(int j = 0; j < array.length; j++) {
			
			thisSum += array[j];
			
			if(thisSum > maxSum)
				maxSum = thisSum;
			else if(thisSum < 0)
				thisSum = 0;
				
		}
			
			
			
		String x = "Sum; " + Integer.toString(maxSum);
		values.add(x);
		String y = " Start Index: " + Integer.toString(array[1]); 
		values.add(y);
		String z = " End Index: " + Integer.toString(array[size-1]);
		values.add(z);
		
		return values;
	}	
	
}



