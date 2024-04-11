
public class MBDriver {

	static void segrevtest (int listlen, int seglen)
	{
		MBDoubleLL<Integer> list = new MBDoubleLL<>();
		for (int i = 1; i <= listlen; i++) list.add(i);
		System.out.printf("List Len %d, Seg Len %d\n",listlen,seglen);
		String before = String.format("\tForward: %s\n\tBackward: %s\n",list.printList(),list.printListRev());
		list.reverseSegments(seglen);
		String after = String.format("\tForward: %s\n\tBackward: %s\n",list.printList(),list.printListRev());
		System.out.printf("Before...\n%sAfter...\n%s\n",before,after);
	}
	
	public static void main(String[] args) {
		MBBST<Integer> tree1 = new MBBST<>();
		tree1.insert(5);
		tree1.insert(4);
		tree1.insert(7);
		tree1.insert(2);
		tree1.insert(3);
		tree1.insert(8);
		tree1.insert(1);
		tree1.insert(6);
		tree1.insert(9);
		tree1.insert(10);
		tree1.insert(0);
/*
     5
   4   7
  2   6 8
 1 3     9
0         10
 */
		System.out.println("Correct:true, Method:"+tree1.contains(tree1));
		System.out.println("Correct:true, Method:"+tree1.contains(tree1));
		
		MBBST<Integer> tree2 = new MBBST<>();
		tree2.insert(2);
		tree2.insert(1);
		tree2.insert(3);
/*
 2
1 3
 */
		System.out.println("Correct:true, Method:"+tree1.contains(tree2));
		System.out.println("Correct:false, Method:"+tree2.contains(tree1));
		
		MBBST<Integer> tree3 = new MBBST<>();
		tree3.insert(3);
		tree3.insert(1);
		tree3.insert(2);
/*
 3
1
 2
 */
		System.out.println("Correct:false, Method:"+tree1.contains(tree3));
		System.out.println("Correct:false, Method:"+tree3.contains(tree1));
		
		
		
		
		
		for (int i = 1; i <= 7; i++)
			segrevtest(6,i);
	}

}
