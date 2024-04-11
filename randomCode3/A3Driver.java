
public class A3Driver {
	
	static void TestDoubleLL (int N, int pos)
	{
		A3DoubleLL<Integer> list = new A3DoubleLL<>();
		for(int i = 0; i < N; i++) {
			list.add(i);
		}
		System.out.println("Before Swap("+pos+")");
		System.out.println(list.printList());
		System.out.println(list.printListRev());
		list.swap(pos);
		System.out.println("After Swap("+pos+")");
		System.out.println(list.printList());
		System.out.println(list.printListRev());
		System.out.println();
	}
	public static void main(String[] args) {
		
		int N = 8;
		for (int i = -1; i <= N; i++)
		{
			TestDoubleLL(N,i);
		}
		
		
		A3CircleLL hotPotatoe = new A3CircleLL();
		hotPotatoe.playGame(5, 3);
		
		A3Queue<Integer> queue = new A3Queue<>();
		queue.enqueue(5);
		queue.enqueue(20);
		queue.enqueue(15);
		System.out.println(queue.peek());
		System.out.println(queue.dequeue());
		queue.enqueue(25);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());

	}
}
