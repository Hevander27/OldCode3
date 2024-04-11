import java.util.*;

public class Assignment4_16<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	private int del_Count = 0;
	private int total_Count = 0;
	
	public Assignment4_16()
	{
		root = null;
	}

	public void makeEmpty()
	{
		del_Count = 0;
		total_Count = 0;
		root = null;
	}
	
	public boolean isEmpty()
	{
		return root == null;
	}
	
	public boolean contains(E val)
	{
		return contains(val, root);
	}
	
	private boolean  contains(E val, BinaryNode<E> curr)
	{
		if(curr == null)
			return false;

		int comparison = val.compareTo(curr.val);

		if(comparison < 0)//item is smaller than current
		{
			return contains(val, curr.left);
		}
		else if(comparison > 0)
		{
			return contains(val, curr.right);
		}
		else if(curr.isDeleted){
			return false;
		}
		else
			return true;
	}
	
	public E findMin()
	{
		if(isEmpty())
			return null;
		return findMin(root).val;
	}
	
	private BinaryNode<E> findMin(BinaryNode<E> curr)
	{
		if(curr == null)
			return null;
		
		else if(leftSubtreeExists(curr))
			return findMin(curr.left);
		
		else if (!curr.isDeleted)
			return curr;
		
		else if(rightSubtreeExists(curr))
			return findMin(curr.right);
		else
			return null;
		
	}
	
	public E findMax()
	{
		if(isEmpty())
			return null;
		return findMax(root).val;
	}
	
	private BinaryNode<E> findMax(BinaryNode<E> curr)
	{
		if(curr == null)
			return null;
		
		else if(rightSubtreeExists(curr))
			return findMax(curr.right);
		else if(!curr.isDeleted)
			return curr;
		
		else if(leftSubtreeExists(curr))
			return findMax(curr.left);
		else
			return null;
	}
	
	public void update(E val)
	{
		insert(val);
	}
	public void insert(E val)
	{
		root = insert(val, root);
	}
	
	private boolean leftSubtreeExists(BinaryNode <E> curr) 
	{
		boolean exist;
		
		if(curr == null)
			exist = false;
		
		if(curr.left != null) {
			if(!curr.left.isDeleted)
				exist = true;
			else if(leftSubtreeExists(curr.left) || rightSubtreeExists(curr.left))
				exist = true;
			else 
				exist = false;
		}
		else
			exist = false;
		return exist;
	}
	
	private boolean rightSubtreeExists(BinaryNode <E> curr) 
	{
		boolean exist;
		
		if(curr == null)
			exist = false;
		
		if(curr.left != null) {
			if(!curr.left.isDeleted)
				exist = true;
			else if(leftSubtreeExists(curr.left) || rightSubtreeExists(curr.left))
				exist = true;
			else 
				exist = false;
		}
		else
			exist = false;
		return exist;
	}
	
	private BinaryNode<E> insert(E val, BinaryNode<E> curr)
	{
		if(curr == null){
			total_Count = total_Count + 1;
			return new BinaryNode<E>(val,null,null);
		}
		int comparison = val.compareTo(curr.val);

		if(comparison < 0)//item is smaller than current
		{
			curr.left = insert(val, curr.left);
		}
		else if(comparison > 0)
		{
			curr.right = insert(val, curr.right);
		}
		else if(curr.isDeleted)
		{
			curr.isDeleted = false;
			del_Count = del_Count - 1;
		}
		return curr;
	}
	
	public void remove(E val)
	{
		root = Lremove(val, root);
	}
	private BinaryNode<E> Lremove(E val, BinaryNode<E> curr)
	{
		if(curr == null)//not found
			return curr;

		int comparison = val.compareTo(curr.val);

		if(comparison < 0)//item is smaller than current
		{
			curr.left = Lremove(val, curr.left);
		}
		else if(comparison > 0)
		{
			curr.right = Lremove(val, curr.right);
		}
		
		else 
		{
			if(!curr.isDeleted) {
				curr.isDeleted = true;
				del_Count = del_Count +1;
			}
			else;
		}
		return curr;
	}

	private class BinaryNode<E>
	{
		E val;
		BinaryNode<E> left, right;//left = smaller, right = greater
		boolean isDeleted;
		
		public BinaryNode(E val)
		{
			this.val = val;
			left = null;
			right = null;
		}
		
		BinaryNode(E val, BinaryNode<E> lt, BinaryNode<E> rt) 
		{
			this.val = val;
			left = lt;
			right = rt;
			isDeleted = false;
		}
		
		public String toString()
		{
			return val.toString();
		}
	}
	



	
}
