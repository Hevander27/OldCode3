import java.util.*;

public class Assignment3_22 {

	public static void main(String args []) {
	
    MyStack<Double> dubs = new MyStack<>();
	MyStack<String> pre = new MyStack<>();	
	MyStack<String> ops = new MyStack<>();	
	
	Scanner scan = new Scanner (System.in);

	System.out.println("Enter format");
	String format = scan.next();
	
	int j = 0;
	
	double val = 0;
	while(!format.isEmpty()) {
		
		String s = format.substring(0, 1);
		format = format.substring(s.length(), format.length());
	
		if  (s.equals(" "))   continue;	
		if  (!(s.equals("(") || s.equals(")") || s.equals("+") ||
			   s.equals("-") || s.equals("*") || s.equals("/") || s.equals("sqrt")))
			{ 
				System.out.println(s);
				pre.push(s); 
			}
		else {
			double n1 = Double.parseDouble(pre.pop());
			System.out.println("VAL" + n1);
			if 		(s.equals("+"))    val = Double.parseDouble(pre.pop()) + n1;
			else if (s.equals("-"))    val = Double.parseDouble(pre.pop()) - n1;
			else if (s.equals("*"))    val = Double.parseDouble(pre.pop()) * n1;
			else if (s.equals("/"))    val = Double.parseDouble(pre.pop()) / n1;
			else if (s.equals("sqrt")) val = (Double.parseDouble(pre.pop()))   ;
			pre.push("" + val);
		}
			
			
		
	    //System.out.println(val);
		/*String[] array = format.split("[^\\d]+");

		for(int i = 0; i < array.length; i++)
		
    	System.out.println(Double.parseDouble(array[i]));*/	
	}
	System.out.println(val);
  }
}
