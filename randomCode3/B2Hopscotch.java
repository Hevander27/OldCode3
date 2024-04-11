import java.math.BigInteger;

/*
 * Complete the insert(E val) method
 * Complete the contains(E val) method
 * Complete the get(E val) method
 * Complete the remove(E val) method
 */
public class B2Hopscotch<E> {
	private Node<E>[] table;
	private int count, hops;
	private static int defaultSize = 7;
	private static int defaultHops = 4;
	
	/*
	 * Grading:
	 * Correctly inserts value into available position - 1pt
	 * Correctly moves values 1 step - 1pt
	 * Correctly moves values multiple steps - 1pt
	 * Correctly prevents values from being to far from expected position - 0.5pt
	 * Correctly updating hop values - 0.5pt
	 * Correctly determines when to rehash - 0.5pt
	 */
	private void MoreHops ()
	{
		/*
			The purpose of this is to make buckets bigger instead of performing
			a true (expensive) rehash, and may sometimes solve the problem.
		
			It also makes the table resilient against inserting exactly-equal
			values many times, even though that is not the intended usage.
		*/
		hops += 4;
		for (int i = 0; i < table.length; i++)
		{
			Node node = table[i];
			boolean[] old_hops = node.hops;
			node.hops = new boolean [hops];
			System.arraycopy(old_hops,0,node.hops,0,old_hops.length);
		}
	}
	public void insert(E val)
	{
		//add item to table1 or table 2
		//move other items between table1/table2 if needed
		//rehash if more than half full or can't complete insert
		//[!] ^^ this section of comments might need updating ^^ [!]
		if (count << 1 > table.length) rehash(); // "rehash if more than half"
		
		ATTEMPT: while (true)
		{
			int into_pos = hash(val);
			boolean hops_ok = false;
			for (boolean hop : table[into_pos].hops) 
				if (!hop) hops_ok = true;
			if (!hops_ok) MoreHops(); 
			
			int pos = into_pos;
			for (int i = 0; i < table.length; i++)
			{
				Node node = table[pos];
				if (node.val == null)
				{
					// viable candidate!
                    MOVING: while (i >= hops)
					{
						// too far. try to do an intermediate move.
						
						int earlier = pos - (hops - 1);
                        if (earlier < 0) earlier += table.length;
                        for (int j = 0; j < hops - 1; j++)
                        {
                            boolean[] map = table[earlier].hops;
                            for (int k = 0; k < hops; k++)
                            {
                                if (map[k])
                                {
                                    map[k] = false;
                                    map[(hops - 1) - j] = true;
                                    int move_pos = earlier + k;
                                    if (move_pos >= table.length) move_pos -= table.length;
                                    E move_item = table[move_pos].val;
                                    table[move_pos].val = null;
                                    table[pos].val = move_item;
                                    pos = earlier;
                                    i -= (hops - 1) - j;
                                    continue MOVING;
                                }
                            }
							
                            if (++earlier >= table.length) earlier -= table.length;
                        }
						hmul = nextPrime(hmul);
                        rehash();
                        continue ATTEMPT;
					}
					// directly set it
					table[into_pos].hops[i] = true;
					table[pos].val = val;
					count++;
                    break ATTEMPT;
				}
				if (++pos >= table.length) pos -= table.length;
			}
			rehash();
		}
	}
	/*
	 * Grading:
	 * Correctly finds if value exists in table or not guided by hop values - 0.5pt
	 */
	public boolean contains(E val)
	{
		//return true/false if the value exists in the structure
		return get(val) != null;
	}
	/*
	 * Grading:
	 * Correctly returns value from table, guided by hop values - 0.5pt
	 */
	public E get(E val)
	{
		//return the object if it exists in the structure
		int pos = hash(val);
		boolean[] map = table[pos].hops;
		for (int hop = 0; hop < hops; hop++)
		{
			if (map[hop])
			{
				E here = table[pos].val;
				if (here.equals(val)) return here;
			}
			if (++pos >= table.length) pos -= table.length;
		}
		return null;
	}
	/*
	 * Grading:
	 * Correctly removes value from table and updates hop value - 0.5pt
	 */
	public boolean remove(E val)
	{
		//return true/false if the value was successfully removed from the structure
		int pos = hash(val);
		boolean[] map = table[pos].hops;
		for (int hop = 0; hop < hops; hop++)
		{
			if (map[hop])
			{
				E here = table[pos].val;
				if (here.equals(val)) 
				{
					map[hop] = false;
					table[pos].val = null;
					count--;
					return true;
				}
			}
			if (++pos >= table.length) pos -= table.length;
		}
		return false;
	}
	
	/*
	 * COMPLETED METHODS
	 */
	public B2Hopscotch()
	{
		this(defaultSize, defaultHops);
	}
	public B2Hopscotch(int size)
	{
		this(size, defaultHops);
	}
	public B2Hopscotch(int size, int h)
	{
        if (h < 2) h = 2; 
            // 1 hop can't move things, 
            // 0 hops can't store things at all, 
            // -1 hops invalid, etc
            
		table = new Node[nextPrime(size)];
		hops = h;
		makeEmpty();
	}
	public void makeEmpty()
	{
		for(int i = 0; i < table.length; i++)
		{
			table[i] = new Node(hops);
		}
		count = 0;
	}
	int hmul = 1; 
		/* 
			The purpose of this is to prevent infinitely rehashing
			due to "clumps" of values with overlapping buckets.
		*/
	private int hash(E val)
	{
		return (val.hashCode()*hmul)%table.length;
	}
	private void rehash()
	{
		Node<E>[] t1 = table;//store old table
		table = new Node[nextPrime(table.length << 1)];//create larger table
		//System.out.printf("rehash, size %d, hops %d, hashmul %d\n",table.length,hops,hmul);
		makeEmpty(); // <-- [!] This was left out originally. [!]
		for (Node<E> node : t1)
			if (node.val != null) 
				insert(node.val);
	}
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
		return table.length-count;
	}
	public String toString()
	{
		String output = "Table: "+table.length+"\n";
		for(int i = 0; i < table.length; i++)
		{
			output += i+": "+table[i]+"\n";
		}
		return output;
	}
	private class Node<E>
	{
		E val;//public for direct access
		boolean[] hops;//public for direct access
		public Node(int h)
		{
			val = null;
			hops = new boolean[h];//values default to false
		}
		public String toString()
		{
			String output = "Hops: ";
			for(boolean h : hops)
			{
				output += h+",";
			}
			output += " :: Value: "+val;
			return output;
		}
	}
}
