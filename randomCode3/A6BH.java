/*
 * Add operation counts to all methods - 3pts
 * No other methods/variables should be added/modified
 * Except for deleteMinAndInsert, which is super cool and definitely not pointless
 */
public class A6BH <E extends Comparable<? super E>> {
	private int defaultSize = 4;
	private int count = 0;
	private E[] heap;
	private int opCount = 0;
	
	public int getOpCount()
	{
		return opCount;
	}
	public void resetOpCount()
	{
		opCount = 0;
	}

	public A6BH()
	{
		heap = (E[])new Comparable[defaultSize];
			opCount+=2; // allocate, assign
	}
	public A6BH(int size)
	{
		heap = (E[])new Comparable[this.nextSize(size)];
			opCount+=3; // function call, allocate, assign
	}
	public A6BH(E[] items)
	{
		heap = (E[])new Comparable[this.nextSize(items.length)];
			opCount+=4; // access, function call, allocate, assign
		this.addAll(items);
			opCount++; // function call
	}

	public void addAll(E[] items)
	{
		//make sure there is room for all new items
		
		opCount+=2; // add, compare >= 
		if(count+items.length >= heap.length)
		{
			growArray(this.nextSize(count+items.length));
				opCount+=4; // access, add, function call, function call
		}

		opCount++; // make iterator
		for(E item : items)//copy new items in order
		{
			opCount+=2; // step iterator, assign
			
			count++;
				opCount+=2; // add, assign
			heap[count] = item;
				opCount++; // assign
		}

		this.buildHeap();//fix heap order
			opCount++; // function call
	}
	private void buildHeap()
	{
		opCount+=2; // right shift, assign
		for(int i = count >> 1; i > 0; i--)
		{
			opCount++; // compare i>0 success
			
			percolateDown(i);
				opCount++; // function call
			
			opCount+=2; // subtract, assign (i--)
		}
		opCount++; // compare i>0 fail
	}

	public void insert(E val)
	{
		//make sure we have room for new item
		
		opCount+=3; // add, access, compare
		if(count+1 >= heap.length)
		{
			growArray();
				opCount++; // function call
		}
		count++;
			opCount++; // function call
		heap[0] = val;//temporary storage
			opCount++; // assign
		percolateUp(count);
			opCount++; // function call
	}
	private void percolateUp(int pos)
	{
		//pos>>1 = pos/2 - getting to parent of empty space
		
		for(;heap[pos>>1].compareTo(heap[0]) > 0;pos = pos>>1)
		{
			opCount+=5; // access [0], right shift, access[pos>>1], function call, compare success
			
			heap[pos] = heap[pos>>1];//move parent down a level
				opCount+=3; // right shift, access, assign
			
			opCount+=2; // right shift, assign
		}
		opCount+=5; // access [0], right shift, access[pos>>1], function call, compare failure
			
		heap[pos] = heap[0];//take value from initial insert and put in correct pos
			opCount+=2; // access, assign
	}

	public E findMin()
	{
		opCount += 2 /*compare, return*/ + (count > 0 ? 1 /*access*/ : 0); 
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
	public E deleteMinAndInsert (E to_insert)
	{
		/*
			Does same thing as deleteMin() followed by insert(E), 
			but more efficiently by doing both at once.
			Avoids moving an existing item and percolating it down,
			when the next action is inserting a new item and percolating it up.
		
			Same scaling O(log N) of course.
			insert is O(log N), deleteMin is O(log N),
			and O(log N) + O(log N) == O(log N)
		*/
		opCount++; // compare count>0
		if (count > 0)
		{
			E temp = heap[1];
				opCount+=2; // access, assign
			heap[1] = to_insert;
				opCount++; // assign
			percolateDown(1);
				opCount++; // function call
			opCount++; // return
			return temp;
		}
		else 
		{
			insert(to_insert);
				opCount++; // function call
			opCount++; // return
			return null;
		}
	}
	private void percolateDown(int pos)
	{
		int firstChild = pos << 1;//pos * 2
			opCount+=2; // left shift, assign
		E temp = heap[pos];
			opCount+=2; // access, assign
			
		for(;pos<<1 <= count; pos = firstChild, firstChild = pos<<1)
		{
			opCount+=2; // left shift, compare success
			
			opCount+=2; // add, compare
			if(firstChild+1 <= count)//there is a second child
			{
				opCount+=5; // access, function call, access, add, compare
				if(heap[firstChild].compareTo(heap[firstChild+1]) > 0)
				{
					firstChild++;
						opCount+=2; // add, assign
				}
			}
			//firstChild is now the index of the smaller child
			
			opCount+=3; // function call, access, compare
			if(temp.compareTo(heap[firstChild]) > 0)
			{
				heap[pos] = heap[firstChild];//move child up to parent and continue
				opCount+=2; // access, assign
			}
			else
			{
				break;//stop loop because we found correct position for temp
			}
			
			opCount+=3; // assign, left shift, assign
		}
		opCount+=2; // left shift, compare failure
		
		heap[pos] = temp;
			opCount++; // assign
	}

	public String toString()
	{
		String output = "Heap Size:"+heap.length+"\n";
			opCount+=4; // access, concat, concat, assign
				
		opCount++; // assign
		for(int i = 1; i <= count; i++)
		{
			opCount++; // compare succeed
			
			output += heap[i]+",";
				opCount+=4; // access, concat, concat, assign
				
			opCount+=2; // add, assign
		}
		opCount++; // compare fail
		
		opCount++; // return
		return output;
	}

	public boolean isEmpty()
	{
		opCount+=2; // compare, return
		return count == 0;
	}
	public void makeEmpty()
	{
		count = 0;
			opCount++; // assign
	}

	private void growArray()//O(N)
	{
		growArray(heap.length << 1);//heap.length * 2
			opCount+=3; // access, left shift, function call
	}
	private void growArray(int size)
	{
		//new array that is twice as big
		E[] newArr = (E[])new Comparable[size];
			opCount+=2; // allocate, assign
		//Move values to new array
		
		opCount++; // assign
		for(int i = 1; i <= count; i++)//O(N)
		{
			opCount++; // compare success
			
			newArr[i] = heap[i];
				opCount+=2; // access, assign
				
			opCount+=2; // add, assign i++
		}
		opCount++; // compare fail
		
		//System.out.println(Arrays.toString(newArr));
		heap = newArr;//replace small array with new one
			opCount++; // assign
	}
	private int nextSize(int size)
	{
		opCount+=5; // function call, function call, add, left shift, return
		return 1 << (Integer.toBinaryString(size).length() + 1);//2^(number of bits to represent number)
	}
}
