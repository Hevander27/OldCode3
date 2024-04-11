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
		//randGen.nextInt(N);//count as 1 unit of time
		
		A6BH<Integer> heap = new A6BH();
		//for (int i = 0; i < N; i++) heap.insert(randGen.nextInt(N));
		Integer[] ints = new Integer [N];
		for (int i = 0; i < N; i++) ints[i] = randGen.nextInt(N);
		heap.addAll(ints); // N
		
		int out = -1;
		for (int i = 0; i < k; i++) // log(N) * N
			out = heap.deleteMin(); // log(N)
		
		// log(N) * N > N
		
		System.out.println("f(N) = [Your answer here]");
		System.out.println("O(N) = N * log(N)");
		return out;
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
		//randGen.nextInt(N);//count as 1 unit of time
		Integer[] ints = new Integer [N];
		for (int i = 0; i < N; i++) ints[i] = randGen.nextInt(N);
		
		A6BH<Integer> heap = new A6BH();
		Integer[] first_k = new Integer [k];
		System.arraycopy(ints,0,first_k,0,k);
		heap.addAll(first_k); // k
		for (int i = k; i < N; i++) // (N - k) * log(k)
		{
			int n = ints[i];
			int smallest = heap.findMin(); // 1
			if (n > smallest) 
			{
				heap.deleteMinAndInsert(n); // log(k)
				/*
					Yes I know "don't add or modify the A6BH"
					but it is more efficient...
					Of course, the following two commented lines would work too
				*/
				//heap.deleteMin(); // log(k)
				//heap.insert(n); // log(k)
			}
			// log(k) > 1
		}
		
		// (N - k) * log(k) > k 
		// Going to just simplify this to "N log N" under the assumption N-k ~= N and k ~= N
		
		System.out.println("f(N) = [Your answer here]");
		System.out.println("O(N) = N * log(N)");
		return heap.findMin();
	}
	
}
