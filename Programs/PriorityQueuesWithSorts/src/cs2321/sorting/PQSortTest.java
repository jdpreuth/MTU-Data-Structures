package cs2321.sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class PQSortTest {
	cs2321.sorting.Sorter<Integer> sorter;

	@Before
	public void setup() {
		sorter = new InsertionSort<Integer>();
	}

	@Test
	public void test1() {
		Integer[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] expectedData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		sorter.sort(data);
		assertArrayEquals(expectedData, data);
	}

	@Test
	public void test2() {
		Integer[] data = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Integer[] expectedData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		sorter.sort(data);
		assertArrayEquals(expectedData, data);
	}

	@Test
	public void test3() {
		Integer[] data = { 1, 10, 2, 9, 3, 8, 4, 5, 7, 6 };
		Integer[] expectedData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		sorter.sort(data);
		assertArrayEquals(expectedData, data);
	}

	@Test
	public void test4() {
		Integer[] data = { 10, 9, 9, 7, 6, 6, 6, 3, 2, 1 };
		Integer[] expectedData = { 1, 2, 3, 6, 6, 6, 7, 9, 9, 10 };
		sorter.sort(data);
		assertArrayEquals(expectedData, data);
	}

	@Test
	public void test5() {
		Integer[] data = new Integer[1024];
		Integer[] expectedData = new Integer[1024];
		int a = 5; // a must be 4c+1
		int b = 3; // b must be odd
		int m = 1024;
		int x = 10; // the first number;
		data[0] = x;
		for (int i = 1; i < 1024; i++) {
			x = (a * x + b) % m;
			data[i] = x;
		}
		for (int i = 0; i < 1024; i++) {
			expectedData[i] = i;
		}
		sorter.sort(data);
		assertArrayEquals(expectedData, data);
	}
}