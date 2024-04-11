import java.util.Scanner;

public class B1Driver {

	public static void main(String[] args) {
		mainloop: while(true)
		{
			Scanner s = new Scanner(System.in);
			
			System.out.print("What format will your formula be in (infix/postfix):");
			String format = s.nextLine().toLowerCase();
			
			if (format.equals("exit")) break;
			else if (!format.equals("infix") && !format.equals("postfix"))
			{
				System.out.println("???");
				continue;
			};
			
			System.out.print("Enter your formula:");
			String formula = s.nextLine().replaceAll(" ", "");//removes whitespace
			String infix, postfix;
			switch(format) 
			{				
				case "infix":
				infix = formula;
				postfix = B1Work.infixToPostfix(infix);
				System.out.println("Infix:"+infix);
				System.out.println("Postfix:"+postfix);
				System.out.println("Answer:"+B1Work.solvePostfix(postfix));
				break;
				
				case "postfix":
				postfix = formula;
				infix = B1Work.postfixToInfix(postfix);
				System.out.println("Infix:"+infix);
				System.out.println("Postfix:"+postfix);
				System.out.println("Answer:"+B1Work.solvePostfix(postfix));
				break;
			}
		}
	}

}
