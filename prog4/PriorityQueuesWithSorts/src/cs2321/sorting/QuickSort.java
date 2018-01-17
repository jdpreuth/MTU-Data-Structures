package cs2321.sorting;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Sorts an array using a quicksort
	 * 
	 * @param E[]
	 *            the array to be sorted
	 */
	@TimeComplexity("O(nlogn)")
	public void sort(E[] array) {
		/*
		 * TCJ: This method calls the quicksort method which is an nlogn
		 * algorithm
		 */
		// TODO Auto-generated method stub
		if (array == null || array.length == 0) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	/**
	 * Takes an array, paritions it apart, and sorts the array
	 * 
	 * @param A
	 *            array to be sorted
	 * @param p
	 *            the first index of the array
	 * @param r
	 */
	@TimeComplexity("O(nlogn)")
	private void quickSort(E[] A, int p, int r) {
		/*
		 * This algorithm cuts the data in half each time and then calls an O(n)
		 * alg partition each time
		 */
		if (p < r) {
			int q = partition(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}
	}

	/**
	 * Partitions an array based on a pivot
	 * 
	 * @param A
	 *            array to be partitioned
	 * @param p
	 *            pivot index
	 * @param r
	 *            range
	 * @return
	 */
	@TimeComplexity("O(n)")
	private int partition(E[] A, int p, int r) {
		/*
		 * This algorithm has to look through
		 */
		E x = A[r];
		int i = p;
		int j = r - 1;
		while (i <= j) {
			while (i <= j && A[i].compareTo(x) <= 0) {
				i++;
			}
			while (j >= i && A[j].compareTo(x) >= 0) {
				j--;
			}
			if (i < j) {
				E temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		E temp = A[i];
		A[i] = A[r];
		A[r] = temp;
		return i;
	}
}
