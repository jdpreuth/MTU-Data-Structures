package cs2321.sorting;

import net.datastructures.PriorityQueue;

/**
 * A class that performs three forms of PQ Sort: SelectionSort (Unordered PQ)
 * InsertionSort (Ordered PQ) HeapSort (Heap PQ)
 *
 * @author CS2321 Instructor
 * @param <K>
 */
public abstract class PQSort<K extends Comparable<K>> {

	/*
	 * Simple MinPQSort - relies on a functional minimum PQ and a Sequence's
	 * addLast, first, last, and next operations are used.
	 */
	@TimeComplexity("O(n)")
	protected void sort(K[] kArray, PriorityQueue<K, K> pq) {
		/*
		 * TCJ: This algorithm has two for loops that each run n times where n
		 * is the number of elements in the array
		 */

		// TODO: implement this sort.

		for (int i = 0; i < kArray.length; i++) {
			pq.insert(kArray[i], kArray[i]);
		}

		for (int i = 0; i < kArray.length; i++) {
			kArray[i] = pq.removeMin().getValue();
		}

	}

}
