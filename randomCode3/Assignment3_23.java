import java.util.Scanner;

public class Assignment3_23 {

	public static void main(String[] args) {
		//(1 + ((2 * 3) + (((4 * 5) + 6) * 7)))
		MyStack<String> ops = new MyStack<String>();
		
		MyStack<Double> vals = new MyStack<Double>();
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Choose infix or postfix format: ");
		
		String format = scan.next();
		scan.nextLine();
		String formula = "";
		
		switch(format) {
		
		case "infix":
		
			System.out.println("Enter formula in infix format: ");
			formula = scan.nextLine();
			break;
		
		case "postfix":
			
			System.out.println("Enter formula in postfix format: ");
			formula = scan.nextLine();
			break;
			
		default:
			System.out.println("Invalid format");
		}
		
		while(!formula.isEmpty()) {
			
			String s = formula.substring(0, 1);
			formula = formula.substring(s.length(), formula.length());
			
			// put string into array then push all #s from array into stack
			String []array = format.split("[^\\d]+");

			for(int i = 0; i < array.length; i++){
				if      (s.equals(" ")) continue   ;
	    	vals.push(Double.parseDouble(array[i]));
			}
			
			
			if      (s.equals(" ")) continue   ;
			// when ")" is encountered pop, pop
				if (s.equals(")")) {
				String op = ops.pop();
				System.out.println(op);
			    double v = vals.pop();
			    System.out.println(v);
				if 		(op.equals("+"))    v = vals.pop() + v;
				else if (op.equals("-"))    v = vals.pop() - v;
				else if (op.equals("*"))    v = vals.pop() * v;
				else if (op.equals("/"))    v = vals.pop() / v;
				else if (op.equals("sqrt")) v = Math.sqrt(v);
				vals.push(v);
			}
			else { vals.push(Double.parseDouble(s)); }
		}
		
		System.out.println(vals.pop());
	}


	
}
