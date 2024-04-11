/*
 * Complete the reverseSegments(int segmentSize) method
 * No other methods/variables should be added/modified
 */
public class MBDoubleLL<E> {
	/*
	 * Grading:
	 * Correctly reverses segments - 2pts
	 * f(N) - 1pt
	 * O(N) - 1pt
	 * Operation Counts - 1pt
	 */
	private long ReverseSegment (Object[] seg_buf, int seg_len)
	{
		long opCount = 0;
		
		opCount++; // seg_len<=1
		if (seg_len <= 1) return opCount;
		
		opCount+=2; // math, set
		int last_pos = seg_len - 1;
		
		opCount+=2; // array access, set
		Node was_first = (Node)seg_buf[0];
		
		opCount+=2; // array access, set
		Node was_last = (Node)seg_buf[last_pos];
		
		opCount+=2; // object access, set
		Node seg_before = was_first.prev;
		
		opCount+=2; // object access, set
		Node seg_after = was_last.next;
		
		opCount++; // i=0
		for (int i = 0; i < last_pos; i++)
		{
			opCount++; // i<last_pos pass
			
			opCount+=2; // access, set
			Node was_left = (Node)seg_buf[i];
			
			opCount+=3; // math, array access, set
			Node was_right = (Node)seg_buf[i + 1];
			
			opCount+=2; // object access, set
			was_left.prev = was_right;
			
			opCount+=2; // object access, set
			was_right.next = was_left;
			
			opCount+=2; // i++ math, set
		}
		opCount++; // i<last_pos fail
		
		opCount+=3; // array access, object access, set
		((Node)seg_buf[0]).next = seg_after;
		
		opCount+=3; // array access, object access, set
		((Node)seg_buf[last_pos]).prev = seg_before;
		
		opCount++; // ==null
		if (seg_before == null)
		{
			opCount++; // set
			start = was_last;
		}
		else
		{
			opCount+=2; // object access, set
			seg_before.next = was_last;
		}
		
		opCount++; // ==null
		if (seg_after == null) 
		{
			opCount++; // set
			end = was_first;
		}
		else
		{
			opCount+=2; // object access, set
			seg_after.prev = was_first;
		}
		
		return opCount;
	}
		
	public void reverseSegments(int segmentSize)
	{
		long opCount = 0;
		/*
		 * Reverse the values in each segment of the list based on the segment size
		 * It is ok if the last segment is smaller than the others, you should still reverse this segment
		 * Changes must occur by modifying the values of next/prev variables on nodes
		 */
		
		/*
			1 2 3 4 5 6 7 8 9 10 &seg=4
			4 3 2 1 8 7 6 5 10 9 output
		*/
		
		opCount+=2; // allocate, set
		Object[] seg_buf = new Object [segmentSize]; 
		
		opCount++; // set
		int seg_pos = 0;
		
		opCount++; // set
		Node here = start;
		
		while (here != null)
		{
			opCount++; // !=null pass
			
			opCount+=3; // math, array access, set
			seg_buf[seg_pos++] = here;
			
			opCount+=2; // object access, set
			here = here.next;
			
			opCount+=3; // >=, ==, ||
			if (seg_pos >= segmentSize || here == null)
			{
				opCount++; // function call
				ReverseSegment(seg_buf,seg_pos);
				
				opCount++; // set
				seg_pos = 0;
			}
		}
		opCount++; // !=null fail
		
		
		
		System.out.println("OpCount:"+opCount);
		System.out.println("f(N) = [your answer here]");
		System.out.println("O(N) = N");
	}

	private Node start, end;
	private int count;
	public MBDoubleLL() {
		start = end = null;
		count = 0;
	}
	public String printList() {
		String output = "";
		Node current = start;
		while(current != null) {
			output += current.value + ",";
			current = current.next;
		}
		return output;
	}
	public String printListRev() {
		String output = "";
		Node current = end;
		while(current != null) {
			output += current.value + ",";
			current = current.prev;
		}
		return output;
	}
	public void add(E val) {
		Node newItem = new Node(val);
		if(start == null) {
			start = newItem;
			end = start;
			count = 1;
		} else {
			end.next = newItem;
			newItem.prev = end;
			end = newItem;
			count++;
		}
	}
	public void insert(E val, int index) {
		if(index < 0) {//fix invalid index
			index = 0;
		}
		if(index >= count) {//goes in last position
			this.add(val);
		} else {
			Node newItem = new Node(val);
			if(index == 0) {//goes in first position
				newItem.next = start;
				start.prev = newItem;
				start = newItem;
			} else {//goes in middle
				Node current = start;
				for(int i = 1; i < index; i++) {
					current = current.next;
				}
				newItem.next = current.next;
				newItem.prev = current;
				current.next.prev = newItem;
				current.next = newItem;
			}
			count++;
		}
	}
	public void delete(int index) {
		if(index >= 0 && index < count) {//valid index
			if(index == 0) {//remove first
				start = start.next;
				if(start != null) {//as long as there was an item next in list
					start.prev = null;
				} else {//if only item was removed
					end = null;
				}
			} else if(index == count-1) {//remove last item
				end = end.prev;
				end.next = null;
			} else {//remove middle item
				Node current = start;
				for(int i = 1; i < index; i++) {
					current = current.next;
				}
				current.next = current.next.next;
				current.next.prev = current;
			}
			count--;
		}
	}
	public E get(int index) {
		if(index >= 0 && index < count) {//valid index
			Node current = start;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.value;
		}
		return null;
	}
	public String toString() {
		return this.printList();
	}
	private class Node {
		E value;
		Node next, prev;
		public Node(E v) {
			value = v;
			next = prev = null;
		}
	}
}
