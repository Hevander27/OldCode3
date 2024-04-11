import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class A7Work {
	/*
	 * Grading: 
	 * Search the two-dimensional array for the value and return if it exists in the matrix or not - 1pt
	 * The matrix is guaranteed to have each row/column sorted, and the number of rows/columns are equal (NxN) - see driver for example
	 * f(N) - 0.5pts
	 * O(N) - 0.5pts
	 * opCounts - 0.5pts
	 * compareCounts - 0.5pts
	 * No more than 2N-1 compares - 1pt
	 */
	public static <E extends Comparable<? super E>> boolean contains(E val, E[][] matrix) //throws Exception
	{
		long opCount = 0;
		long compareCount = 0; // operations involving compareCount will not be tracked by opCount
		//Determine if the val exists in the matrix as efficiently as possible
		//increment compareCounts every time you compare val to an position in the matrix
		//for notation, consider the number of rows/columns to be N, so the matrix contains N^2 values (rows/columns always equal)
		
		/*
			This solution assumes:
				A.compareTo(B) == 0 is equivalent to A.equals(B)
			While not guaranteed for every class that could be made,
			for basic things like Integer this should always be true.
		*/
		
		/*
			It's just a binary search.
			Runtime: log(N*N) = log(N^2) = 2*log(N) -> O(log N)
		*/
		
		int dimension = matrix.length; 
			opCount+=2; // access, assign
		int range = dimension * dimension;
			opCount+=2; // mul, assign
		int low = 0;
			opCount++; // assign
		boolean found = false;
			opCount++; // assign
		while (range >= 1)
		{
			opCount++; // compare (succeed)
			
			int mid = low + range / 2;
				opCount+=3; // divide, add, assign
			E item = matrix[mid / dimension][mid % dimension];
				opCount+=5; // divide, access, modulus, access, assign
			int relation = val.compareTo(item);
				compareCount++;
				opCount+=2; // function call, assign
			if (relation < 0)
			{
				opCount++; // compare relation<0
				
				range = mid - low;
					opCount+=2; // subtract, assign
			}
			else if (relation > 0)
			{
				opCount+=2; // compare relation<0, compare relation>0
				
				range += low;
					opCount+=2; // add, assign
				low = mid + 1;
					opCount+=2; // add, assign
				range -= low;
					opCount+=2; // subtract, assign
			}
			else 
			{
				opCount+=2; // compare relation<0, compare relation>0
				
				found = true;
					opCount++; // assign
				break;
			}
		}
		opCount++; // compare (fail)
		
		opCount++; // for the return statement much later
		// printing etc not tracked by opCount
		
		System.out.println("f(N) = [your answer]");
		System.out.println("O(N) = [your answer]");
		System.out.println("opCount = "+opCount);
		// For ensuring that this *always* meets the 2N-1 requirement.
		// It does, and assuming N >= 3 it is always explicitly less than 2N-1.
		/*
		int $2N_1 = dimension * 2 - 1;
		int max_cmp = $2N_1;
		if (dimension >= 3) max_cmp--; // Test hypothesis about never reaching 2N-1 for N >= 3.
		System.out.printf("N = %d, 2N-1 = %d, max_cmp = %d, ",dimension,$2N_1,max_cmp);
		if (compareCount > max_cmp) throw new Exception("too many compares");
		*/
		System.out.println("compareCounts = "+compareCount);
		return found;
	}

	/* 
		The following (class Vat, function radixSortStringsAlpha) 
		are not a direct answer to any part of the assignment7,
		only included because I made them and thought might as well leave them in the file.
	*/
	private static class Vat<el>
	{
		/*
			This is not adding a new data structure to the sort.
			Its job is just to wrap the "int pos" in a class,
			instead of keeping it in a local variable.
			I did this because I didn't want to duplicate the "pour" code
			for every single loop in my version of the sort,
			and Java does not allow you to pass in a pointer to an int,
			and Integer does not work since it is immutable.
			So I needed to make a wrapper class and that's what this is.
		*/
		el[] arr; int pos;
		Vat (el[] arr) { this.arr = arr; } 
		void reset () { pos = 0; }
		void pour (List<el> bucket)
		{
			for (el e : bucket) arr[pos++] = e;
			bucket.clear();
		}
	}
	public static void radixSortStringsAlpha(String[] arr)
	{
		//Configure this method to perform a radix sort on an array of Strings of various lengths
		//use the version below that requires all the strings to be the same length as a starting point
		//very little should need to be modified for this to work
		
		/*
			More complicated logic is in order to do a true alphabetical sort.
			With sorting by the raw character value only, the ordering
			0123456789AaBbCcDdEe...#$%&+- etc is not upheld.
			This solution enforces that ordering, though only for ASCII values,
			it does not take into account any alternate Unicode codepoints etc
			(obviously) such as the fixed-width latin characters.
			All characters which are not in the ASCII 0x00-0x7F range are 
			transformed into the 0x7F 'DELETE' character.
		*/
		final int ASCII_START = 0x00, ASCII_STOP = 0x7F;
		final int ASCII_DIGIT_START = '0', ASCII_DIGIT_STOP = '9';
		final int ASCII_UPPER_START = 'A', ASCII_UPPER_STOP = 'Z';
		final int ASCII_LOWER_START = 'a', ASCII_LOWER_STOP = 'z';
		
		final int bucketCount = ASCII_STOP + 1;
		
		int stringLen = 0;
		for (String str : arr)
		{
			int len = str.length();
			stringLen = len > stringLen ? len : stringLen; // max
		}

		//Buckets need to be lists instead of counters
		List<String>[] buckets = new ArrayList[bucketCount];
		//create array of lists and initialize each object
		for(int i = 0; i < bucketCount; i++)
			buckets[i] = new ArrayList<>();
		/*
			Using ArrayList instead of LinkedList since they are frequently
			going to be cleared to 0 items and reused. Less allocation overhead.
		*/

		//loop from end of string to beginning
		Vat vat = new Vat(arr);
		for (int strpos = stringLen; strpos-- > 0; )
		{
			//loop through each string
			for(String item : arr)
			{
				//add to appropriate bucket
				char cc = 0; // default to NUL character if is beyond end of str
				if (strpos < item.length()) 
				{
					cc = item.charAt(strpos);
					if (cc >= bucketCount) 
						cc = bucketCount - 1;
					else if (cc >= ASCII_LOWER_START && cc <= ASCII_LOWER_STOP) 
						cc += ASCII_UPPER_START - ASCII_LOWER_START;
						// Necessary for 'abc' to come before 'Abg', etc.
				}
				buckets[cc].add(item);
			}
			
			// Sort:
			//	all numbers come first (themselves sorted by digit value)
			//	all letters come next (themselves sorted by letter)
			//	all symbols come last (themselves sorted by ascii value)
			vat.reset();
			for (int cc = ASCII_DIGIT_START; cc <= ASCII_DIGIT_STOP; cc++) vat.pour(buckets[cc]);
			for (int cc = ASCII_UPPER_START; cc <= ASCII_UPPER_STOP; cc++) vat.pour(buckets[cc]);
			for (int cc = ASCII_START; cc <= ASCII_STOP; cc++) vat.pour(buckets[cc]);
			// loop overlap is fine because buckets we're already "poured" are now empty.
			// buckets ASCII_LOWER_START through ASCII_LOWER_STOP are empty.
			
			System.out.println("Sorted on "+strpos+" : "+Arrays.toString(arr));
		}
	}
	/*
	 * Grading: 
	 * Modify the String radix sort for same length strings to work for multiple length strings - 2pts
	 * Do not add any additional data structures to the sort
	 * It is ok to determine the max length of strings in the array, but no other variables should be added
	 */
	public static void radixSortStrings(String[] arr)
	{
		/* begin new code */
		int stringLen = 0;
		for (String str : arr) 
			stringLen = Math.max(stringLen,str.length());
		/* end new code */
		
		//number of buckets = 256 (characters in the character set)
		int bucketCount = 256;
		//if you were doing a case insensitive sort, and you knew everything was single words, you could use 26 as your size

		//Buckets need to be lists instead of counters
		List<String>[] buckets = new LinkedList[bucketCount];
		//create array of lists and initialize each object
		for(int i = 0; i < buckets.length; i++)
		{
			buckets[i] = new LinkedList<>();
		}

		//loop from end of string to beginning
		for(int strpos = stringLen-1; strpos >= 0; strpos--)
		{
			//loop through each string
			for(String item : arr)
			{
				//add to appropriate bucket
				
				/* begin new/modified code */
				
				int ch = 0;
				if (strpos < item.length()) ch = item.charAt(strpos);
				buckets[ch].add(item);
				
				/*buckets[item.charAt(strpos)].add(item);*/
				
				/* end new/modified code */
				
				//item.charAt(strpos) converts to int automatically
				//A = 65, a = 97, 0 = 48, space = 32
			}
			//pointer for position in original list
			int pos = 0;
			//loop through buckets
			for(int i = 0; i < buckets.length; i++)
			{
				//loop through items in each buck
				for(String item : buckets[i])
				{
					//add each string back to original array in new order
					arr[pos] = item;
					pos++;
				}
				//clear the bucket
				buckets[i].clear();//O(1)
			}
			System.out.println("Sorted on "+strpos+" : "+Arrays.toString(arr));
		}
	}
	
	/*DO NOT MODIFY*/
	public static void radixSortStrings(String[] arr, int stringLen)
	{
		//number of buckets = 256 (characters in the character set)
		int bucketCount = 256;
		//if you were doing a case insensitive sort, and you knew everything was single words, you could use 26 as your size

		//Buckets need to be lists instead of counters
		List<String>[] buckets = new LinkedList[bucketCount];
		//create array of lists and initialize each object
		for(int i = 0; i < buckets.length; i++)
		{
			buckets[i] = new LinkedList<>();
		}

		//loop from end of string to beginning
		for(int strpos = stringLen-1; strpos >= 0; strpos--)
		{
			//loop through each string
			for(String item : arr)
			{
				//add to appropriate bucket
				buckets[item.charAt(strpos)].add(item);
				//item.charAt(strpos) converts to int automatically
				//A = 65, a = 97, 0 = 48, space = 32
			}
			//pointer for position in original list
			int pos = 0;
			//loop through buckets
			for(int i = 0; i < buckets.length; i++)
			{
				//loop through items in each buck
				for(String item : buckets[i])
				{
					//add each string back to original array in new order
					arr[pos] = item;
					pos++;
				}
				//clear the bucket
				buckets[i].clear();//O(1)
			}
			System.out.println("Sorted on "+strpos+" : "+Arrays.toString(arr));
		}
	}
}
