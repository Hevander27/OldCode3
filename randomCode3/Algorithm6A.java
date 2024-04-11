package main.java;

public class Algorithm6A<E extends Comparable<? super E>> {

	private MyHeap<E> heap;

	public Algorithm6A(E[] items) {
		heap = new MyHeap<E>(items);
	}

	public E getSmallest(int k) {
		E item = null;
		for (int i = 0; i < k; i++) item = heap.deleteMin();
		return item;
	}

	public static void main(String[] args) {
		Integer[] nums = {34, 45, 12, 19, 6, 20, 89, 8};
		Algorithm6A<Integer> alg6A = new Algorithm6A<Integer>(nums);
		System.out.println(alg6A.getSmallest(4));
	}
}
