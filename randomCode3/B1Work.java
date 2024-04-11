import java.util.Stack;

public class B1Work {
	/*
	 * Grading:
	 * Correctly converts infix to postfix - 3pts
	 */
    
	private static class Expr
	{
		boolean is_number;

		// if is_number:
		char number; 

		// if not is_number:
		Expr left_expr,right_expr; 
		char opcode;
		
		
		
		String MakePostfix ()
		{
			if (is_number) return "" + number;
			else return left_expr.MakePostfix() + right_expr.MakePostfix() + opcode;
		}
		
		String MakeInfix ()
		{
			if (is_number) return "" + number;
			else return "(" + left_expr.MakeInfix() + opcode + right_expr.MakeInfix() + ")";
		}
	}

	private static class Reading
	{
		String str;
		int pos;
		Reading (String str) { this.str = str; };
		char getchar () { return str.charAt(pos++); };
	}

	private static Expr LoadExprInfix (Reading in)
	{
		char first = in.getchar();
		Expr out = new Expr();
		if (first == '(') 
		{
			out.is_number = false;
			out.left_expr = LoadExprInfix(in);
			out.opcode = in.getchar();
			out.right_expr = LoadExprInfix(in);
			in.getchar(); // Should be ')'
		}
		else 
		{
			out.is_number = true;
			out.number = first;
		};
		return out;
	}
	
	
    
	public static String infixToPostfix(String infix) {
		/*
		 * Convert an infix math formula to postfix format
		 * Infix format will always include parens around any two values and a symbol
		 * You will not need to imply any PEMDAS rules besides parens
		 * EXAMPLES:
		 * infix: (1+2) postfix: 12+
		 * infix: (1+(2-3)) postfix: 123-+
		 * infix: ((1+2)-3) postfix: 12+3-
		 * infix: ((1+2)-(3*4)) postfix: 12+34*-
		 */
		
		Reading reading = new Reading(infix);
		Expr root = LoadExprInfix(reading);
		return root.MakePostfix();
	}
	/*
	 * Grading:
	 * Correctly converts postfix to infix - 2pts
	 */
	private static boolean isopchar (char cc)
	{
		return cc == '+' || cc == '-' || cc == '*' || cc == '/';
	}
	public static String postfixToInfix(String postfix) {
		/*
		 * Convert a postfix math formula to an infix format
		 * See above for conversion examples
		 * Make sure to include parens in the infix format
		 */
		
		Stack<Expr> stack = new Stack<>();
		
		for (int i = 0; i < postfix.length(); i++)
		{
			char cc = postfix.charAt(i);
			Expr expr = new Expr();
			if (isopchar(cc))
			{
				expr.is_number = false;
				expr.right_expr = stack.pop();
				expr.left_expr = stack.pop();
				expr.opcode = cc;
			}
			else
			{
				expr.is_number = true;
				expr.number = cc;
			}
			stack.push(expr);
		}
		
		return stack.pop().MakeInfix();
	}
	/*
	 * Grading:
	 * Correctly solves postfix formulas with +-/* - 1pt
	 */
	public static double solvePostfix(String postfix) {
		Stack<Double> stack = new Stack<>();
		/*
		 * Use a Stack to help solve a postfix format formula for the numeric answer
		 * Order of operations is implied by where symbols/numbers exist
		 * EXAMPLES
		 * postfix: 12+ = 3
		 * postfix: 123-+ = 0 :: 2-3 = -1 :: 1 + (-1) = 0
		 */
		for (int i = 0; i < postfix.length(); i++)
		{
			char cc = postfix.charAt(i);
			if (isopchar(cc))
			{
				double right = stack.pop();
				double left = stack.pop();
				//System.out.printf("%d%c%d\n",left,cc,right);
				switch (cc)
				{
					case '+': left += right; break;
					case '-': left -= right; break;
					case '*': left *= right; break;
					case '/': left /= right; break;
				}
				stack.push(left);
			}
			else stack.push((double)cc - '0');
		}
		
		return stack.pop();
	}
}
