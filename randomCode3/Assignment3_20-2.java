package main.java;

public class Assignment3_20 {

	// Pro: The ability to restore deleted values 
	// Con: Retained memory. O(N) worst case.

	public static void main(String[] args) {
		LLDoubleLazy<Integer> list = new LLDoubleLazy<>();
		for (int i = 1; i <= 10; i++) list.add(i);
		System.out.println("Deleting index 1");
		int d1 = list.delete(1);
		System.out.println("Deleting index 3");
		int d2 = list.delete(3);
		System.out.println("Deleting index 5");
		int d3 = list.delete(5);
		System.out.println("Deleting index 6");
		int d4 = list.delete(6);
		System.out.println(list.printList());

		boolean hidden = list.find(d1) == null && list.find(d2) == null &&
				         list.find(d3) == null && list.find(d4) == null; 
		System.out.println("lazy deleted values are hidden or deleted: " + hidden);

		int v1 = list.get(1);
		int v2 = list.get(3);
		int v3 = list.get(5);
		System.out.println(list.remove(v1));
		System.out.println(list.remove(v2));
		System.out.println(list.remove(v3));
		System.out.println(list.printList());
	}
}
