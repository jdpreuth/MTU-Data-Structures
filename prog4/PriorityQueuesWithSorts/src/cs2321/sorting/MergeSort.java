package cs2321.sorting;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Takes two sorted arrays and combimes them into one
	 * 
	 * @param a1
	 *            first sorted array
	 * @param a2
	 *            second sorted array
	 * @param a
	 *            final sorted array
	 */
	@TimeComplexity("O(n)")
	private void merge(E[] a1, E[] a2, E[] a) {
		/*
		 * TCJ: Has to look through each element of both arrays to compare them
		 * into a single array
		 */
		int i = 0;
		int j = 0;
		while (i + j < a.length) {
			if (j == a2.length || (i < a1.length && a1[i].compareTo(a2[j]) < 0)) {
				a[i + j] = a1[i++];
			} else {
				a[i + j] = a2[j++];
			}
		}
	}

	/**
	 * Takes an array, divides it into smaller arrays, and then sorts them
	 * together
	 * 
	 * @param array
	 *            array to be sorted
	 */
	@TimeComplexity("O(nlogn)")
	public void sort(E[] array) {
		/*
		 * TCJ: This algorithm divides the array in half each time O(logn) and
		 * then combines everything together in O(n) time
		 */
		// TODO Auto-generated method stub
		int n = array.length;
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		E[] a1 = Arrays.copyOfRange(array, 0, mid);
		E[] a2 = Arrays.copyOfRange(array, mid, n);
		sort(a1);
		sort(a2);
		merge(a1, a2, array);
	}
}
