package cs2321.sorting;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * 
	 * @param array
	 *            - Array to sort
	 */

	private int heapSize;

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private void exchange(K[] A, int i, int largest) {
		K temp = A[i];
		A[i] = A[largest];
		A[largest] = temp;
	}

	/**
	 * Forms a max heap from an array at a given i
	 * 
	 * @param A
	 *            array to be heapified
	 * @param i
	 *            index to be used
	 */
	@TimeComplexity("O(logn)")
	private void maxHeapify(K[] A, int i) {
		/*
		 * TCJ: cuts the data in half each iteration
		 */
		int largest = i;

		int l = left(i);
		int r = right(i);

		if (l < heapSize && A[l].compareTo(A[largest]) > 0) {
			largest = l;
		}

		if (r < heapSize && A[r].compareTo(A[largest]) > 0) {
			largest = r;
		}

		if (i != largest) {
			exchange(A, i, largest);
			maxHeapify(A, largest);
		}

	}

	/**
	 * Builds a max heap given an array
	 * 
	 * @param A
	 *            array to be made a heap
	 */
	@TimeComplexity("O(nlogn")
	private void buildMaxHeap(K[] A) {
		/*
		 * TCJ: calls an O(logn) operation n times
		 */
		heapSize = A.length;

		for (int i = (A.length / 2) - 1; i >= 0; i--) {
			maxHeapify(A, i);
		}

	}

	/**
	 * Sorts an array using a heap
	 * 
	 * @param array
	 *            the array to be sorted
	 */
	@TimeComplexity("O(nlogn)")
	public void sort(K[] array) {
		/*
		 * TCJ: Calls an O(nlogn) operation
		 */
		// TODO Auto-generated method stub
		buildMaxHeap(array);

		for (int i = array.length - 1; i >= 0; i--) {
			exchange(array, 0, i);
			heapSize--;
			maxHeapify(array, 0);
		}
	}

}
