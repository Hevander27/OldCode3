package main.java;

public class Assignment3_22_23 {

	public static void main(String[] args) {
		System.out.printf("Infix:\n\n");
		String input = "(1+(2-3))";
		PEMDASOperation.FormulaType test = PEMDASOperation.FormulaType.INFIX;
		PEMDASOperation operation = new PEMDASOperation(test, input);
		System.out.println("Evaluation: " + operation.evaluate());
		System.out.println("Conversion: " + operation.conversion());
		System.out.printf("\nPostfix:\n\n");
		input = "1 2 3 -+";
		test = PEMDASOperation.FormulaType.POSTFIX;
		operation = new PEMDASOperation(test, input);
		System.out.println("Evaluation: " + operation.evaluate());
		System.out.println("Conversion: " + operation.conversion());
	}
}
