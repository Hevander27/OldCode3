package main.java;

import java.util.*;

public class Assignment4_16<E extends Comparable<? super E>> {
	 
	private BinaryNode<E> root;
	private int del_Count = 0;
	private int total_Count = 0;
	
	public Assignment4_16() { root = null; }

	public void insert(E e) { root = insert(e, root); }	
	public void remove(E e) { root = remove(e, root); }
	
	public E findMin()
	{
		if (isEmpty()) throw new NoSuchElementException("Empty Stack");
		return findMin(root).val;
	}
	

	public E findMax()
	{
		if (isEmpty()) throw new NoSuchElementException("Empty Stack");
		return findMax(root).val;
	}

	public boolean isEmpty()     { return del_Count == total_Count; }
	public boolean contains(E e) { return contains(e, root); }
	
	public void makeEmpty()
	{
		del_Count = 0;
		total_Count = 0;
		root = null;
	}
	
	public void printTree(){
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}
	
	private BinaryNode<E> insert(E val, BinaryNode<E> curr)
	{
		if (curr == null){
			total_Count = total_Count + 1;
			return new BinaryNode<E>(val, null, null);
		}
		
		int comparison = val.compareTo(curr.val);
		
		if (comparison < 0)      { curr.left = insert(val, curr.left); }
		else if (comparison > 0) { curr.right = insert(val, curr.right); }
		else if (curr.isDeleted)
		{
			curr.isDeleted = false;
			del_Count = del_Count - 1;
		}
		return curr;
	}
	
	private BinaryNode<E> remove(E val, BinaryNode<E> curr)
	{
		if (curr == null) return curr;

		int comparison = val.compareTo(curr.val);

		if(comparison < 0)      { curr.left = remove(val, curr.left); }
		else if(comparison > 0) { curr.right = remove(val, curr.right); }
		else 
		{
			if (!curr.isDeleted) {
				curr.isDeleted = true;
				del_Count = del_Count +1;
			}
		}
		return curr;
	}
	
	
	private BinaryNode<E> findMin(BinaryNode<E> curr)
	{
		if (curr == null)                 return null;
		else if (leftSubtreeExists(curr)) return findMin(curr.left);		
		else if (!curr.isDeleted)         return curr;		
		else if(rightSubtreeExists(curr)) return findMin(curr.right);
		return null;	
	}
	
	
	private BinaryNode<E> findMax(BinaryNode<E> curr)
	{
		if (curr == null) return null;
		else if (rightSubtreeExists(curr)) {
			return findMax(curr.right);
		}
		else if (!curr.isDeleted) return curr;
		else if (leftSubtreeExists(curr)) {
			return findMax(curr.left);
		}
		return null;
	}
	
    public void update(E val) { insert(val); }
	
	private boolean leftSubtreeExists(BinaryNode <E> curr) 
	{
		if (curr == null) return false;
		if (curr.left != null) {
			if (!curr.left.isDeleted) return true;
			else if (leftSubtreeExists(curr.left) || rightSubtreeExists(curr.left))
				return true;
			else  return false;
		}
		return false;
	}
	
	private boolean rightSubtreeExists(BinaryNode <E> curr) 
	{
		if (curr == null) return false;
		if (curr.left != null) {
			if (curr.right != null && !curr.right.isDeleted) return true;
			else if (leftSubtreeExists(curr.right) || rightSubtreeExists(curr.right))
				return true;
			else return false;
		}
		return false;
	}
	

	private boolean  contains(E e, BinaryNode<E> curr)
	{
		if (curr == null) return false;
		
		int comparison = e.compareTo(curr.val);
		if (comparison < 0)      return contains(e, curr.left);
		else if (comparison > 0) return contains(e, curr.right);
		else if (curr.isDeleted) return false;
		return true;
	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			if (!n.isDeleted) System.out.println(n.val);
			printTree(n.right);
		}
	}

	private class BinaryNode<E>
	{
		E val;
		BinaryNode<E> left, right;//left = smaller, right = greater
		boolean isDeleted;
		
		@SuppressWarnings("unused")
		public BinaryNode(E value)
		{
			this.val = value;
			left = null;
			right = null;
		}
		
		BinaryNode(E value, BinaryNode<E> lt, BinaryNode<E> rt) 
		{
			val = value;
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
