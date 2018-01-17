package cs2321.sorting;

import cs2321.UnorderedPQ;

public class SelectionSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	/**
	 * sort - Perform a PQ sort using an UnorderedPQ
	 * 
	 * @param array
	 *            - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		/*
		 * This method sorts the array using a PQ sort with an unordered PQ. The
		 * PQ sort has two inner for loops when implemented with a unordered PQ
		 */
		super.sort(array, new UnorderedPQ<K, K>());
	}
}
