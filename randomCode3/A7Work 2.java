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
	public static <E extends Comparable<? super E>> boolean contains(E val, E[][] matrix)
	{
		long opCount = 0;
		long compareCount = 0;
		//Determine if the val exists in the matrix as efficiently as possible
		//increment compareCounts every time you compare val to a position in the matrix
		//for notation, consider the number of rows/columns to be N, so the matrix contains N^2 values (rows/columns always equal)
		
		for (int i = 0; i < matrix.length; i++) 
		{
			int low = 0;
			int high = matrix[i].length-1;
			
			while (high >= low) {
				int mid = (low + high)/2;
				if (val.compareTo(matrix[i][mid]) < 0) {
					high = mid-1;
				} else if (val.compareTo(matrix[i][mid]) == 0) {
					return true;
				} else {
					low = mid+1;
				}
			}
		}
		
		System.out.println("f(N) = [your answer]");
		System.out.println("O(N) = [your answer]");
		System.out.println("opCount = "+opCount);
		System.out.println("compareCounts = "+compareCount);
		return false;
	}

	/*
	 * Grading: 
	 * Modify the String radix sort for same length strings to work for multiple length strings - 2pts
	 * Do not add any additional data structures to the sort
	 * It is ok to determine the max length of strings in the array, but no other variables should be added
	 */
	public static void radixSortStrings(String[] arr)
	{
		//Configure this method to perform a radix sort on an array of Strings of various lengths
		//use the version below that requires all the strings to be the same length as a starting point
		//very little should need to be modified for this to work
		
			int strLen = arr.length;
			int BUCKETS = 256 + strLen + 1;
			ArrayList<String>[] buckets = new ArrayList[BUCKETS];
			for (int i = 0; i < BUCKETS; i++) buckets[i] = new ArrayList<>();

			int idx = 0;
			for (String s : arr) buckets[s.length()].add(s);
			for (int i = 1; i < strLen + 1; i++) {
				ArrayList<String> wordList = buckets[i];
				for (String s : wordList) arr[idx++] = s;
			}

			int startIndex = arr.length;
			for (int pos = strLen - 1; pos >= 0; pos--) {
				startIndex -= buckets[pos + 1].size();
				for (int i = startIndex; i < arr.length; i++) {
					int si = arr[i].charAt(pos) + strLen;
					buckets[si].add(arr[i]);
				}
				
				idx = startIndex;
				for (int i = strLen; i < buckets.length; i++) {
					ArrayList<String> bucket = buckets[i];
					for (String s : bucket) {
						if (idx == arr.length) break;
						arr[idx++] = s;
					}
					bucket.clear();
				}
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
