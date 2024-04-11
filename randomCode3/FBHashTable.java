/* DO NOT MODIFY */
import java.math.BigInteger;
import java.util.LinkedList;

public class FBHashTable<E> {
	private LinkedList<E>[] table;
	private int count;
	private static int defaultSize = 12;//nextPrime will produce a 13 from this value
	public FBHashTable() {
		this(defaultSize);//call version with the parameter
	}
	public FBHashTable(int defaultSize) {
		table = new LinkedList[nextPrime(defaultSize)];//ensure user provided value is changed to next largest prime
		for(int i = 0; i < table.length; i++)
		{
			table[i] = new LinkedList<E>();
		}
		count = 0;
	}
	public void makeEmpty()
	{
		for(int i = 0; i < table.length; i++)
		{
			table[i].clear();//clears start/end/count of linked list
		}
		count = 0;
	}
	public boolean isEmpty()
	{
		return count == 0;
	}
	public void insert(E val)//O(M) = "Nearly O(1)"
	{
		int position = hash(val);//O(1)
		table[position].remove(val);//prevent duplicates//O(M) = "Nearly O(1)"
		table[position].add(val);//O(M) = "Nearly O(1)"
		count++;//O(1)
	}
	public boolean contains(E val)
	{
		int position = hash(val);//O(1)
		return table[position].contains(val);//O(M), values should be evenly spread, so M is very small in comparison to N
		//this means O(M) is our "Nearly O(1)"
	}
	public E get(E val)//O(2M)
	{
		int position = hash(val);//O(1)
		int llPos = table[position].indexOf(val);//returns -1 if not found
		if(llPos >= 0)
			return table[position].get(llPos);
		else
			return null;
	}
	public boolean remove(E val)
	{
		int position = hash(val);//O(1)
		return table[position].remove(val);//O(M) "Nearly O(1)"
	}

	private int hash(E val)//O(1)
	{
		return (val.hashCode() % table.length + table.length) % table.length;//gives zero to length-1
	}
	private int nextPrime(int size)
	{
		BigInteger bi = new BigInteger(""+size);
		return bi.nextProbablePrime().intValue();
	}
	public String toString()
	{
		String output = "Table Size:"+table.length+"\n";
		for(int i = 0; i < table.length; i++)
		{
			if(table[i].size() > 0)
			{
				output += i+":"+table[i]+"\n";
			}
		}
		return output;
	}
}
