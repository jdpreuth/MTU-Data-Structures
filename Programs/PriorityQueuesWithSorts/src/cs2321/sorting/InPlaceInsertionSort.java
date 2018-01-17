package cs2321.sorting;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * 
	 * @param array
	 *            - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		/*
		 * TCJ: a while loop runs n times inside of a for loop that runs n times
		 */
		// TODO Auto-generated method stub
		for (int i = 1; i < array.length; i++) {
			K cursor = array[i];
			int j = i - 1;
			while (j >= 0 && array[j].compareTo(cursor) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = cursor;
		}
	}

}
