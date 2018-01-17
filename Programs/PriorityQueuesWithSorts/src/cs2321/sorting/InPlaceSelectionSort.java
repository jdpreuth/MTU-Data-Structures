package cs2321.sorting;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * 
	 * @param array
	 *            - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		/*
		 * TCJ: Nested for loops with each moving through all n elements
		 */
		// TODO Auto-generated method stub
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				K temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}

}
