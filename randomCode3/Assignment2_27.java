package main.java;

public class Assignment2_27 {

	public static int[][] matrix;

	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		System.out.println("Enter matrix number N: ");
		int N = 12; //scan.nextInt();
		createNByNMatrix(N);
		System.out.printf("Please enter an integer: \n");
		int x = 12; //scan.nextInt();
		checkContains(x);
		displayMatrix();
		//scan.close();
	}
	
	private static boolean matrixExists() {
		if (matrix == null) {
			System.out.println("N by N matrix must be created to class");
			return false;
		}
		return true;
	}

	public static void createNByNMatrix(int N) {
		matrix = new int[N][N];
		
		int dr = (int) (Math.random() * N);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				dr = (int) (Math.random() * 3) + (dr + 1);
				matrix[i][j] = dr;
			}
		}
	}

	public static void checkContains(int c) {
		if (contains(c)) System.out.println("" + c + " is contained within the matrix.");
		else             System.out.println("" + c + " is not contained within the matrix.");
	}

	public static boolean contains(int c) {
		int loi = 0, hii = matrix.length - 1, midi = 0;
		while (loi <= hii) {
			midi = loi + (hii - loi) / 2;
			int loj = 0, hij = matrix.length - 1, midj = 0;
			while (loj <= hij) {
				midj = loj + (hij - loj) / 2;
				if      (c < matrix[midi][midj]) hij = midj - 1;
				else if (c > matrix[midi][midj]) loj = midj + 1;
				else return true;
		    }
			if      (c < matrix[midi][midj]) hii = midi - 1;
			else if (c > matrix[midi][midj]) loi = midi + 1;
		}
		return false;
	}

	public static void displayMatrix() {
		String displayMessage = "";
		if (!matrixExists()) return;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				displayMessage += matrix[i][j] + " ";
			}
			displayMessage.trim();
			displayMessage += "\n";
		}
		
		System.out.println(displayMessage);
	}
}
