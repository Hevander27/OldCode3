
import java.util.LinkedList;

/*
 * Complete the printInLevelOrder() method
 * Complete the visuallyIdentical(A4BST rhs) method
 * No other methods/variables should be added/modified
 */
public class A4BST<E extends Comparable<? super E>> {
	/*
	 * Grading:
	 * Correctly prints values in level order - 1.5pt
	 * Runs in O(N) - 1.5pt
	 */
    
    private class SuperConcat
    {
        private class ConcatItem
        {
            char[] str;
            ConcatItem next;
        }
        ConcatItem first,last;
        int len = 0;
        void Append (String str)
        {
            ConcatItem item = new ConcatItem();
            item.str = str.toCharArray();
            len += item.str.length;
            if (last == null) first = item;
            else last.next = item;
            last = item;
        }
        String All ()
        {
            char[] out = new char [len];
            int pos = 0;
            for (ConcatItem item = first; item != null; item = item.next)
            {
                System.arraycopy(item.str,0,out,pos,item.str.length);
                pos += item.str.length;
            }
            return new String(out);
        }
    }
    
    private class Levels
    {
        private class LevelItem
        {
            String str;
            LevelItem next_item;
            LevelItem (Node node) 
            { 
                str = node.data.toString();
            }
        }
        private class Level
        {
            LevelItem first_item;
            LevelItem last_item;
            void AddItem (Node node)
            {
                LevelItem li = new LevelItem(node);
                if (last_item == null) first_item = li;
                else last_item.next_item = li;
                last_item = li;
            }
            Level next_level;
        }
        int nodecount;
        int levels;
        Level root_level;
        Level LowerLevel (Level from)
        {
            if (from == null)
            {
                root_level = new Level();
                levels++;
                return root_level;
            }
            else 
            {
                if (from.next_level == null)
                {
                    from.next_level = new Level();
                    levels++;
                }
                return from.next_level;
            }
        }
        void Traverse (Level upper_level, Node node)
        {
            if (node == null) return;
            nodecount++;
            Level level = LowerLevel(upper_level);
            level.AddItem(node);
            Traverse(level,node.left);
            Traverse(level,node.right);
        }
        String Print ()
        {
            String separator = ", ";
            String newlinetab = "\n\t";
            String intro = String.format("%d Nodes, %d Levels",nodecount,levels);
            SuperConcat concat = new SuperConcat();
            concat.Append(intro);
            for (Level level = root_level; level != null; level = level.next_level)
            {
                String prepend = newlinetab;            
                for (LevelItem item = level.first_item; item != null; item = item.next_item)
                {
                    concat.Append(prepend);
                    prepend = separator;
                    concat.Append(item.str);
                }
            }
            return concat.All();
        }
    }
    
	public String printInLevelOrder()
	{
		/*
		 * Add items from the tree to content one level at a time
		 * No line breaks are required between levels
		 * Ensure method runs in O(N) - does not revisit any node
		 */
        Levels levels = new Levels();
        levels.Traverse(null,root);
        return levels.Print();
	}
    
	/*
	 * Grading:
	 * Correctly compares the structure of both trees - 3pts
	 */
    
    private boolean vis_ident (Node a, Node b)
    {
        if (a == null)
            return b == null;
        else if (b == null)
            return false;
        else 
            return vis_ident(a.left,b.left) && vis_ident(a.right,b.right);
    }
    
	public boolean visuallyIdentical(A4BST rhs)
	{
		/*
		 * Check if the structure of the local tree and the rhs tree are identical
		 * This means they have the same left/right connections
		 * This means there are no extra connections in either tree
		 * The values at each position do not need to match, only the structure of the tree
		 * Think about if you drew both trees on paper, would they visually look the same (besides values)
		 */
        
		return vis_ident(root,rhs.root);
	}
	
	private Node root;

	public A4BST()
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
