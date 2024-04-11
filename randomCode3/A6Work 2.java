package TestFX.Ch6Assignment;

import java.util.Random;

public class A6Work {
	private static Random randGen = new Random();
	/*
	 * Add operation counts to all methods - 3pts
	 */
	
	/*
	 * Grading: 
	 * Create array of N random values: 0.5pt
	 * Create a heap from the values in the array in the most efficient method: 1pt
	 * Remove the k smallest values from the heap: 0.5pt
	 * Return the kth smallest value from the heap: 0.5pt
	 * f(N): 0.75pt
	 * O(N): 0.75pt
	 */
	public static int kthSmallest(int N, int k)
	{
		/*
		 * Create an Array of size N
		 * Fill the array with N random values between 0 and N (duplicates is fine)
		 * Create a heap from the array, ensure this is done in the most efficient way possible
		 * Perform k deleteMin() operations
		 * Return the final value returned from deleteMin() as the kth smallest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		
		Integer[] items = new Integer[N];
		for (int i = 0; i < N; i++) items[i] = randGen.nextInt(N);
		
		int min = 0;
		A6BH<Integer> heap = new A6BH<>(items);
		for (int i = 0; i < k; i++) min = heap.deleteMin();
		
		//randGen.nextInt(N);//count as 1 unit of time
		System.out.println("f(N) = [Your answer here]");
		System.out.println("O(N) = [Your answer here]");
		return min;
	}
	/*
	 * Grading: 
	 * Create array of N random values: 0.5pt
	 * Create a heap of first k values: 1pt
	 * Correctly maintain heap size while adding/removing values: 1pt
	 * Return the kth largest value from the heap: 0.5pt
	 * f(N): 0.5pt
	 * O(N): 0.5pt
	 */
	public static int kthLargest(int N, int k)
	{
		/*
		 * Create an Array of size N
		 * Fill the array with N random values between 0 and N (duplicates is fine)
		 * Create a heap from the first k values in the array
		 * For all other values in the array
		 * - if the value is larger than the smallest in the heap, remove the smallest from the heap and add the larger value
		 * - if the value is smaller than the smallest in the heap, continue to the next value
		 * Return the smallest value in the heap as the kth largest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		
		Integer[] items = new Integer[N];
		for (int i = 0; i < N; i++) items[i] = randGen.nextInt(N);
		
		Integer[] sArray = new Integer[k];
		for (int i = 0; i < k; i++)  sArray[i] = items[i];
		A6BH<Integer> heap = new A6BH<>(sArray);
		
		for (int i = k; i < N; i++) {
			if (items[i].compareTo(heap.findMin()) > 0) {
				heap.deleteMin();
				heap.insert(items[i]);
			}
		}
		
		//randGen.nextInt(N);//count as 1 unit of time
		System.out.println("f(N) = [Your answer here]");
		System.out.println("O(N) = [Your answer here]");
		return heap.deleteMin();
	}
	
}
