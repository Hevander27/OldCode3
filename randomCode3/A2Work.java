
import java.util.Random;


public class A2Work {
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum1(int N)//f(N) =  6N + 4; O(N) = N
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 0; i < N; i++)
		{
			sum++;
		}
		System.out.println("OpCount : "+opCount);
		return sum;
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=0*/
		for(int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
					
			sum++; opCount+=2;/*add,assign*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 6
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = 6*N + 4
		// O(N) = N
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum2(int N)//f(N) =  6N^2 + 7N + 4; O(N) = N^2
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				sum++;
			}
		}
		System.out.println("OpCount : "+opCount);
		return sum;
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=0*/
		for(int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			opCount++;/*assign j=0*/
			for(int j = 0; j < N; j++)
			{
				opCount+=2;/*compare j<N success, branch*/
				
				sum++; opCount+=2;/*add,assign*/
				
				opCount+=2;/*add,assign j++*/
				
				// block: f(N) = 6
			}
			opCount+=2;/*compare j<N failure, branch*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 6*N + 7
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = (6*N + 7)*N + 4 = 6N^2 + 7N + 4
		// O(N) = N^2
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum3(int N)//f(N) =  7N^3 + 8N + 4; O(N) = N^3
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N*N; j++)
			{
				sum++;
			}
		}
		System.out.println("OpCount : "+opCount);
		return sum;
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=0*/
		for(int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/			
			
			opCount++;/*assign j=0*/
			for(int j = 0; j < N*N; j++)
			{
				opCount+=3;/*mul N*N, compare j<N*N success, branch*/
				
				sum++; opCount+=2;/*add,assign*/
				
				opCount+=2;/*add,assign j++*/
				
				// block: f(N) = 7
			}
			opCount+=3;/*mul N*N, compare j<N*N failure, branch*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 7*N^2 + 8
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = (7*N^2 + 8)*N + 4 = 7N^3 + 8N + 4
		// O(N) = N^3
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum4(int N)//f(N) =  3N^2 + 7N + 4; O(N) = N^2
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < i; j++)
			{
				sum++;
			}
		}
		System.out.println("OpCount : "+opCount);
		return sum;
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=0*/
		for(int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			opCount++;/*assign j=0*/
			for(int j = 0; j < i; j++)
			{
				opCount+=2;/*compare j<i success, branch*/
				
				sum++; opCount+=2;/*add,assign*/
				
				opCount+=2;/*add,assign j++*/
				
				// block: f(N) = 6
				// average number of times this loop will run: N/2
			}
			opCount+=2;/*compare j<i failure, branch*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 6*(N/2) + 7 = 3*N + 7
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = (3*N + 7)*N + 4 = 3N^2 + 7N + 4
		// O(N) = N^2
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum5(int N)//f(N) =  6N^4 + 8N^3 + 8N; O(N) = N^4
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < i*i; j++)
			{
				for(int k = 0; k < j; k++)
				{
					sum++;
				}
			}
		}
		System.out.println("OpCount : "+opCount);
		return sum;
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=0*/
		for(int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			opCount++;/*assign j=0*/
			for(int j = 0; j < i*i; j++)
			{
				opCount+=3;/*mul i*i, compare j<i*i success, branch*/
				
				opCount++;/*assign k=0*/
				for(int k = 0; k < j; k++)
				{
					opCount+=2;/*compare k<j success, branch*/
					
					sum++; opCount+=2;/*add,assign*/
					
					opCount+=2;/*add,assign k++*/
					
					// block: f(N) = 6
					// estimation of times this loop runs: N
				}
				opCount+=2;/*compare k<j failure, branch*/
				
				opCount+=2;/*add,assign j++*/
				
				// block: f(N) = 6*~N + 8
				// estimation of times this loop runs: N^2
			}
			opCount+=3;/*mul i*i, compare j<i*i failure, branch*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = (6*~N + 8)*~N^2 + 8 = 6*~N^3 + 8*~N^2 + 8
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = (6*~N^3 + 8*~N^2 + 8)*N + 4 ~= 6N^4 + 8N^3 + 8N
		// O(N) = N^4
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static long sum6(int N)//f(N) =  3N^4 + 9.5N^3 + 8N + 4; O(N) = N^4
	{
		/*
		long opCount = 0;
		long sum = 0;
		for(int i = 1; i < N; i++)//i starts at 1 to prevent division error in if statement
		{
			for(int j = 0; j < i*i; j++)
			{
				if(j%i == 0)
				{
					for(int k = 0; k < j; k++)
					{
						sum++;
					}
				}
			}
		}
		System.out.println("OpCount : "+opCount);
		return sum;		
		*/
		
		long opCount = 0;
		long sum = 0; opCount++;/*assign*/
		
		opCount++;/*assign i=1*/
		for(int i = 1; i < N; i++)//i starts at 1 to prevent division error in if statement
		{
			opCount+=2;/*compare i<N success, branch*/
			
			opCount++;/*assign j=0*/
			for(int j = 0; j < i*i; j++)
			{
				opCount+=3;/*mul i*i, compare j<i*i success, branch*/
				
				opCount+=3;/*mod,compare,branch*/
				if(j%i == 0)
				{
					opCount++;/*assign k=0*/
					for(int k = 0; k < j; k++)
					{
						opCount+=2;/*compare k<j success, branch*/
						
						sum++; opCount+=2;/*add,assign*/
						
						opCount+=2;/*add,assign k++*/
						
						// block: f(N) = 6
						// estimation of times this loop will run: N
					}
					opCount+=2;/*compare k<j failure, branch*/
					
					// block: f(N) = 6*~N + 3
					// estimation of times this loop will run: 1 / 2
				}
				
				opCount+=2;/*add,assign j++*/
				
				// block: f(N) = (6*~N + 3)/2 + 8 = 3*~N + 9.5
				// estimation of times this loop will run: N^2
			}
			opCount+=3;/*mul i*i, compare j<i*i failure, branch*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = (3*~N + 9.5)*(~N^2) + 8 = 3*~N^3 + 9.5*~N^2 + 8
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		// block: f(N) = (3*~N^3 + 9.5*~N^2 + 8)*N + 4 ~= 3N^4 + 9.5N^3 + 8N + 4
		// O(N) = N^4
		
		System.out.println("OpCount : "+opCount);
		return sum;
	}
	/*
	 * Grading:
	 * Correctly follow the described algorithm to complete the method - 1.5pt
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static int[] algorithm1(int N)//f(N) =  3/2*N^3 + 7/2*N^2 + 4*N + 6; O(N) = N^3
	{
		long opCount = 0;
		int[] arr = new int[N];
		/*
		 * Use the following method to fill the array
		 * For each position in the array, generate a random number between zero and N
		 * - If N = 10, random numbers should be 0-9
		 * Check if that random number is used in any previous position in the array
		 * - If it is used anywhere else, generate a new number and try again
		 * - If it is not used anywhere else, place it into the position and move forward
		 */
		/*
		Random rand = new Random();
		for (int i = 0; i < N; i++)
		{
			TryAgain: while (true)
			{
				int value = rand.nextInt(N);
				for (int j = 0; j < i; j++)
					if (value == arr[j]) 
						continue TryAgain;
				arr[i] = value;
				break;
			}
		}
		*/
		Random rand = new Random(); opCount+=3;/*allocate,construct,assign*/
		
		opCount++;/*assign i=0*/
		for (int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			TryAgain: while (true)
			{
				int value = rand.nextInt(N); opCount+=2;/*call,assign*/
				
				opCount++;/*assign j=0*/
				for (int j = 0; j < i; j++)
				{
					opCount+=2;/*compare j<i success, branch*/
					
					opCount+=3;/*access,compare,branch*/
					if (value == arr[j]) 
					{
						opCount++;/*jump*/ 
						continue TryAgain;
						
						// block: f(N) = 1
						// average times this block runs: ~0
					}
					
					opCount+=2;/*add,assign j++*/
					
					// block: f(N) = 6 + 1*~0 = ~6
					// average number of times this loop runs: N/2
				}
				opCount+=2;/*compare j<i success, branch*/
				
				arr[i] = value; opCount++;/*assign*/
				
				opCount++;/*jump*/
				break;
				
				// block: f(N) = ~6*(N/2) + 7 = ~3*N + 7
				// estimation of times this loop runs: ~N/2
			}
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = (~3*N + 7)*(~N/2) + 4 = ~3/2*N^2 + ~7/2*N + 4
			// times this loop runs: N
		}
		opCount+=2;/*compare i<N failure, branch*/
				
		// block: f(N) = (~3/2*N^2 + ~7/2*N + 4)*N + 6 ~= 3/2*N^3 + 7/2*N^2 + 4*N + 6
		// O(N) = N^3
		
		System.out.println("OpCount : "+opCount);
		return arr;
	}
	/*
	 * Grading:
	 * Correctly follow the described algorithm to complete the method - 1.5pt
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static int[] algorithm2(int N)//f(N) =  3N^2 + 4N + 6; O(N) = N^2
	{
		long opCount = 0;
		int[] arr = new int[N];
		boolean[] used = new boolean[N];
		/*
		 * Use the following method to fill the array
		 * For each position in the array, generate a random number between zero and N
		 * - If N = 10, random numbers should be 0-9
		 * Check if that used[random] is true
		 * - If it is, generate a new number and try again
		 * - If it is not, place it into the position, set used[random] = true, and move forward
		 */
		/*
		Random rand = new Random();
		for (int i = 0; i < N; i++)
		{
			while (true)
			{
				int value = rand.nextInt(N);
				if (!used[value]) 
				{
					arr[i] = value;
					used[value] = true;
					break;
				}
			}
		}
		*/
		Random rand = new Random(); opCount+=3;/*allocate,construct,assign*/
		
		opCount++;/*assign i=0*/
		for (int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			while (true)
			{
				int value = rand.nextInt(N); opCount+=2;/*call,assign*/
				
				opCount+=3;/*access,invert,branch*/
				if (!used[value]) 
				{
					arr[i] = value; opCount++;/*assign*/
					used[value] = true; opCount++;/*assign*/
					
					opCount++;/*jump*/
					
					// block: f(N) = 3
					// likelihood of this block: ~0
					break;
				}
				
				opCount++;/*jump*/
				
				// block: f(N) = 3*~0 + 5 + 1*~1 = ~6
				// times this loop runs: ~N/2
			}
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = ~6*~N/2 + 4 = ~3N + 4
			// times this loop runs: N
		}
		opCount+=2;/*compare i<N failure, branch*/		
		
		// block: f(N) = (~3N + 4)*~N + 6 ~= 3N^2 + 4N + 6
		// O(N) = N^2
				
		System.out.println("OpCount : "+opCount);
		return arr;
	}
	/*
	 * Grading:
	 * Correctly follow the described algorithm to complete the method - 1.5pt
	 * Add operation counts - 0.5pt
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public static int[] algorithm3(int N)//f(N) =  16N + 9; O(N) = N
	{
		long opCount = 0;
		int[] arr = new int[N];
		/*
		 * Use the following method to fill the array
		 * Fill the arr with zero to N-1 in order
		 * Run a loop through each position
		 * - For each position, swap that position and a randomly chosen position
		 */
		/*
		for (int i = 0; i < N; i++)
			arr[i] = i + 1;
		Random rand = new Random();
		for (int i = 0; i < N; i++)
		{
			int swapping = arr[i];
			int swapto = rand.nextInt(N);
			arr[i] = arr[swapto];
			arr[swapto] = swapping;			
		}
		*/
		opCount++;/*assign i=0*/
		for (int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			arr[i] = i; opCount++;/*assign*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 5
		}
		opCount+=2;/*compare i<N failure, branch*/
		
		Random rand = new Random(); opCount+=3;/*allocate,construct,assign*/
		
		opCount++;/*assign i=0*/
		for (int i = 0; i < N; i++)
		{
			opCount+=2;/*compare i<N success, branch*/
			
			int swapping = arr[i]; opCount+=2;/*access,assign*/
			int swapto = rand.nextInt(N); opCount+=2;/*call,assign*/
			arr[i] = arr[swapto]; opCount+=2;/*access,assign*/
			arr[swapto] = swapping; opCount++;/*assign*/
			
			opCount+=2;/*add,assign i++*/
			
			// block: f(N) = 11
		}
		opCount+=2;/*compare i<N failure, branch*/
				
		// block: f(N) = 5*N + 11*N + 9 = 16N + 9
		// O(N) = N
		
		System.out.println("OpCount : "+opCount);
		return arr;
	}
}
