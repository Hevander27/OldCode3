/*
 * Complete FBMap.push()
 * Complete FBMap.containsKey()
 * Complete FBMap.getValue()
 * Complete FBMap.remove()
 * Add missing required methods to FBMap.Entry
 */
public class FBMap<KEY,VALUE> {
	private FBHashTable<Entry> map;

	
	
	Entry dummy = new Entry(); // We're going to hold on to this,
		// instead of allocating it for every single call to getValue.
		// It could cause threading problems but otherwise is more efficient.

	
	
	/*
	 * Grading:
	 * Complete push method - 0.75pts
	 */
	public void push(KEY k, VALUE v)
	{
		/*
		// If there is something with this key already, update it.
		dummy.k = k;
		System.out.println("find: "+dummy);
		Entry entry = map.get(dummy);
		System.out.println("got: " + entry);
		
		if (entry == null)
			// An entry with this key didn't already exist. Make a new entry.
			map.insert(new Entry(k,v));
		else 
			// Update the value of existing entry.
			entry.v = v;
		*/
		
		// HashTable already prevents duplicates so the implementation can be simpler.
		map.insert(new Entry(k,v));
	}
	/*
	 * Grading:
	 * Complete containsKey method - 0.75pts
	 */
	public boolean containsKey(KEY k)
	{
		dummy.k = k;
		return map.contains(dummy);
	}
	/*
	 * Grading:
	 * Complete getValue method - 0.75pts
	 */
	public VALUE getValue(KEY k)
	{
		dummy.k = k;
		Entry entry = map.get(dummy);
		if (entry == null) return null;
		return entry.v;
	}
	/*
	 * Grading:
	 * Complete remove method - 0.75pts
	 */
	public boolean remove(KEY k)
	{
		dummy.k = k;
		return map.remove(dummy);
	}
	private class Entry
	{
		/*
		 * Grading:
		 * Add missing required methods - 2pts
		 * NOTE: Make sure they are configured correctly for all above methods to work as expected
		 */
		KEY k;
		VALUE v;
		public Entry () {}
		public Entry(KEY k, VALUE v)
		{
			this.k = k;
			this.v = v;
		}
		public String toString()
		{
			return k+":"+v;
		}
		
		
		
		@Override
		public int hashCode ()
		{
			return k.hashCode();
		}
		
		@Override // <- "@Override" really saved the day. 
		// "boolean equals (Entry other)" was not actually overriding anything.
		public boolean equals (Object other)
		{
			return k.equals(((Entry)other).k);
		}
	}
	
	
	
	public FBMap()
	{
		map = new FBHashTable<>();
	}
	public void makeEmpty()
	{
		map.makeEmpty();
	}
	public boolean isEmpty()
	{
		return map.isEmpty();
	}
	public String toString()
	{
		return map.toString();
	}

	
}
