
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class FBWork {
	/*
	 * Complete FBMap.push() - 0.75pts
	 * Complete FBMap.containsKey() - 0.75pts
	 * Complete FBMap.getValue() - 0.75pts
	 * Complete FBMap.remove() - 0.75pts
	 * Add missing required methods to FBMap.Entry - 2pts
	 */

	
	
	/* 
		encode_Nscaling is O(N) but does not really "use data structures 
		from this class" as required, so it's not meant to be graded.
	
		It also only supports an arbitrary range of character values
		due to being array-based.
	*/
	static class CharUsage implements Comparable<CharUsage>
	{
		int usage;
		int in_char;
		CharUsage (int in_char) { this.in_char = in_char; }
		public int compareTo (CharUsage other) { return other.usage - this.usage; }
	}
	public static String encode_simple (String plain)
	{
		/**
		 * This is a modified version of my FinalBonus during Spring 2019.
		 * However, there are of course many code quality improvements.
		 */
		final int ASCII_COUNT = 128;
		CharUsage[] usages = new CharUsage [ASCII_COUNT]; // Constant
		for (int i = 0; i < ASCII_COUNT; i++) // Constant
			usages[i] = new CharUsage(i); // Constant
		
		for (int i = 0; i < plain.length(); i++) // O(N) 
			usages[plain.charAt(i)].usage++; // Constant
		// The above will throw ArrayIndexOutOfBoundsException for invalid characters.
		// This solution is only designed for valid ASCII characters (0x00 .. 0x7F).
		
		Arrays.sort(usages);
			// The sorting algorithm may have O(N^2), O(N^1.5), O(log N), etc...
			// but it doesn't matter because the number of things being sorted
			// is constant in this context. N in the context of the sort
			// is ASCII_RANGE in the context of this code that calls it.
			// So, even O(ASCII_RANGE^2) might as well be O(1).
		
		String[] outputs = new String [ASCII_COUNT]; // Constant
		for (int i = 0; i < ASCII_COUNT; i++) // Constant, does not scale with N.
			outputs[usages[i].in_char] = Integer.toBinaryString(i); // Constant
		// Well, maybe Integer.toBinaryString is not truly constant runtime,
		// but it doesn't matter, for the same reason why the runtime scaling
		// of the sorting algorithm doesn't matter.
		
		StringBuilder out = new StringBuilder(); // Constant
		// Using StringBuilder ensures linear scaling.
		// Concatenating lots of Strings repeatedly would lead to O(N^2)
		// due to needing to copy the entire String contents every time,
		// while the length of the String contents itself grows every time.
		// I think technically the StringBuilder uses an array it reallocates
		// as necessary, but much less often that String concatenation.
		// Due to the doubling behavior, the amount of copying is never more
		// than twice as much as the number of items. So it can be considered
		// that for N items, N*2 (so, N) copying happens, thus actually
		// despite the reallocation, we can say the runtime is averaging O(1).
		
		boolean first = true;
		for (int i = 0; i < plain.length(); i++) // O(N)
		{
			if (first) first = false;
			else out.append(' ');
			out.append(outputs[plain.charAt(i)]); // Constant
		}
		
		return out.toString(); // Constant
	}
	
	
	
	/*
		This is the version meant to be graded.
	*/
	/*
	 * Grading:
	 * Correctly encodes a string and runs in at least O(N^2) - 1.5pts
	 * Runs in at least O(N log N) - 1pt 
	 * Runs in O(N) - 1pt
	 * Operation Counts - 0.5pt
	 * f(N) - 0.5pt
	 * O(N) - 0.5pt
	 */
	static class Usage implements Comparable<Usage>
	{
		int usage;
		public int compareTo (Usage other) { return other.usage - this.usage; }
		String binary;
	}
	public static String encode (String plain)
	{
		/* 
			This solution supports all Unicode codepoints, even ones that
			are not supported by a Java `char` value.
		
			In order to do so, it uses a TreeMap instead of a plain array
			to store the Usage values for every unique codepoint it encounters.
			
			This gives a space vs. time tradeoff: for code point 0x00 and 
			code point 0x7F (ASCII null and delete) this solution needs space
			for two Usage objects (well, plus the tree overhead), whereas
			the array-based solution would need space for 128 Usage objects.
			That space requirement would quickly become unsustainable when
			supporting larger and larger codespaces (such as the entire
			Unicode character space, orders of magnitude larger than ASCII).
			
			The tradeoff is that instead of constant-time access of the Usage
			object belonging to a codepoint (as a raw array index), there is now
			a more expensive log(P) lookup time to find the Usage object within
			the binary search tree, where P is the number of unique codepoints 
			encountered, **not** the length of the input String (well, unless
			the input String had no repeating characters).
		*/
		
		/*
			Further clarification of N vs P:
				P (named for code 'Points') is the number of unique codepoints.
				N is the total number of characters.
			"AAAAA" would have P=1 and N=5. "ABCDE" would have P=5 and N=5.
			
			Since, for the most part, longer text contains more repeated 
			characters (i.e. beyond a certain point it's very unlikely
			that longer text would actually mean larger number of unique 
			characters... you can write a paragraph with 29 characters [A-Z,
			space, comma, dot] or an entire novel, no difference in P.
		
			As such, it is safe to call P *essentially* a constant, so it
			can be left out of the overall O notation.
		*/
		
		TreeMap<Integer,Usage> usages = new TreeMap(); // Constant
		for (int i = 0; i < plain.length(); i++) 
		// Runs N times. Contains log(P). Total N * log(P)
		{
			int codepoint = plain.codePointAt(i); // Constant
			Usage usage = usages.get(codepoint); // log(P)
			if (usage == null) // Constant
			{
				usage = new Usage(); // Constant
				usages.put(codepoint,usage); // log(P)
				// One may not that this block only executes P times, rather than N.
			}
			usage.usage++; // Constant
		}
		
		Set<Map.Entry<Integer,Usage>> usages_set = usages.entrySet(); // O(P)
		ArrayList<Usage> usages_sorted = new ArrayList(usages_set.size()); // Constant
		
		for (Map.Entry<Integer,Usage> entry : usages_set) 
		// Runs P times. Contains constant. Total P.
			usages_sorted.add(entry.getValue()); // Constant
		
		Collections.sort(usages_sorted); 
			// I don't know what sorting algorithm Java will choose.
			// Let's go with worst case and say it chooses Insertion Sort: O(P^2).
			
		int value = 0; // Constant
		for (Usage usage : usages_sorted)
		// Runs P times. Contains constant. Total P.
			usage.binary = Integer.toBinaryString(value++); // We'll call this constant.
		
		StringBuilder out = new StringBuilder(); // Constant
		boolean first = true; // Constant
		for (int i = 0; i < plain.length(); i++)
		// Runs N times. Contains log(P). Total N * log(P).
		{
			if (first) first = false; // Constant
			else out.append(' '); // Constant
			out.append(usages.get(plain.codePointAt(i)).binary); 
				// log(P) lookup, basically constant append.
		}
		String encoded = out.toString(); // Constant
		
		
		
		// I think I am not going to bother with the opCount etc
		long opCount = 0;
		/*
		 * Encode the plain text into a series of binary values based on their frequency within the string
		 * The most common character should receive the lowest binary value (0)
		 * Example: "Hello, World"
		 * 	The letter 'l' is the most common, so it would be converted to (0)
		 * 	The letter 'o' is the next most common, so it would be converted to (1)
		 * 	All other characters occur only once, each would be given the next largest binary value
		 * 		NOTE: There are multiple correct answers depending on how you process the list of characters that occur the same number of times
		 * 	One possible output would be: 10 11 0 0 1 100 101 110 1 111 0 1000
		 * 								  H  e  l l o ,       W   o r   l d
		 * 
		 * You MUST use data structures we learned about in class (you can use built in ones, or custom ones)
		 * Annotate your code with Big-O at all crucial lines (any method calls, loops, etc)
		 * Define O(N) where N is the number of characters in the string
		 * At the bottom of this file is a list of common built in methods and their O(N), please ask if you want to use something that isn't included
		 * 
		 * You are welcome to create additional methods/classes to support this conversion process
		 */
		System.out.println("Op Count:"+opCount);
		System.out.println("f(N) = N * log(P) + up to P^2");
		System.out.println("^^ f(N) is not intended to be for credit");
		System.out.println("O(N) = N");
		return encoded;
	}
	
	/*
	 * Whenever multiple O() is listed, one is more accurate (for op count), the other is for calculating f(N)/O(N)
	 * String.charAt(0);//O(1)
	 * String.toCharArray();//O(1)
	 * String.equals(str);//O(N) where N is the number of characters in the String
	 * String.indexOf(chr);//O(index) or O(N) where N is the number of characters in the String
	 * String.length();//O(1)
	 * String.replaceAll(search, replace);//O(N) where N is the number of characters in the String
	 * String.substring(begin, end);//O(length) or O(N) where N is the number of characters in the String
	 * String.trim();//O(N) where N is the number of characters in the String
	 * Integer.equals(int);//O(1)
	 * Integer.parseInt(str);//O(N) where N is the number of characters in the String
	 * Integer.toBinraryString(int);//O(N) where N is the number of characters produced in the output String
	 * int c = (int)'A';//casting is O(1)
	 * Math.PI;//constants are O(1)
	 * Math.min(num1, num2);//O(1) same for max
	 * for(item i : items)//initialize is 1, compare is 1, increment is 1, place counters at usual spots
	 * for opCounts, increment by 1 for log(N) work
	 */
}
