/*
 * Complete the contains(MBBST subtree) method
 * No other methods/variables should be added/modified
 */
public class MBBST<E extends Comparable<? super E>> {
	/*
	 * Grading:
	 * Correctly determines if the subtree tree exists inside of this tree - 2pts
	 * f(N) - 1pt
	 * O(N) - 1pt
	 * Operation Counts - 1pt
	 */
	
	private class OpTrack
	{
		int ops = 0;
		void add (int n) { ops += n; };
	}
	
	private boolean contains (Node local, Node other, OpTrack ops)
	{
		ops.add(1); // local == null
		if (local == null)
		{
			ops.add(2); // other==null, return
			return other == null; // other must be null too to pass
		} 
		else 
		{
			// local exists
			
			ops.add(1); // other==null
			if (other == null) 
			{
				ops.add(1); // return
				return true; // "local may have additional values"
			} 
			else
			{
				// other exists too so must be equal, and also check the lower levels.
				
				ops.add(3); // access local.data, access other.data, function call
				if (local.data.equals(other.data))
				{
					ops.add(3); // access local.left, access other.left, function call
					if (contains(local.left,other.left,ops))
					{
						ops.add(3); // access local.right, access other.right, function call
						if (contains(local.right,other.right,ops))
						{
							ops.add(1); // return
							return true;
						}
					}
				}
				ops.add(1); // return 
				return false;
			}
		}
	}
	
	private boolean contains (Node node, MBBST subtree, OpTrack ops)
	{
		ops.add(1); // node == null
		if (node == null)
		{
			ops.add(1);
			return false;
		}
		ops.add(2); // access subtree.root, function call
		if (contains(node,subtree.root,ops))
		{
			ops.add(1); // return
			return true;
		}
		ops.add(2); // access node.left, function call
		if (contains(node.left,subtree,ops))
		{
			ops.add(1); // return
			return true;
		}
		ops.add(2); // access node.right, function call
		if (contains(node.right,subtree,ops))
		{
			ops.add(1); // return
			return true;
		}
		ops.add(1); // return
		return false;
	}
		
	public boolean contains(MBBST subtree)
	{
		//long opCount = 0;
		OpTrack ops = new OpTrack();
		/*
		 * Check if the exact values/structure of the sub tree matches the local tree
		 * This means they have the same left/right connections with the same values
		 * The local tree may have additional values that the sub tree does not
		 */
		
		boolean result = contains(root,subtree,ops);
		
		//opCount++;//for return statement at bottom
		ops.add(1);//for return statement at bottom
		//System.out.println("OpCount:"+opCount);
		System.out.println("OpCount:"+ops.ops);
		System.out.println("f(N) = [your answer here]");
		System.out.println("O(N) = N^2");
		
		return result;
	}
	
	private Node root;

	public MBBST()
	{
		root = null;
	}

	public String printTree()
	{
		return printTree(root);
	}
	private String printTree(Node current)
	{
		String content = "";
		if(current != null)
		{
			content += "Current:"+current.data.toString();
			if(current.left != null)
			{
				content += "; Left side:"+current.left.data.toString();
			}
			if(current.right != null)
			{
				content += "; Right side:"+current.right.data.toString();
			}
			content+="\n";
			content+=printTree(current.left);
			content+=printTree(current.right);

		}
		return content;
	}
	public String printInOrder()
	{
		return printInOrder(root);
	}
	private String printInOrder(Node current)
	{
		String content = "";
		if(current != null)
		{
			content += printInOrder(current.left);
			content += current.data.toString()+",";
			content += printInOrder(current.right);
		}
		return content;
	}
	public boolean contains(E val)
	{
		Node result = findNode(val, root);

		if(result != null)
			return true;
		else
			return false;
	}
	private Node findNode(E val, Node current)
	{
		//base cases
		if(current == null)
			return null;
		if(current.data.equals(val))
			return current;

		//recursive cases
		int result = current.data.compareTo(val);
		if(result < 0)
			return findNode(val, current.right);
		else
			return findNode(val, current.left);
	}
	public E findMin()
	{
		Node result = findMin(root);
		if(result == null)
			return null;
		else
			return result.data;
	}
	private Node findMin(Node current)//used in findMin and delete
	{
		while(current.left != null)
		{
			current = current.left;
		}
		return current;
	}
	public E findMax()
	{
		Node current = root;
		while(current.right != null)
		{
			current = current.right;
		}
		return current.data;
	}
	public void insert(E val)
	{
		root = insertHelper(val, root);
	}
	public Node insertHelper(E val, Node current)
	{
		if(current == null)
		{
			return new Node(val);
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = insertHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = insertHelper(val, current.left);
		}
		else//update
		{
			current.data = val;
		}
		return current;
	}
	public void remove(E val)
	{
		root = removeHelper(val, root);
	}
	private Node removeHelper(E val, Node current)
	{
		if(current.data.equals(val))
		{
			if(current.left == null && current.right == null)//no children
			{
				return null;
			}
			else if(current.left != null && current.right != null)//two children
			{
				Node result = findMin(current.right);
				result.right = removeHelper(result.data, current.right);
				result.left = current.left;
				return result;
			}
			else//one child
			{
				return (current.left != null)? current.left : current.right;
			}
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = removeHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = removeHelper(val, current.left);
		}
		return current;
	}


	private class Node
	{
		E data;
		Node left, right;
		public Node(E d)
		{
			data = d;
			left = null;
			right = null;
		}
	}

}
