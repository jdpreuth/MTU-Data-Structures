package cs2321.sorting;

import cs2321.HeapPQ;

public class HeapSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	/**
	 * sort - Perform an PQ sort using a Heap
	 * 
	 * @param array
	 *            - Array to sort
	 */
	@TimeComplexity("O(nlogn)")
	public void sort(K[] array) {
		/*
		 * TCJ: Uses a heap for a PQSort which is an O(logn) n times
		 */
		super.sort(array, new HeapPQ<K, K>());
	}
}
