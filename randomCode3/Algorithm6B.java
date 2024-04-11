package main.java;

public class Algorithm6B<E extends Comparable<? super E>> {

	private E[] k;
	private MyHeap<E> heap;

	public Algorithm6B(E[] items) { this(items, 4); }
	public Algorithm6B(E[] items, int ki) {
		if (ki >= items.length) ki = 3;
		int kN = items.length - ki;
		k = (E[]) new Comparable[kN];
		E[] s = (E[]) new Comparable[ki];
		for (int i = 0; i < ki; i++) s[i] = items[i];
		for (int i = 0; i < kN; i++) k[i] = items[ki + i];
		heap = new MyHeap<E>(s);
	}

	public E getLargest() {
		for (int i = 0; i < k.length; i++) {
			if (k[i].compareTo(heap.findMin()) > 0) {
				heap.deleteMin();
				heap.setRoot(k[i]);
			}
		}
		return heap.deleteMin();
	}

	public static void main(String[] args) {
		Integer[] nums = {34, 45, 12, 19, 6, 20, 89, 8};
		Algorithm6B<Integer> alg6B = new Algorithm6B<Integer>(nums);
		System.out.println(alg6B.getLargest());
	}
}
