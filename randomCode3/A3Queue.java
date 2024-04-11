/*
 * Complete the enqueue(E val) method
 * Complete the dequeue() method
 * Complete the peek() method
 * No other methods/variables should be added/modified
 */
public class A3Queue<E> {
	/*
	 * Grading:
	 * Correctly adds an item to the queue - 1pt
	 */
	public void enqueue(E val) {
		/*
		 * Add a node to the list
		 */
		Node node = new Node(val);
		if (end == null) front = node;
		else end.next = node;
		node.prev = end;
		end = node;
		length++;
	}
	/*
	 * Grading:
	 * Correctly removes an item from the queue - 1pt
	 * Handles special cases - 0.5pt
	 */
	public E dequeue() {
		/*
		 * Remove a node from the list and return it
		 */
		if (front == null) return null;
		
		Node node = front;
		front = front.next;
		if (front == null) end = null;
		else front.prev = null;
		length--;
		return node.value;
	}
	/*
	 * Grading:
	 * Correctly shows an item from the queue - 1pt
	 * Handles special cases - 0.5pt
	 */
	public E peek() {
		/*
		 * Show a node from the list
		 */
		if (front == null) return null;
		
		return front.value;
	}
	
	private Node front, end;
	private int length;
	public A3Queue() {
		front = end = null;
		length = 0;
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
