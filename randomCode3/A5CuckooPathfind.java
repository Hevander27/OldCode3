/*

	This was my original solution.
	It's a bit over complicated due to using a path finding algorithm,
	but I'll include it in case you were curious what I was talking about
	when I described it in class.

*/

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
public class A5CuckooPathfind<E> {
	private E[] table1, table2;
	private int count;
	private int defaultSize = 7;
	/*
	 * Grading:
	 * Correctly initializes all values - 0.25pt
	 */
	int A1,A2,B1,B2,P1,P2;
	byte[] visited;
	private void Init (int size)
	{
		//System.out.println("Allocating size " + size);
		table1 = (E[]) new Object [size];
		table2 = (E[]) new Object [size];
		visited = new byte [size];
		
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
	}
	public A5CuckooPathfind()
	{
		//create tables using defaultSize
		Init(defaultSize);
	}
	/*
	 * Grading:
	 * Correctly initializes all values - 0.25pt
	 */
	public A5CuckooPathfind(int size)
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
	class Insertions 
	{
		/* 
			The purpose of this thing is to enhance performance,
			by not creating new storage every time.
		
			Originally I used a LinkedList<Insertion> which meant
			unconditionally creating a new Insertion as well as
			the internal nodes being allocated in that LinkedList.
		*/
		class Insertion
		{
			E[] arr;
			int pos;
			E obj;
		}
		Object[] insertions;//Insertion[] insertions;
		int usage;
		void Clear ()
		{
			usage = 0;
		}
		void Commit ()
		{
			for (int i = 0; i < usage; i++) 
			{
				Insertion ins = (Insertion)insertions[i];
				ins.arr[ins.pos] = ins.obj;
			}
		}
		void Unvisit ()
		{
			for (int i = 0; i < usage; i++)
			{
				Insertion ins = (Insertion)insertions[i];
				visited[ins.pos] = 0;
			}
		}
		Insertions ()
		{
			final int starting_capacity = 8;
			insertions = new Object [starting_capacity];
			for (int i = 0; i < starting_capacity; i++) 
				insertions[i] = new Insertion();
		}
		void Expand () 
		{
			Object[] previous_storage = insertions;
			insertions = new Object [previous_storage.length << 1];
			System.arraycopy(previous_storage,0,insertions,0,previous_storage.length);
			for (int i = previous_storage.length; i < insertions.length; i++) 
				insertions[i] = new Insertion();
		}
		void Add (E[] arr, int pos, E obj)
		{
			if (usage >= insertions.length) Expand();
			Insertion ins = (Insertion)insertions[usage++];
			ins.arr = arr;
			ins.pos = pos;
			ins.obj = obj;
			
			//if (usage > highest) highest = usage;
			//System.out.printf("\t\tUsing %d (Highest %d)\n",usage,highest);
		}
		//int highest = 0;
	}
	Insertions EvictionPathA = new Insertions(), EvictionPathB = new Insertions();
	private boolean optimal_eviction (E incoming_obj)
	{
		EvictionPathA.Clear();
		EvictionPathB.Clear();
		E obj_a = incoming_obj, obj_b = incoming_obj;
		boolean alive_a = true, alive_b = true;
		boolean success = false;
		boolean cycle = true;
		/*
			bits of "visited"...
				1: table1 visited by a
				2: table2 visited by a
				4: table1 visited by b
				8: table2 visited by b
		*/
		//System.out.println("Path");
		while (true)
		{
			E[] array_a,array_b;
			int visit_bit_a,visit_bit_b;
			int pos_a,pos_b;
			if (cycle)
			{
				array_a = table1;
				visit_bit_a = 1;
				pos_a = hashForTable1(obj_a);
				array_b = table2;
				visit_bit_b = 8;
				pos_b = hashForTable2(obj_b);
			}
			else 
			{
				array_a = table2;
				visit_bit_a = 2;
				pos_a = hashForTable2(obj_a);
				array_b = table1;
				visit_bit_b = 4;
				pos_b = hashForTable1(obj_b);
			}
			//System.out.printf("\tA: %d\tB: %d\n",pos_a,pos_b);
			if (alive_a)
			{
				if ((visited[pos_a] & visit_bit_a) == visit_bit_a) 
				{
					if (!alive_b) break;
					alive_a = false;
				}
				else
				{
					visited[pos_a] |= visit_bit_a;
					EvictionPathA.Add(array_a,pos_a,obj_a);
					obj_a = array_a[pos_a];
					if (obj_a == null)
					{
						EvictionPathA.Commit();
						success = true;
						break;
					}
				}
			}
			if (alive_b)
			{
				if ((visited[pos_b] & visit_bit_b) == visit_bit_b)
				{
					if (!alive_a) break;
					alive_b = false;
				}
				else 
				{
					visited[pos_b] |= visit_bit_b;
					EvictionPathB.Add(array_b,pos_b,obj_b);
					obj_b = array_b[pos_b];
					if (obj_b == null)
					{
						EvictionPathB.Commit();
						success = true;
						break;
					}
				}
			}
			cycle = !cycle;	
		}
		EvictionPathA.Unvisit();
		EvictionPathB.Unvisit();
		return success;
	}
	public void insert(E val)
	{
		//add item to table1 or table 2
		//move other items between table1/table2 if needed
		//rehash if more than half full or can't complete insert
		if (count > table1.length) rehash(); // rehash if over half full
		while (true)
		{
			if (optimal_eviction(val)) break;
			rehash(); // rehash if can't insert
		}
		count++;
	}
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
		String out1 = "[T1]";
		for (E val : table1) out1 += "\t"+val;
		String out2 = "\n[T2]";
		for (E val : table2) out2 += "\t"+val;
		return out1 + out2;
	}
}
