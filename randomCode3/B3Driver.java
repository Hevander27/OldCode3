
public class B3Driver {

	public static void main(String[] args) {
		B3DHeap<Integer> H = new B3DHeap();//makes heap with 2 children from each parent (should act same as binary heap)
		
		
		
		B3DHeap<Integer> two = new B3DHeap<>(2);//makes heap with 2 children from each parent (should act same as binary heap)
		two.insert(5);
		two.insert(15);
		two.insert(1);
		System.out.println(two);
		/*
		 * 1:1
		 * 2:15
		 * 3:5
		 */
		B3DHeap<Integer> three = new B3DHeap<>(3);//makes heap with 3 children from each parent
		three.insert(5);
		three.insert(15);
		three.insert(25);
		three.insert(1);
		System.out.println(three);
		/*
		 * 1:1
		 * 2:15
		 * 3:25
		 * 4:5
		 */
		//I recommend you test adding several levels and different values of D
	}

}
