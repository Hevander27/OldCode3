import java.util.Arrays;

public class A2Driver {

	public static void main(String[] args) {
		for(int i = 10; i <= 50; i+=10)
		{
			System.out.println("N = "+i);
			System.out.println("Sum1:"+A2Work.sum1(i));
			System.out.println("Sum2:"+A2Work.sum2(i));
			System.out.println("Sum3:"+A2Work.sum3(i));
			System.out.println("Sum4:"+A2Work.sum4(i));
			System.out.println("Sum5:"+A2Work.sum5(i));
			System.out.println("Sum6:"+A2Work.sum6(i));
			System.out.println();
		}
		for(int i = 10; i <= 50; i+=10)
		{
			System.out.println("N = "+i);
			System.out.println(Arrays.toString(A2Work.algorithm1(i)));
			System.out.println(Arrays.toString(A2Work.algorithm2(i)));
			System.out.println(Arrays.toString(A2Work.algorithm3(i)));
			System.out.println();
		}
	}

}
