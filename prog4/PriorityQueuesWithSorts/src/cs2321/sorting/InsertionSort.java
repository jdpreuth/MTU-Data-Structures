package cs2321.sorting;

import cs2321.OrderedPQ;

public class InsertionSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {

	/**
	 * sort - Perform an PQ sort using an OrderedPQ
	 * 
	 * @param array
	 *            - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		/*
		 * TCJ: Calls the PQSort with an OrderedPQ which has a nested for loop
		 */
		super.sort(array, new OrderedPQ<K, K>());
	}
}
