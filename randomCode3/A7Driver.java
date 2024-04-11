import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class A7Driver {

	static Integer[][] genmtx (int N)
	{
		Random rand = ThreadLocalRandom.current();
		int v = rand.nextInt(10);
		Integer[][] mtx = new Integer [N][N];
		for (int y = 0; y < N; y++) for (int x = 0; x < N; x++)
		{
			mtx[y][x] = v;
			v += rand.nextInt(10) + 1;
		}
		return mtx;
	}
	static void mtxtest () throws Exception
	{
		for (int N = 1; N <= 20; N++)
		{
			Integer[][] mtx = genmtx(N);
			for (int y = 0; y < N; y++) for (int x = 0; x < N; x++)
			{
				if (!A7Work.contains(mtx[y][x],mtx)) throw new Exception("failed to find a present value");
			}
			if (A7Work.contains(-1,mtx)) throw new Exception("found an absent value");
		}
	}
	public static void main(String[] args) throws Exception {
		/*Integer a = 1;
		Integer b = 2;
		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
		System.out.println(a.compareTo(a));
		if (true) return;*/
		int N = 10;
		Integer[][] matrix = new Integer[N][N];
		int c = 1;
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++, c+=2)
			{
				matrix[i][j] = c;
			}
		}
		System.out.println("Matrix");
		System.out.println(Arrays.deepToString(matrix).replaceAll("], ", "],\n"));
		System.out.println("Contains 5: "+A7Work.contains(5, matrix));
		System.out.println("Correct: true");
		System.out.println("Contains 6: "+A7Work.contains(6, matrix));
		System.out.println("Correct: false");
		//if (true) return;
		//String[] words = new String[]{"abc", "z", "abzf", "12", " a b c"};
		String[] words = new String[]{"abc", "z", "ABZF", "abzg","12", " a b c"};
		System.out.println("Before Sort");
		System.out.println(Arrays.toString(words));
		A7Work.radixSortStrings(words);
		System.out.println("After Sort");
		System.out.println(Arrays.toString(words));
		
		mtxtest();
	}

}
