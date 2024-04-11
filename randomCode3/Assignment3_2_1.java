import java.util.Iterator;

public class Assignment3_2_1<E> {

	private Node<E> start, end;
	private int size;
	
	public Assignment3_2_1()
	{
		start = null;
		end = null;
		size = 0;
	}
	public void insert(E val, int index)
	{
		Node<E> newItem = new Node<>(val);
		if(index < 0)
			index = 0;
		if(start == null)
		{
			start = newItem;
			end = newItem;
		}
		else if(index == 0)
		{
			newItem.next = start;
			start.prev = newItem;
			start = newItem;
		}
		else if(index >= size)
		{
			end.next = newItem;
			newItem.prev = end;
			end = newItem;
		}
		else
		{
			Node<E> current = start;
			if(index > size)
				index = size;
			while(index-1 > 0)//find item before where insert goes
			{
				current = current.next;
				index--;
			}
			//1 2 3 4
			//1 = start
			//insert[5] at pos 2
			//current => 2
			//new item => current.next
			//5 => 3
			//current.next => new item
			//2 => 5
			//1 2 5 3 4
			newItem.next = current.next;
			newItem.prev = current;
			current.next.prev = newItem;
			current.next = newItem;
		}
		size++;
	}
	public void add(E val)
	{
		insert(val, size);
	}
	public String printList()
	{
		String output = "[";
		Node<E> current = start;
		while(current != null)
		{
			output += current.toString()+",";
			current = current.next;
		}
		return output+"]";
	}
	public String printListRev()
	{
		String output = "[";
		Node<E> current = end;
		while(current != null)
		{
			output += current.toString()+",";
			current = current.prev;
		}
		return output+"]";
	}
	public E find(E val)
	{
		for(Node<E> current = start; current != null; current = current.next)
		{
			if(current.val.equals(val))
				return current.val;
		}
		return null;
	}
	public E findLast(E val)
	{
		for(Node<E> current = end; current != null; current = current.prev)
		{
			if(current.val.equals(val))
				return current.val;
		}
		return null;
	}
	public E get(int index)
	{
		if(index >= 0 && index < size)
		{
			if(index > size-index)
			{
				Node<E> current = end;
				for(int i = 0; i < index; i++)
				{
					current = current.prev;
				}
				return current.val;
			}
			else
			{
				Node<E> current = start;
				for(int i = 0; i < index; i++)
				{
					current = current.next;
				}
				return current.val;
			}
		}
		return null;
	}
	public E remove(E val)
	{
		Node<E> temp;
		if(start == null)//list empty
		{
			return null;
		}
		else if(start.val.equals(val))
		{
			temp = start;
			start = start.next;
			if(start != null)
				start.prev = null;
			size--;
			if(size == 0)
				end = null;
			return temp.val;
		}
		else if(end.val.equals(val))
		{
			temp = end;
			end = end.prev;
			end.next = null;
			size--;
			return temp.val;
		}
		else
		{
			Node<E> current = start;
			//find item before the one being deleted
			while(current.next != null && !current.next.val.equals(val))
			{
				current = current.next;
			}
			//if the item we ended on is followed by the one that we want to delete
			if(current.next != null && current.next.val.equals(val))
			{
				temp = current.next;
				current.next = current.next.next;
				current.next.prev = current;
				size--;
				return temp.val;
			}
		}
		return null;
	}
	public E delete(int index)
	{
		Node<E> temp;
		if(index < 0)
			index = 0;
		if(start == null || index >= size)//list empty or deleting item past end of list
		{
			return null;
		}
		else if(index == 0)
		{
			temp = start;
			start = start.next;
			if(start != null)
				start.prev = null;
			size--;
			if(size == 0)
				end = null;
			return temp.val;
		}
		else if(index == size-1)
		{
			temp = end;
			end = end.prev;
			end.next = null;
			size--;
			return temp.val;
		}
		else
		{
			Node<E> current = start;
			//find item before index to delete
			for(int i = 0; i < index-1; i++)
			{
				current = current.next;
			}
			temp = current.next;
			current.next = current.next.next;
			current.next.prev = current;
			size--;
			return temp.val;
		}
	}
	public class Node<E>
	{
		E val;
		Node<E> next, prev;
		public Node()
		{
			val = null;
			next = null;
			prev = null;
		}
		public Node(E val)
		{
			this.val = val;
			next = null;
			prev = null;
		}
		public String toString()
		{
			return val.toString();
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((val == null) ? 0 : val.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (val == null) {
				if (other.val != null)
					return false;
			} else if (!val.equals(other.val))
				return false;
			return true;
		}
		private Assignment3_2_1 getOuterType() {
			return Assignment3_2_1.this;
		}
		
		public void swapNode(int index1, int index2) {
			if(index1 > index2) {
				int index = index1;
				index1 = index2;
				index2 = index;
			}
			
			if(index1 + 1 != index2) {
				System.out.println("Non-adjacent indices! Cannot swap.");
				return;
			}
			
			if(index1 < 0 || index2 > size-1) {
				System.out.println("Out of range! Cannot swap.");
				return;
			}
			
			
			Node node1 = (Node) get(index1);
			Node node2 = (Node) get(index2);
			Node temp1 = node1.prev;
			Node temp2 = node2.next;
			
			temp1.next = node2;
			node2.prev = temp1;
			node1.next = temp2;
			temp2.prev = node1;
			node1.prev = node2;
			node2.next = node1;
			
		}
	}
	

	
}
