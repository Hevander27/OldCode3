import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Complete the A5Cuckoo() method
 * Complete the A5Cuckoo(int size) method
 * Complete the insert(E val) method
 * Complete the contains(E val) method
 * Complete the get(E val) method
 * Complete the remove(E val) method
 * Complete the makeEmpty() method
 * Complete the hashForTable1(E val) method
 * Complete the hashForTable2(E val) method
 * Complete the rehash() method
 */
public class A5Cuckoo<E> {
	private E[] table1, table2;
	private int count;
	private int defaultSize = 7;
	/*
	 * Grading:
	 * Correctly initializes all values - 0.25pt
	 */
	int A1,A2,B1,B2,P1,P2;
	private void Init (int size)
	{
		//System.out.println("Allocating size " + size);
		table1 = (E[]) new Object [size];
		table2 = (E[]) new Object [size];
		
		Random rand = ThreadLocalRandom.current();//new Random();
		
		do
		{
			A1 = rand.nextInt();
			A2 = rand.nextInt();
		}
		while (A1 == A2 || A1 == 0 || A2 == 0); // mul by zero would be bad.
		
		do
		{
			B1 = rand.nextInt();
			B2 = rand.nextInt();
		}
		while (B1 == B2);
		
		P1 = nextPrime(size);
		P2 = nextPrime(P1);
		//System.out.printf("A1 %d, A2 %d, B1 %d, B2 %d, P1 %d, P2 %d\n",A1,A2,B1,B2,P1,P2);
	}
	public A5Cuckoo()
	{
		//create tables using defaultSize
		Init(defaultSize);
	}
	//public int truecount () { int c = 0; for (E e : table1) if (e != null) c++; for (E e : table2) if (e != null) c++; return c; }
	
	/*
	 * Grading:
	 * Correctly initializes all values - 0.25pt
	 */
	public A5Cuckoo(int size)
	{
		//create tables using prime larger than size
		Init(nextPrime(size));
	}
	/*
	 * Grading:
	 * Correctly inserts value into available position - 1pt
	 * Correctly moves values 1 step - 0.5pt
	 * Correctly moves values multiple steps - 1pt
	 * Correctly determines when to rehash - 1pt
	 */
	public void insert(E val)
	{
		//add item to table1 or table 2
		//move other items between table1/table2 if needed
		//rehash if more than half full or can't complete insert
		if (count > table1.length) rehash(); // rehash if over half full
		final int MAX_MOVES = 16;
		TRY: while (true)
		{
			boolean right_side = false;
			for (int move = 0; move < MAX_MOVES; move++, right_side = !right_side)
			{
				E[] arr = right_side ? table2 : table1;
				int pos = right_side ? hashForTable2(val) : hashForTable1(val);
				E was_there = arr[pos];
				arr[pos] = val;
				val = was_there;
				if (val == null) break TRY;
			}
			rehash();
		}
		count++;
	}
	// Alternate loop style
	/*public void insert(E val)
	{
		//add item to table1 or table 2
		//move other items between table1/table2 if needed
		//rehash if more than half full or can't complete insert
		if (count > table1.length) rehash(); // rehash if over half full
		final int MAX_MOVES = 16;
		int moves = 0;
		boolean right_side = false;
		while (val != null)
		{
			if (moves++ >= MAX_MOVES) 
			{
				rehash();
				moves = 0;
			}
			
			E[] arr = right_side ? table2 : table1;
			int pos = right_side ? hashForTable2(val) : hashForTable1(val);
			right_side = !right_side;
			
			E was_there = arr[pos];
			arr[pos] = val;
			val = was_there;
		}
		count++;
	}*/
	/*
	 * Grading:
	 * Correctly finds if value exists in table or not - 1pt
	 */
	public boolean contains(E val)
	{
		//return true/false if the value exists in the structure
		return get(val) != null;
	}
	/*
	 * Grading:
	 * Correctly returns value from table - 1pt
	 */
	public E get(E val)
	{
		//return the object if it exists in the structure
		int pos1 = hashForTable1(val);
		E item1 = table1[pos1];
		if (item1 != null) 
			if (item1.equals(val))
				return item1;
		int pos2 = hashForTable2(val);
		E item2 = table2[pos2];
		if (item2 != null)
			if (item2.equals(val))
				return item2;
		return null;
	}
	/*
	 * Grading:
	 * Correctly removes value from table - 1pt
	 */
	public boolean remove(E val)
	{
		//return true/false if the value was successfully removed from the structure
		int pos1 = hashForTable1(val);
		E item1 = table1[pos1];
		if (item1 != null)
		{
			if (item1.equals(val))
			{
				table1[pos1] = null;
				count--;
				return true;
			}
		}
		int pos2 = hashForTable2(val);
		E item2 = table2[pos2];
		if (item2 != null)
		{
			if (item2.equals(val))
			{
				table2[pos2] = null;
				count--;
				return true;
			}
		}
		return false;
	}
	/*
	 * Grading:
	 * Correctly empties both tables - 1pt
	 */
	public void makeEmpty()
	{
		for (int i = 0; i < table1.length; i++)
		{
			table1[i] = null;
			table2[i] = null;
		}
		count = 0;
		//clear both table1 and table2 of values, maintain current size
	}
	/*
	 * Grading:
	 * Correctly finds position for value based on universal hashing - 1pt
	 */
	private int hash (E val, int A, int B, int P)
	{
		/*
			((A * x + B) % p) % M
			A,B const
			x hashcode
			p prime > M
			M tblsize
		*/
		int x = val.hashCode();
		int out = ((A * x + B) % P) % table1.length;
		if (out < 0) out += table1.length; // fix signed modulus
		return out;
	}
	private int hashForTable1(E val)
	{
		//return a valid position in table1 using a form of Universal Hashing (slide 24 from part 3)
		//ensure the formula for finding a position is different for both
		return hash(val,A1,B1,P1);
	}
	/*
	 * Grading:
	 * Correctly finds DIFFERENT position for value based on universal hashing - 1pt
	 */
	private int hashForTable2(E val)
	{
		//return a valid position in table2 using a form of Universal Hashing (slide 24 from part 3)
		//ensure the formula for finding a position is different for both
		return hash(val,A2,B2,P2);
	}
	/*
	 * Grading:
	 * Correctly creates larger tables of correct sizes - 1pt
	 * Correctly rehashes values to new tables - 1pt
	 */
	private void rehash()
	{
		//make the tables the prime above double the current size and move values into the new tables
		E[] old_table1 = table1;
		E[] old_table2 = table2;
		Init(nextPrime(table1.length << 1));
		count = 0;
		for (E item : old_table1) if (item != null) insert(item);
		for (E item : old_table2) if (item != null) insert(item);	
		//System.out.printf("count %d, truecount %d\n",count,truecount());
	}
	
	/*
	 * COMPLETED METHODS
	 */
	private int nextPrime(int n)
	{
		return new BigInteger(""+n).nextProbablePrime().intValue();
	}
	public int getUsedSpace()
	{
		return count;
	}
	public int getAvailableSpace()
	{
		//total - used = available
		return table1.length+table2.length-count;
	}
	public String toString()
	{
		/*String output = "Table1\n";
		for(E val : table1)
		{
			output += val+",";
		}
		output = "Table2\n";
		for(E val : table2)
		{
			output += val+",";
		}
		
		return output;*/
		/*String out1 = "table1:";
		for (E val : table1) out1 += "\n\t"+val;
		String out2 = "\ntable2:";
		for (E val : table2) out2 += "\n\t"+val;
		return out1 + out2;*/
		StringBuilder out = new StringBuilder();
		out.append("[T1]");
		for (E val : table1) out.append("\t"+val);
		out.append("\n[T2]");
		for (E val : table2) out.append("\t"+val);
		return out.toString(); 
	}
}
