
import java.util.Stack;

/*
 * Complete the populateFromPostfix(String equation) method
 * Complete the populateFromPrefix(String equation) method
 * f(N) and O(N) for populateFromInfix(String equation) method
 * No other methods/variables should be added/modified
 */
public class A4EquationTree {
	/*
	 * Grading:
	 * Correctly fills in tree values from equation string - 3pts
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
    boolean isopchar (char cc)
    {
        return cc == '+' || cc == '-' || cc == '*' || cc == '/';
    }
	public void populateFromPostfix(String equation)
	{
		/*
		 * Given a postfix string, create a series of nodes that represent the formula
		 */
		root = null;
        Stack<Node> stack = new Stack();
        for (char cc : equation.toCharArray())
        {
            Node node = new Node(""+cc);
            if (isopchar(cc))
            {
                node.right = stack.pop();
                node.left = stack.pop();
            }
            stack.push(node);
        }
        root = stack.pop();
	}
	/*
	 * Grading:
	 * Correctly fills in tree values from equation string - 3pts
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
    class Reader 
    {
        String str;
        Reader (String str) { this.str = str; };
        int pos = 0;
        char get () { return str.charAt(pos++); }; 
    }
    Node prefix_get (Reader reader)
    {
        char cc = reader.get();
        Node node = new Node(""+cc);
        if (isopchar(cc)) // is operator
        {
            node.left = prefix_get(reader);
            node.right = prefix_get(reader);
        }
        return node;
    }
	public void populateFromPrefix(String equation)
	{
		/*
		 * Given a prefix string, create a series of nodes that represent the formula
		 */
        root = prefix_get(new Reader(equation));
	}
	/*
	 * Grading:
	 * f(N) formula (show your work) - 1pt
	 * O(N) reduction - 1pt
	 * Give best and average case reduction for BONUS - 1pt
	 */
	/*
	 * f(N) = 18 + 3.5N + 2*(9 + 1.75N + 2*(4.5 + 0.875N + 2*(2.25 + 0.4875 + ...)))
	 * O(N) = N^2
	 * Best&Average: N^1.5
	 *
	 */
	public void populateFromInfix(String equation)
	{
		root = populateFromInfixHelper(equation);
	}
	public Node populateFromInfixHelper(String equation)
	{
		// f(N) = 0 -> 1 + ~0
		if(equation.length() == 1)
		{
			return new Node(equation);//math operand
		}
		
		// f(N) = 1+~0 -> 5
		String temp = equation.substring(1, equation.length()-1);//remove wrapper paren

		// f(N) = 5+~0 -> 7
		//begin search for middle of equation
		int parenCount = 0;
		int mid = 0;
		
		// f(N) = 7+~0 -> 8
		for(int i = 0; i < temp.length(); i++)
		{
			// f(N) = 8 -> 8 + (2 * ~N/2)
			
			// for the outermost parentheses, this traverses the entire string
			// at that point the runtime is N.
			// Since the overall function also runs more times with a longer input,
			// the overall scaling is N^2.
			
			// f(N) = 8+~0 + (2 * ~N/2) -> 8+~0 + (4+~0 * ~N/2)
			if(temp.charAt(i) == '(')
				parenCount++;
			// f(N) = 8+~0 + (4+~0 * ~N/2) -> 8+~0 + (6+~0 * ~N/2)
			if(temp.charAt(i) == ')')
				parenCount--;
			// f(N) = 8+~0 + (6+~0 * ~N/2) -> 8+~0 + (7+~0 * ~N/2) 
			if(parenCount == 0)
			{
				mid = i+1;
				break;
			}
		}
		//middle
		// f(N) = 8+~0 + (7+~0 * ~N/2) -> 12+~0 + (7+~0 * ~N/2) 
		Node n = new Node(""+temp.charAt(mid));		
		//first half
		// f(N) = 12+~0 + (7+~0 * ~N/2) -> 14+~0 + (7+~0 * ~N/2) + f(N/2)
		n.left = populateFromInfixHelper(temp.substring(0, mid));//recursive
		//second half
		// f(N) = 14+~0 + (7+~0 * ~N/2) + f(N/2) -> 17+~0 + (7+~0 * ~N/2) + f(N/2)*2
		n.right = populateFromInfixHelper(temp.substring(mid+1));//recursive
		
		// f(N) = 17+~0 + (7+~0 * ~N/2) + f(N/2)*2 -> 18+~0 + (7+~0 * ~N/2) + f(N/2)*2
		return n;
		
		// f(N) = 18+~0 + (7+~0 * ~N/2) + f(N/2)*2 ~= 18 + (7*N/2) + f(N/2)*2
		// f(N) ~= 18 + 3.5N + 2*(9 + 1.75N + 2*(4.5 + 0.875N + 2*(2.25 + 0.4875 + ...)))
		// O(N) = N ^ 1.5
	}
	
	private Node root;
	public A4EquationTree()
	{
		root = null;
	}

	//(left parent right)
	//(->left->parent->right->)
	public String printInfix()
	{
		return printInfixHelper(root);
	}
	private String printInfixHelper(Node n)//O(N) - visit each node only once
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += "(";
			content += printInfixHelper(n.left);//left side
			content += n.value;//middle item//parent
			content += printInfixHelper(n.right);//right side
			content += ")";
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}
	//left -> right -> parent
	public String printPostfix()
	{
		return printPostfixHelper(root);
	}
	private String printPostfixHelper(Node n)
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += printPostfixHelper(n.left);//left side
			content += printPostfixHelper(n.right);//right side
			content += n.value;//middle item//parent
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}
	//parent -> left -> right
	public String printPrefix()
	{
		return printPrefixHelper(root);
	}
	private String printPrefixHelper(Node n)
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += n.value;//middle item//parent
			content += printPrefixHelper(n.left);//left side
			content += printPrefixHelper(n.right);//right side
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}

	private class Node
	{
		String value;
		Node left, right;
		public Node(String v)
		{
			value = v;
			left = null;
			right = null;
		}
	}
}
