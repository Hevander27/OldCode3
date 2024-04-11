/*
 * Complete the buildHeap() method - 1pt
 * Complete the percolateUp(int pos) method - 2pts
 * Complete the percolateDown(int pos) method - 2pts
 * Complete the nextSize(int size) method - 1pt
 */
public class B3DHeap <E extends Comparable<? super E>> {
	private int defaultD = 2;//defaults to binary heap
	private int count = 0;
	private E[] heap;
	private int d;
	private class Levels
	{
		private int[] levels;
		private int levelcount;
		private int next_size = 2, next_add = d;
		public int get (int level_id)
		{
			if (level_id >= levelcount)
			{
				int[] old = levels;
				levels = new int [level_id + 1];
				System.arraycopy(old,0,levels,0,levelcount);
				while (level_id >= levelcount)
				{
					next_size += next_add;
					next_add *= d;
					levels[levelcount++] = next_size;
				}
			}
			return levels[level_id];
		}
		public int level_id (int index)
		{
			int level_id = 0;
			while (get(level_id) < index) level_id++;
			return level_id;
		}
		public int to_child (int parent_level_id, int parent_pos)
		{
			int parent_level = get(parent_level_id);
			int child_level = get(parent_level_id + 1);
			return child_level + (parent_pos - parent_level) * d;
		}
		public int to_parent (int child_level_id, int child_pos)
		{
			int parent_level = get(child_level_id - 1);
			int child_level = get(child_level_id);
			return parent_level + (child_pos - child_level) / d;
		}
	}
	Levels levels = new Levels();
	/*
	 * Grading:
	 * Correctly runs percolateDown on all parent nodes - 1pt
	 */
	int parent_id (int child)
	{
		//make sure new size is a full level of tree larger than size
		return levels.level_id()
		int nextsize = 2;
		int add = d;
		while (nextsize <= size)
		{
			nextsize += add;
			add *= d;
			System.out.printf("size %d, will add %d\n",nextsize,add);
		}
		return nextsize;
	}
	private void buildHeap()
	{
		//use binary heap as an idea of where to start
		//don't forget to take the d value into account
		int level_id = levels.level_id(count) - 1;
		if (level_id < 0) return;
		int item = count;
		for (; level_id >= 0; level_id--)
		{
			int level = levels.get(level_id);
			
			int parent = levels.to_parent(level_id,count);
			
		}
		int level = 
		for(int i = parent_id(count); i > 0; i--)
		{
			percolateDown(i);
		}
	}
	/*
	 * Grading:
	 * Correctly percolateUp based on number of children denoted by d - 2pt
	 */
	private void percolateUp(int pos)
	{
		//use binary heap as an idea of where to start
		//don't forget to take the d value into account
	}
	/*
	 * Grading:
	 * Correctly percolateDown based on number of children denoted by d - 2pt
	 */
	private void percolateDown(int pos)
	{
		//use binary heap as an idea of where to start
		//don't forget to take the d value into account
		int level = 1;
		int add = 1;
		while (true)
		{
			int nextlevel = level + add;
			if (nextlevel > pos) break;
			level = nextlevel;
			add *= d;
		}
		
		int childpos = pos;
		E temp = heap[pos];
		while (true)
		{
			int nextlevel = level + add;
			childpos = toChild(level,nextlevel,pos);
			level = nextlevel;
			add *= d;
			
			if (childpos > count) break;
			
			int childsel = 0;
			for (int childid = 1; childid < d; childid++)
			{
				if (heap[childpos + childid - 1].compareTo(heap[childpos + childid]) > 0)
					childsel = childid;	
			}
			childpos += childsel;
			if (temp.compareTo(heap[childpos]) > 0)
				heap[pos] = heap[childpos];
			else 
				break;
			pos = childpos;
		}
		heap[pos] = temp;
	}
	/*
	 * Grading:
	 * Correctly determine next size that adds a full level to the tree based on value of d - 1pt
	 */
	private int nextSize(int size)
	{
		//make sure new size is a full level of tree larger than size
		return levels.get(levels.level_id(size) + 1);
	}
	
	/* DO NOT MODIFY */
	public B3DHeap()
	{
		d = 3;//defaultD;
		heap = (E[])new Comparable[this.nextSize(d)];
		nextSize(1000);
	}
	public B3DHeap(int D)
	{
		d = D;
		heap = (E[])new Comparable[this.nextSize(d)];
	}
	public B3DHeap(int D, E[] items)
	{
		d = D;
		heap = (E[])new Comparable[this.nextSize(items.length)];
		this.addAll(items);
	}
	public void addAll(E[] items)
	{
		//make sure there is room for all new items
		if(count+items.length >= heap.length)
		{
			growArray(this.nextSize(count+items.length));
		}

		for(E item : items)//copy new items in order
		{
			count++;
			heap[count] = item;
		}

		this.buildHeap();//fix heap order
	}
	public void insert(E val)
	{
		//make sure we have room for new item
		if(count+1 >= heap.length)
		{
			growArray();
		}
		count++;
		heap[0] = val;//temporary storage
		percolateUp(count);
	}
	public E findMin()
	{
		return (count > 0)?heap[1]:null;
	}
	public E deleteMin()
	{
		if(count > 0)
		{
			E temp = heap[1];

			heap[1] = heap[count];//moved last value to top
			count--;//decrease size
			percolateDown(1);//move top value down to final position

			return temp;
		}
		else
		{
			return null;//no items in heap
		}
	}
	public boolean isEmpty()
	{
		return count == 0;
	}
	public void makeEmpty()
	{
		count = 0;
	}
	public String toString()
	{
		String output = d+"-Heap Size:"+heap.length+"\n";
		for(int i = 1; i <= count; i++)
		{
			output += heap[i]+",";
		}
		return output;
	}
	private void growArray()
	{
		growArray(nextSize(heap.length));
	}
	private void growArray(int size)
	{
		//new array that is twice as big
		E[] newArr = (E[])new Comparable[size];
		//Move values to new array
		for(int i = 1; i <= count; i++)//O(N)
		{
			newArr[i] = heap[i];
		}
		//System.out.println(Arrays.toString(newArr));
		heap = newArr;//replace small array with new one
	}
}
