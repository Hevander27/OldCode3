import java.util.Comparator;

public class A1Driver {

	public static void main(String[] args) {
		for(int i = 10; i <= 10000; i*=10) {
			long start = System.currentTimeMillis();
			int kth = A1Work.kthLargest(i, i/2);
			long end = System.currentTimeMillis();
			System.out.println("i = "+i+", k = "+kth+", elaped time = "+(end-start)/1000.0+"s");
		}
		
		for(int i = 1; i < 10; i++) {
			System.out.println("Binary 1's in "+i+" = "+A1Work.binaryOnes(i));
		}
		
		System.out.println("All Rectangles");
		Rectangle[] rects = new Rectangle[5];
		for(int i = 0; i < rects.length; i++) {
			rects[i] = new Rectangle((int)(Math.random()*10), (int)(Math.random()*10));
			System.out.println(rects[i]);
		}
		System.out.println("Rectangle with the largest area = " + findMax(rects, new A1Work.AreaCompare()));
		System.out.println("Rectangle with the largest perimeter = " + findMax(rects, new A1Work.PerimeterCompare()));
	}
	
	/* DO NOT MODIFY */
	public static <E> E findMax(E[] arr, Comparator<? super E> cmp) {
		int maxIndex = 0;
		for(int i = 1; i < arr.length; i++) {
			if(cmp.compare(arr[i], arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}

}
