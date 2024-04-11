package main.java;

public class Assignment3_2<E>  {

	private Node<E> start;
	private int size;
	public Assignment3_2()
	{
		start = null;
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
		}
		else if(index == 0)
		{
			newItem.next = start;
			start = newItem;
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
	public E find(E val)
	{
		for(Node<E> current = start; current != null; current = current.next)
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
			Node<E> current = start;
			for(int i = 0; i < index; i++)
			{
				current = current.next;
			}
			return current.val;
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
				size--;
				return temp.val;
			}
		}
		return null;
	}
	
	public void swapNodes(E x, E y)
    {
        if (x == y) return;
 
        Node<E> prevX = null, currX = start;
        while (currX != null && !currX.val.equals(x))
        {
            prevX = currX;
            currX = currX.next;
        }
 
        Node<E> prevY = null, currY = start;
        while (currY != null && !currY.val.equals(y))
        {
            prevY = currY;
            currY = currY.next;
        }

        if (currX == null || currY == null) return;
        if (prevX != null) prevX.next = currY;
        else start = currY;

        if (prevY != null) prevY.next = currX;
        else start = currX;
            
        Node<E> temp = currX.next;
		System.out.println(temp);
        currX.next = currY.next;
        currY.next = temp;
		
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
			size--;
			return temp.val;
		}
	}
	public class Node<E>
	{
		int data;
		E val;
		Node<E> next;
		public Node(int d)
		{
			data = d;
			val = null;
			next = null;
		}
		public Node(E val)
		{
			this.val = val;
			next = null;
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
			Node<E> other = (Node<E>) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (val == null) {
				if (other.val != null)
					return false;
			} else if (!val.equals(other.val))
				return false;
			return true;
		}
		private Assignment3_2 getOuterType() {
			return Assignment3_2.this;
		}
		
	}
	
}
