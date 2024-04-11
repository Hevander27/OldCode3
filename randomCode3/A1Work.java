import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class A1Work {
	/*
	 * Grading: 
	 * Create array of N random values: 1pt
	 * Correctly find Kth largest: 2pt
	 */
	public static int kthLargest(int N, int K)
	{
		/*
			values are unique in range [1,N]
			so kthLargest(10,1) should deterministically return 10
			and kthLargest(10,10) should deterministically return 1
		
			kthLargest(1000,500) should return 501
		*/
				
		//create array of N random values
		Random rand = new Random();
		int[] values = new int [N];
		//for (int i = 0; i < N; i++)
		//	values[i] = rand.nextInt(N);
		for (int i = 0; i < N; i++)
			values[i] = i + 1;
		for (int i = 0; i < N; i++)
		{
			int swapping = values[i];
			int swapto = rand.nextInt(N);
			values[i] = values[swapto];
			values[swapto] = swapping;			
		}
		//System.out.println(Arrays.toString(values));
		
		//Find kth largest in array
			//for example, if N = 10 and K = 5, find 5th largest of the 10 random numbers
		Arrays.sort(values);
		
		// K=1 for "1st" will access the last element
		// K=N will access values[0] 
		return values[N-K];//return kth Largest from array of random values
	}
	/*
	 * Grading: 
	 * Used recursion: 1pt (required for any credit)
	 * Followed formula: 2pt
	 * Correct answer: 1pt
	 * Using built in methods to convert to a binary string will receive zero credit
	 */	
	public static int binaryOnes(int X)
	{
		//MUST USE RECURSION FOR CREDIT!
		//MUST USE MATH FORMULAS BELOW FOR CREDIT!
		//X mod 2 tells you if there is a 1 or 0 at that position
		//run X / 2 through function recursively to find next position
		if (X == 0) return 0; // Base case for recursion
		return X % 2 + binaryOnes(X / 2);//return the number of 1's in the binary representation of the number X
	}
	/*
	 * Grading: 1.5 point for completion
	 */
	public static class PerimeterCompare implements Comparator<Rectangle>{

		@Override
		public int compare(Rectangle arg0, Rectangle arg1) {
			// Complete method to compare rectangle perimeter
			return arg0.getPerimeter() - arg1.getPerimeter();
		}
		
	}
	/*
	 * Grading: 1.5 point for completion
	 */
	public static class AreaCompare implements Comparator<Rectangle>{

		@Override
		public int compare(Rectangle arg0, Rectangle arg1) {
			// Complete method to compare rectangle areas
			return arg0.getArea() - arg1.getArea();
		}
		
	}
}

